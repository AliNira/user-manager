package org.nira.usermanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.nira.usermanager.dto.UserDto;
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
    public UserDto createUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("Email already exists");
        }
        User user = MAPPER.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        UserDto savedUserDto = MAPPER.mapToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(MAPPER::mapToUserDto)
                .toList();
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userDto.getId()));
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepository.deleteById(userId);
    }

}
