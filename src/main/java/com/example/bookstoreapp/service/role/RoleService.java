package com.example.bookstoreapp.service.role;

import com.example.bookstoreapp.model.Role;

public interface RoleService {
    Role findRoleByRoleName(Role.RoleName roleName);
}
