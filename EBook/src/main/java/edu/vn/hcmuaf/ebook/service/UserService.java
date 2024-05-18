package edu.vn.hcmuaf.ebook.service;

import edu.vn.hcmuaf.ebook.dto.request.UserCreationRequest;
import edu.vn.hcmuaf.ebook.dto.response.UserResponse;
import edu.vn.hcmuaf.ebook.entity.User;
import edu.vn.hcmuaf.ebook.exception.AppException;
import edu.vn.hcmuaf.ebook.exception.ErrorCode;
import edu.vn.hcmuaf.ebook.mapper.UserMapper;
import edu.vn.hcmuaf.ebook.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.USER_EXISTED);
        if (!request.getPassword().equals(request.getConfirmPassword()))
            throw new AppException(ErrorCode.PASSWORD_NOT_MATCH);
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .toList();
    }
}
