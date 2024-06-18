package edu.vn.hcmuaf.ebook.service;

import edu.vn.hcmuaf.ebook.dto.request.ResetPasswordRequest;
import edu.vn.hcmuaf.ebook.dto.request.UserCreationRequest;
import edu.vn.hcmuaf.ebook.dto.request.UserUpdationRequest;
import edu.vn.hcmuaf.ebook.dto.response.BookResponse;
import edu.vn.hcmuaf.ebook.dto.response.RoleResponse;
import edu.vn.hcmuaf.ebook.dto.response.UserResponse;
import edu.vn.hcmuaf.ebook.entity.PasswordResetToken;
import edu.vn.hcmuaf.ebook.entity.Role;
import edu.vn.hcmuaf.ebook.entity.User;
import edu.vn.hcmuaf.ebook.enums.RoleEnum;
import edu.vn.hcmuaf.ebook.exception.AppException;
import edu.vn.hcmuaf.ebook.exception.ErrorCode;
import edu.vn.hcmuaf.ebook.mapper.BookMapper;
import edu.vn.hcmuaf.ebook.mapper.RoleMapper;
import edu.vn.hcmuaf.ebook.mapper.UserMapper;
import edu.vn.hcmuaf.ebook.repository.BookRepository;
import edu.vn.hcmuaf.ebook.repository.PasswordResetTokenRepository;
import edu.vn.hcmuaf.ebook.repository.RoleRepository;
import edu.vn.hcmuaf.ebook.repository.UserRepository;
import edu.vn.hcmuaf.ebook.utils.RandomStringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    PasswordResetTokenRepository passwordResetTokenRepository;
    EmailService emailService;
    BookRepository bookRepository;
    BookMapper bookMapper;
    RoleMapper roleMapper;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.USER_EXISTED);
        if (!request.getPassword().equals(request.getConfirmPassword()))
            throw new AppException(ErrorCode.PASSWORD_NOT_MATCH);
        User user = userMapper.toUser(request);
        Role role = roleRepository.findByName(RoleEnum.USER.name())
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse getMyInfo() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .map(userMapper::toUserResponse)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers() {
        log.info("In method getAllUsers");
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @PostAuthorize("returnObject.email == authentication.name")
    public UserResponse getUser(String id) {
        log.info("In method getUser");
        return userRepository.findById(id)
                .map(userMapper::toUserResponse)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }

    public void forgetPassword(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.EMAIL_NOT_EXISTED));
//        passwordResetTokenRepository.deleteByUser(user);
        String token = RandomStringUtils.randomNumber(6);
        PasswordResetToken passwordResetToken = PasswordResetToken.builder()
                .token(token)
                .user(user)
                .expiryDate(new Date(System.currentTimeMillis() + 3600000))
                .build();
        passwordResetTokenRepository.save(passwordResetToken);
        emailService.sendEmail(email, "Reset password", "Enter code " + token + " to reset password. This code will expire in 1 hour.");
    }

    public void resetPassword(ResetPasswordRequest request) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(request.getToken());
        if (passwordResetToken == null)
            throw new AppException(ErrorCode.TOKEN_NOT_EXISTED);
        if (!passwordResetToken.getUser().getEmail().equals(request.getEmail()))
            throw new AppException(ErrorCode.EMAIL_NOT_MATCH);
        if (passwordResetToken.getExpiryDate().before(new Date()))
            throw new AppException(ErrorCode.TOKEN_EXPIRED);
        User user = passwordResetToken.getUser();
        String newPassword = RandomStringUtils.randomString(8);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        passwordResetTokenRepository.delete(passwordResetToken);
        emailService.sendEmail(request.getEmail(), "New password", "Your new password is " + newPassword);
    }

    public void addBookToWishList(Long bookId) {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        user.getBooks().add(bookRepository.findById(bookId)
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_EXISTED)));
        userRepository.save(user);
    }

    public List<BookResponse> getBooksInWishList() {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return user.getBooks().stream()
                .map(bookMapper::toBookResponse)
                .toList();
    }

    public void removeBookFromWishList(long bookId) {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        user.getBooks().removeIf(book -> book.getId() == bookId);
        userRepository.save(user);
    }

    public void updateUser(UserUpdationRequest request) {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        user.setFullName(request.getFullName());
        user.setAvatar(request.getAvatar());
        userRepository.save(user);
    }

    public RoleResponse getRole() {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Role role = user.getRole();
        return roleMapper.toRoleResponse(role);
    }
}
