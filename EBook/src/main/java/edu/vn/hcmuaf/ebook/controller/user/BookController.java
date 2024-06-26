package edu.vn.hcmuaf.ebook.controller.user;

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
    ApiResponse<List<BookResponse>> getActiveBooks() {
        return ApiResponse.<List<BookResponse>>builder()
                .result(bookService.getBooksActive())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<BookResponse> getActiveBook(@PathVariable long id) {
        return ApiResponse.<BookResponse>builder()
                .result(bookService.getBookActive(id))
                .build();
    }

    @GetMapping("/search/{keyword}")
    ApiResponse<List<BookResponse>> searchActiveBooks(@PathVariable String keyword) {
        return ApiResponse.<List<BookResponse>>builder()
                .result(bookService.searchActiveBooks(keyword))
                .build();
    }

}
