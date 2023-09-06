package com.usermanageent.usermanagement.repository;

import com.usermanageent.usermanagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("""
        SELECT user
        FROM User user
        ORDER BY user.userName DESC
    """)
    Page<User> findAllByPage(Pageable pageable);
}
