package edu.vn.hcmuaf.ebook.configuration;

import edu.vn.hcmuaf.ebook.entity.Role;
import edu.vn.hcmuaf.ebook.entity.User;
import edu.vn.hcmuaf.ebook.enums.RoleEnum;
import edu.vn.hcmuaf.ebook.exception.AppException;
import edu.vn.hcmuaf.ebook.exception.ErrorCode;
import edu.vn.hcmuaf.ebook.repository.RoleRepository;
import edu.vn.hcmuaf.ebook.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
    @Autowired
    PasswordEncoder passwordEncoder;

//    @Bean
//    ApplicationRunner applicationRunner(UserRepository userRepository,
//                                        RoleRepository roleRepository) {
//        return args -> {
//            if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
//                Role role = roleRepository.findByName(RoleEnum.ADMIN.name())
//                        .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));
//                User user = User.builder()
//                        .email("admin@gmail.com")
//                        .password(passwordEncoder.encode("admin"))
//                        .role(role)
//                        .build();
//                userRepository.save(user);
//            }
//        };
//    }
}
