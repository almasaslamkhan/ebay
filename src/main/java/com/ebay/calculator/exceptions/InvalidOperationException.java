package com.ebay.calculator.exceptions;
import com.ebay.calculator.calculator.Operation;
public class InvalidOperationException extends CalculationException {
    public InvalidOperationException(Operation operation) {
        super("Operation not supported: " + operation);
    }
}
