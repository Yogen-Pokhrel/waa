package com.waa.logger.service;

import com.waa.logger.entity.Logger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class LoggerServiceImpl implements LoggerService {

    @Autowired
    private final EntityManager entityManager;

    @Transactional
    public void logOperation(String principle, String operation) {
        Logger logger = new Logger(LocalDateTime.now(), principle, operation);
        entityManager.persist(logger);
    }
}
