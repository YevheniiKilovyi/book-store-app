package com.example.bookstoreapp.service.user;

import com.example.bookstoreapp.dto.request.user.UserRegistrationRequestDto;
import com.example.bookstoreapp.dto.response.user.UserResponseDto;
import com.example.bookstoreapp.exception.RegistrationException;
import com.example.bookstoreapp.mapper.user.UserRegistrationMapper;
import com.example.bookstoreapp.model.Role;
import com.example.bookstoreapp.model.User;
import com.example.bookstoreapp.repository.role.RoleRepository;
import com.example.bookstoreapp.repository.user.UserRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserRegistrationMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findUserByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("User with email " + requestDto.getEmail()
                    + " already exists, unable to complete registration");
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(encoder.encode(requestDto.getPassword()));
        user.setRoles(Set.of(roleRepository.findRoleByRoleName(Role.RoleName.ROLE_USER).get()));
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
