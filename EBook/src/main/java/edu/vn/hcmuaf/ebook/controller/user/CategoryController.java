package edu.vn.hcmuaf.ebook.controller;

import edu.vn.hcmuaf.ebook.dto.response.ApiResponse;
import edu.vn.hcmuaf.ebook.dto.response.BookResponse;
import edu.vn.hcmuaf.ebook.dto.response.CategoryResponse;
import edu.vn.hcmuaf.ebook.service.BookService;
import edu.vn.hcmuaf.ebook.service.CategoryService;
import edu.vn.hcmuaf.ebook.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService categoryService;
    BookService bookService;

    @GetMapping
    ApiResponse<List<CategoryResponse>> getCategories() {
        return ApiResponse.<List<CategoryResponse>>builder().result(categoryService.getCategories()).build();
    }

    @GetMapping("/{categoryId}")
    ApiResponse<List<BookResponse>> getBooksActiveByCategoryId(@PathVariable long categoryId) {
        return ApiResponse.<List<BookResponse>>builder().result(bookService.getBooksActiveByCategoryId(categoryId)).build();
    }
}
