package edu.vn.hcmuaf.ebook.mapper;

import edu.vn.hcmuaf.ebook.dto.request.RoleRequest;
import edu.vn.hcmuaf.ebook.dto.response.RoleResponse;

import edu.vn.hcmuaf.ebook.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
   Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
