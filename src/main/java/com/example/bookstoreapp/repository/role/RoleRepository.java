package com.example.bookstoreapp.repository.role;

import com.example.bookstoreapp.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByRoleName(Role.RoleName roleName);
}
