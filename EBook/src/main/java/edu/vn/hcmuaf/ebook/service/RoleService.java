package edu.vn.hcmuaf.ebook.service;

import edu.vn.hcmuaf.ebook.dto.request.RoleRequest;
import edu.vn.hcmuaf.ebook.dto.response.RoleResponse;
import edu.vn.hcmuaf.ebook.entity.Role;
import edu.vn.hcmuaf.ebook.mapper.RoleMapper;
import edu.vn.hcmuaf.ebook.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;

    public RoleResponse create(RoleRequest request) {
        Role role = roleMapper.toRole(request);
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    public List<RoleResponse> getAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

    public void delete(long id) {
        roleRepository.deleteById(id);
    }

}
