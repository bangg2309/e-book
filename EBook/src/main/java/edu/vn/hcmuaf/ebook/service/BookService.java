package edu.vn.hcmuaf.ebook.service;

import edu.vn.hcmuaf.ebook.dto.response.ApiResponse;
import edu.vn.hcmuaf.ebook.dto.response.BookResponse;
import edu.vn.hcmuaf.ebook.entity.Book;
import edu.vn.hcmuaf.ebook.mapper.BookMapper;
import edu.vn.hcmuaf.ebook.repository.BookRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookService {
    BookRepository bookRepository;
    BookMapper bookMapper;

    public List<BookResponse> getBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toBookResponse)
                .toList();
    }

    public BookResponse getBook(long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toBookResponse)
                .orElse(null);
    }

    public List<BookResponse> getBooksByCategoryId(long categoryId) {
        return bookRepository.findByCategoriesId(categoryId).stream()
                .map(bookMapper::toBookResponse)
                .toList();
    }

    public List<BookResponse> searchBooks(String keyword) {
        return bookRepository.findByTitleContainingIgnoreCase(keyword).stream()
                .map(bookMapper::toBookResponse)
                .toList();
    }
}
