package com.ebay.calculator.service;

import com.ebay.calculator.calculator.Operation;
import com.ebay.calculator.exceptions.InvalidOperationException;
import com.ebay.calculator.strategy.OperationStrategy;

import org.springframework.stereotype.Component;
import java.util.Map;


@Component
public class CalculatorService {

    private final Map<Operation, OperationStrategy> operationStrategies;
    private Number currentValue;

    public CalculatorService(Map<Operation, OperationStrategy> operationStrategies) {
    	this.operationStrategies = operationStrategies;
    }

    public Number calculate(Operation op, Number num1, Number num2) {
        OperationStrategy strategy = operationStrategies.get(op);
        if (strategy == null) {
            throw new InvalidOperationException(op);
        }
        return strategy.apply(num1, num2);
    }

    public CalculatorService start(Number initialValue) {
        if (initialValue == null) {
            throw new IllegalArgumentException("Initial value cannot be null.");
        }
        this.currentValue = initialValue;
        return this;
    }

    public CalculatorService perform(Operation op, Number num) {
        if (this.currentValue == null) {
            throw new IllegalStateException("Calculator not started. Call start() with an initial value.");
        }
        if (num == null) {
            throw new IllegalArgumentException("Operand cannot be null.");
        }
        this.currentValue = calculate(op, this.currentValue, num);
        return this; // Enable chaining
    }

    public Number getResult() {
        return this.currentValue;
    }
}