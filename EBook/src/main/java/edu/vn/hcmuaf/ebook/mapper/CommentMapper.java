package edu.vn.hcmuaf.ebook.mapper;

import edu.vn.hcmuaf.ebook.dto.request.CommentRequest;
import edu.vn.hcmuaf.ebook.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toComment(CommentRequest request);

}
