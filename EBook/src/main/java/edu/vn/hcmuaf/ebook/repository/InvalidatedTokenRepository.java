package edu.vn.hcmuaf.ebook.repository;

import edu.vn.hcmuaf.ebook.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
}