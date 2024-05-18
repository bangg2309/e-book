package edu.vn.hcmuaf.ebook.repository;

import edu.vn.hcmuaf.ebook.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}