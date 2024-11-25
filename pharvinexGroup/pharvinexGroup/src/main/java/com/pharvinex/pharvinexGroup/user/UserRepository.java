package com.pharvinex.pharvinexGroup.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

     Optional<User> findByEmail(String email);
}
