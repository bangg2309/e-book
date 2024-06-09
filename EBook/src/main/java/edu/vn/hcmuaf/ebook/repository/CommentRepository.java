package edu.vn.hcmuaf.ebook.repository;

import edu.vn.hcmuaf.ebook.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}