package edu.vn.hcmuaf.ebook.repository;

import edu.vn.hcmuaf.ebook.entity.PasswordResetToken;
import edu.vn.hcmuaf.ebook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(User user);

    void deleteByUser(User user);
}