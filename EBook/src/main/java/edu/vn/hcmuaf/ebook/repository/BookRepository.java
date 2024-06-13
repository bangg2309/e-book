package edu.vn.hcmuaf.ebook.repository;

import edu.vn.hcmuaf.ebook.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Collection<Book> findByCategoriesIdAndStatus(long categoryId, int status);

    Collection<Book> findByTitleContainingIgnoreCaseAndStatus(String keyword, int status);

    Collection<Book> findByStatus(int status);

    Optional<Book> findByIdAndStatus(Long id, int status);
}