package com.ebay.calculator.exceptions;

public class CalculationNotFoundException extends RuntimeException{
    public CalculationNotFoundException(Long id) {
        super("Calculation not found with ID: " + id);
    }
}
