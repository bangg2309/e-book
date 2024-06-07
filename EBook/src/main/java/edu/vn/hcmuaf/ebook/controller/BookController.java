package edu.vn.hcmuaf.ebook.controller;

import edu.vn.hcmuaf.ebook.dto.response.ApiResponse;
import edu.vn.hcmuaf.ebook.dto.response.BookResponse;
import edu.vn.hcmuaf.ebook.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.query.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookController {
    BookService bookService;

    @GetMapping
    ApiResponse<List<BookResponse>> getBooks() {
        return ApiResponse.<List<BookResponse>>builder()
                .result(bookService.getBooks())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<BookResponse> getBook(@PathVariable long id) {
        return ApiResponse.<BookResponse>builder()
                .result(bookService.getBook(id))
                .build();
    }

    @GetMapping("/search/{keyword}")
    ApiResponse<List<BookResponse>> searchBooks(@PathVariable String keyword) {
        return ApiResponse.<List<BookResponse>>builder()
                .result(bookService.searchBooks(keyword))
                .build();
    }

}
