package edu.vn.hcmuaf.ebook.controller;


import edu.vn.hcmuaf.ebook.utils.Message;
import edu.vn.hcmuaf.ebook.dto.request.ForgetPasswordRequest;
import edu.vn.hcmuaf.ebook.dto.request.ResetPasswordRequest;
import edu.vn.hcmuaf.ebook.dto.request.UserCreationRequest;
import edu.vn.hcmuaf.ebook.dto.response.ApiResponse;
import edu.vn.hcmuaf.ebook.dto.response.UserResponse;
import edu.vn.hcmuaf.ebook.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/users")
public class UserController {
    UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @PostMapping("/forget-password")
    ApiResponse<Void> forgetPassword(@RequestBody ForgetPasswordRequest request) {
        String email = request.getEmail();
        userService.forgetPassword(email);
        return ApiResponse.<Void>builder()
                .message(Message.PASSWORD_FORGET_SUCCESS)
                .build();
    }

    @PostMapping("/reset-password")
    ApiResponse<Void> resetPassword(@RequestBody ResetPasswordRequest request) {
        userService.resetPassword(request);
        return ApiResponse.<Void>builder()
                .message(Message.PASSWORD_RESET_SUCCESS)
                .build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getAllUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("User: {}", authentication.getName());
        log.info("Role: {}", authentication.getAuthorities());

        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAllUsers())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<UserResponse> getUser(@PathVariable String id) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(id))
                .build();
    }

    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }
}
