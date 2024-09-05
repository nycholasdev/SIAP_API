package com.siap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siap.models.User;
import com.siap.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUserService(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserByNameService(String name) {
        return userRepository.findByName(name);
    }

    public Optional<User> getUserByEmailService(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsersService() {
        return userRepository.findAll();
    }

    public User getUserByIdService(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean deleteUserByIdService(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
