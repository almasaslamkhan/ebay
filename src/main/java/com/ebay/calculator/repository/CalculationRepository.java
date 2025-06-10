package com.ebay.calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebay.calculator.entity.Calculation;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Long> {
}
