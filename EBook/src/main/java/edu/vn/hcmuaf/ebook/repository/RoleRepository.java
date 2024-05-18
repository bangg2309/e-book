package edu.vn.hcmuaf.ebook.repository;

import edu.vn.hcmuaf.ebook.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}