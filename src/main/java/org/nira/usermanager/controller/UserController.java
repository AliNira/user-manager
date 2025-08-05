package org.nira.usermanager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nira.usermanager.dto.UserRequestDto;
import org.nira.usermanager.dto.UserResponseDto;
import org.nira.usermanager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        UserResponseDto userCreated = userService.createUser(userRequestDto);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") Long id) {
        UserResponseDto userRequestDto = userService.getUserById(id);
        return new ResponseEntity<>(userRequestDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable("id") Long userId,
            @RequestBody @Valid UserRequestDto userRequestDto) {
        UserResponseDto userUpdated = userService.updateUser(userId, userRequestDto);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("Delete Successfully.", HttpStatus.NO_CONTENT);
    }


}
