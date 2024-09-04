package com.luannycholas.repositories;

import java.util.List;
import java.util.Optional;
import com.luannycholas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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