package edu.vn.hcmuaf.ebook.mapper;

import edu.vn.hcmuaf.ebook.dto.request.UserCreationRequest;
import edu.vn.hcmuaf.ebook.dto.response.UserResponse;
import edu.vn.hcmuaf.ebook.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);

}
