package org.nira.usermanager.service;

import org.nira.usermanager.dto.UserRequestDto;
import org.nira.usermanager.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto getUserById(Long userId);

    List<UserResponseDto> getAllUsers();

    UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto);

    void deleteUser(Long userId);
}
