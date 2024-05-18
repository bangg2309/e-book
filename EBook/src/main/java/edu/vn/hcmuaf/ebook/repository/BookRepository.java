package edu.vn.hcmuaf.ebook.repository;

import edu.vn.hcmuaf.ebook.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}