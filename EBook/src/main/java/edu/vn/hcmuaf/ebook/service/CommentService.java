package edu.vn.hcmuaf.ebook.service;

import edu.vn.hcmuaf.ebook.dto.request.CommentRequest;
import edu.vn.hcmuaf.ebook.entity.Book;
import edu.vn.hcmuaf.ebook.entity.Comment;
import edu.vn.hcmuaf.ebook.entity.User;
import edu.vn.hcmuaf.ebook.exception.AppException;
import edu.vn.hcmuaf.ebook.exception.ErrorCode;
import edu.vn.hcmuaf.ebook.mapper.CommentMapper;
import edu.vn.hcmuaf.ebook.repository.BookRepository;
import edu.vn.hcmuaf.ebook.repository.CommentRepository;
import edu.vn.hcmuaf.ebook.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentService {
    CommentRepository commentRepository;
    UserRepository userRepository;
    CommentMapper commentMapper;
    BookRepository bookRepository;

    public void addComment(CommentRequest request) {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_EXISTED));
        Comment comment = commentMapper.toComment(request);
        comment.setUser(user);
        comment.setBook(book);
        commentRepository.save(comment);
    }
}
