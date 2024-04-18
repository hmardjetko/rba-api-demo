package com.example.rba.repository;

import com.example.rba.model.CardRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRequestRepository extends JpaRepository<CardRequest, Long> {
    List<CardRequest> findByClientId(Long clientId);
}
