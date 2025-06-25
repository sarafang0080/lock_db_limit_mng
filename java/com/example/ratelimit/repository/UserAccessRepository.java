package com.example.ratelimit.repository;

import com.example.ratelimit.entity.UserAccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccessRepository extends JpaRepository<UserAccess, String> {
}