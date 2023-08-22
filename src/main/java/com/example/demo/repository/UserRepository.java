package com.example.demo.repository;

import com.example.demo.repository.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = """
    SELECT user_id from user
    """, nativeQuery = true)
    List<Integer> testSelect();

    @Query(value = """
    SELECT * from user where email = :#{#email}
    """, nativeQuery = true)
    Optional<UserEntity> findByEmail(@Param("email") String email);
}
