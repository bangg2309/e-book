package edu.vn.hcmuaf.ebook.controller;

import edu.vn.hcmuaf.ebook.dto.request.CommentRequest;
import edu.vn.hcmuaf.ebook.dto.response.ApiResponse;
import edu.vn.hcmuaf.ebook.service.CommentService;
import edu.vn.hcmuaf.ebook.utils.Message;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentController {
    CommentService commentService;

    @PostMapping
    public ApiResponse<Void> addComment(@RequestBody CommentRequest request) {
        commentService.addComment(request);
        return ApiResponse.<Void>builder()
                .message(Message.COMMENT_ADDED_SUCCESSFULLY)
                .build();
    }
}
