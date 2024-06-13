package edu.vn.hcmuaf.ebook.controller.user;

import edu.vn.hcmuaf.ebook.dto.request.WishListRequest;
import edu.vn.hcmuaf.ebook.dto.response.ApiResponse;
import edu.vn.hcmuaf.ebook.dto.response.BookResponse;
import edu.vn.hcmuaf.ebook.service.UserService;
import edu.vn.hcmuaf.ebook.utils.Message;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/wish-list")
public class WishListController {
    UserService userService;

    @PostMapping
    ApiResponse<Void> addBookToWishList(@RequestBody WishListRequest request) {
        userService.addBookToWishList(request.getBookId());
        return ApiResponse.<Void>builder()
                .message(Message.ADD_TO_WISHLIST_SUCCESS)
                .build();
    }
    @GetMapping
    ApiResponse<List<BookResponse>> getBooksInWishList() {
        return ApiResponse.<List<BookResponse>>builder()
                .result(userService.getBooksInWishList())
                .build();
    }
    @DeleteMapping("/{bookId}")
    ApiResponse<Void> removeBookFromWishList(@PathVariable long bookId) {
        userService.removeBookFromWishList(bookId);
        return ApiResponse.<Void>builder()
                .message(Message.REMOVE_FROM_WISHLIST_SUCCESS)
                .build();
    }
}
