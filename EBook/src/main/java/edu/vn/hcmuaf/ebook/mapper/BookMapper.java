package edu.vn.hcmuaf.ebook.mapper;

import edu.vn.hcmuaf.ebook.dto.request.BookRequest;
import edu.vn.hcmuaf.ebook.dto.response.BookResponse;
import edu.vn.hcmuaf.ebook.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface BookMapper {
    Book toBook(BookRequest request);

    BookResponse toBookResponse(Book book);
}
