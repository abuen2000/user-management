package com.usermanageent.usermanagement.service;

import com.usermanageent.usermanagement.entity.User;
import com.usermanageent.usermanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private  final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    // Get a paginated list of all users
    // Assume that the database will have a high number of users
    public Page<User> getAllUsers(int page, int pageSize) {
        return userRepository.findAllByPage(PageRequest.of(page, pageSize));
    }
}
