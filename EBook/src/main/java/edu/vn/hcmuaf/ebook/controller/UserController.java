package edu.vn.hcmuaf.ebook.controller;


import edu.vn.hcmuaf.ebook.dto.request.UserCreationRequest;
import edu.vn.hcmuaf.ebook.dto.response.ApiResponse;
import edu.vn.hcmuaf.ebook.dto.response.UserResponse;
import edu.vn.hcmuaf.ebook.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/users")
public class UserController {
    UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        System.out.println("UserController.createUser");
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }


}
