package org.nira.usermanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.nira.usermanager.dto.UserRequestDto;
import org.nira.usermanager.dto.UserResponseDto;
import org.nira.usermanager.entity.User;
import org.nira.usermanager.exception.EmailAlreadyExistsException;
import org.nira.usermanager.exception.ResourceNotFoundException;
import org.nira.usermanager.repository.UserRepository;
import org.nira.usermanager.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.nira.usermanager.mapper.AutoUserMapper.MAPPER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        emailExistence(userRequestDto);
        User user = MAPPER.mapToUser(userRequestDto);
        User savedUser = userRepository.save(user);
        UserResponseDto savedUserResponseDto = MAPPER.mapToUserResponseDto(savedUser);
        return savedUserResponseDto;
    }

    private void emailExistence(UserRequestDto userRequestDto) {
        if (userRepository.findByEmail(userRequestDto.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("Email already exists");
        }
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return MAPPER.mapToUserResponseDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(MAPPER::mapToUserResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        existingUser.setFirstName(userRequestDto.getFirstName());
        existingUser.setLastName(userRequestDto.getLastName());
        if (!userRequestDto.getEmail().equals(existingUser.getEmail())){
            emailExistence(userRequestDto);
            existingUser.setEmail(userRequestDto.getEmail());
        }
        User updatedUser = userRepository.save(existingUser);
        return MAPPER.mapToUserResponseDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepository.deleteById(userId);
    }

}
