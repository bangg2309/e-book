package edu.vn.hcmuaf.ebook.mapper;

import edu.vn.hcmuaf.ebook.dto.response.CategoryResponse;
import edu.vn.hcmuaf.ebook.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toCategoryResponse(Category category);

}
