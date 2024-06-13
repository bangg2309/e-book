package edu.vn.hcmuaf.ebook.controller.admin;

import edu.vn.hcmuaf.ebook.dto.request.ChangeStatusBookRequest;
import edu.vn.hcmuaf.ebook.dto.response.ApiResponse;
import edu.vn.hcmuaf.ebook.dto.response.BookResponse;
import edu.vn.hcmuaf.ebook.service.BookService;
import edu.vn.hcmuaf.ebook.utils.Message;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/books")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminBookController {
    BookService bookService;

    @GetMapping
    ApiResponse<List<BookResponse>> getBooks() {
        return ApiResponse.<List<BookResponse>>builder()
                .result(bookService.getBooks())
                .build();
    }

    @PostMapping("/status")
    ApiResponse<Void> changeBookStatus(@RequestBody ChangeStatusBookRequest request) {
        bookService.changeBookStatus(request.getBookId());
        return ApiResponse.<Void>builder()
                .message(Message.CHANGE_BOOK_STATUS_SUCCESS)
                .build();
    }
}
