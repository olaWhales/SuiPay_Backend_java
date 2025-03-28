package com.suipayment.suiPays.data.repository;

import com.suipayment.suiPays.data.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SignUpRepository extends JpaRepository<Users, String> {
    Optional<Users> findUsersByUserName(String username);

    Optional<Users> findByEmail(String email);  // Fixed the method

    boolean existsByEmail(String email);
}
