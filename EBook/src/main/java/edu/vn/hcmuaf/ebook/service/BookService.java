package edu.vn.hcmuaf.ebook.service;

import edu.vn.hcmuaf.ebook.dto.response.BookResponse;
import edu.vn.hcmuaf.ebook.entity.Book;
import edu.vn.hcmuaf.ebook.exception.AppException;
import edu.vn.hcmuaf.ebook.exception.ErrorCode;
import edu.vn.hcmuaf.ebook.mapper.BookMapper;
import edu.vn.hcmuaf.ebook.repository.BookRepository;
import edu.vn.hcmuaf.ebook.utils.Constant;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookService {
    BookRepository bookRepository;
    BookMapper bookMapper;

    public List<BookResponse> getBooksActive() {
        return bookRepository.findByStatus(Constant.ACTIVE).stream()
                .map(bookMapper::toBookResponse)
                .toList();
    }

    public BookResponse getBookActive(long id) {
        Book book = bookRepository.findByIdAndStatus(id, Constant.ACTIVE)
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_EXISTED));
        return bookMapper.toBookResponse(book);
    }

    public List<BookResponse> getBooksActiveByCategoryId(long categoryId) {
        return bookRepository.findByCategoriesIdAndStatus(categoryId, Constant.ACTIVE).stream()
                .map(bookMapper::toBookResponse)
                .toList();
    }

    public List<BookResponse> searchActiveBooks(String keyword) {
        return bookRepository.findByTitleContainingIgnoreCaseAndStatus(keyword, Constant.ACTIVE).stream()
                .map(bookMapper::toBookResponse)
                .toList();
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<BookResponse> getBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toBookResponse)
                .toList();
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void changeBookStatus(long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_EXISTED));
        book.setStatus(book.getStatus() == Constant.ACTIVE ? Constant.INACTIVE : Constant.ACTIVE);
        bookRepository.save(book);
    }
}
