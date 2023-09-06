package com.usermanageent.usermanagement.repository;

import com.usermanageent.usermanagement.entity.Task;
import com.usermanageent.usermanagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("""
        SELECT task
        FROM Task task
        WHERE task.userId = :userId
        ORDER BY task.name DESC
    """)
    Page<Task> findAllByPage(@Param("userId") Long userId, Pageable pageable);
}
