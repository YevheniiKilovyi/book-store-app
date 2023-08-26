package com.example.bookstoreapp.service.role;

import com.example.bookstoreapp.exception.EntityNotFoundException;
import com.example.bookstoreapp.model.Role;
import com.example.bookstoreapp.repository.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role findRoleByRoleName(Role.RoleName roleName) {
        return roleRepository.findRoleByRoleName(roleName).orElseThrow(
                () -> new EntityNotFoundException(
                        "Can't find role by role name " + roleName.name()));
    }
}
