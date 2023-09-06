package com.usermanageent.usermanagement.controller;

import com.usermanageent.usermanagement.dto.UserDTO;
import com.usermanageent.usermanagement.entity.User;
import com.usermanageent.usermanagement.mapper.UserMapper;
import com.usermanageent.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return Optional.ofNullable(userDTO)
                .map(userMapper::asUser)
                .map(userService::save)
                .map(userMapper::asUserDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(RuntimeException::new);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {

        Optional<User> optionalUser = userService.getUserById(id);

        return optionalUser.map(existingUser -> {
            userMapper.updateUserFromDTO(userDTO, existingUser); // Update the existing user
           return Optional.ofNullable(existingUser)
                    .map(userService::save)
                    .map(userMapper::asUserDTO)
                    .map(ResponseEntity::ok)
                    .orElseThrow(RuntimeException::new);
        }).orElseThrow(RuntimeException::new);
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getAllUsers(@RequestParam(name = "page", defaultValue = "0") int page,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        Page<UserDTO> userDTOS = userService.getAllUsers(page,pageSize)
                .map(userMapper::asUserDTO);
        return ResponseEntity.ok(userDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(userMapper::asUserDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(RuntimeException::new);
    }

}
