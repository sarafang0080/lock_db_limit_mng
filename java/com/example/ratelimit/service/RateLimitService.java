package com.example.ratelimit.service;

import com.example.ratelimit.entity.UserAccess;
import com.example.ratelimit.repository.UserAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class RateLimitService {

    @Autowired
    private UserAccessRepository repository;

    private final ReentrantLock lock = new ReentrantLock();

    private static final int LIMIT = 5;
    private static final long INTERVAL_MS = 60000;

    public boolean tryAccess(String userId) {
        lock.lock();
        try {
            long now = Instant.now().toEpochMilli();
            UserAccess record = repository.findById(userId).orElseGet(() -> {
                UserAccess newUser = new UserAccess();
                newUser.setUserId(userId);
                newUser.setTimestamp(now);
                newUser.setCounter(0);
                return newUser;
            });

            if (now - record.getTimestamp() > INTERVAL_MS) {
                record.setTimestamp(now);
                record.setCounter(1);
            } else if (record.getCounter() < LIMIT) {
                record.setCounter(record.getCounter() + 1);
            } else {
                return false;
            }

            repository.save(record);
            return true;
        } finally {
            lock.unlock();
        }
    }
}