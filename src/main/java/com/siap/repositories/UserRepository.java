package com.siap.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siap.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);

    @SuppressWarnings("null")
    Optional<User> findById(Long id);
    
    @SuppressWarnings("null")
    List<User> findAll();

    void deleteById(@SuppressWarnings("null") Long id);

    @SuppressWarnings("null")
    boolean existsById(Long id);

}