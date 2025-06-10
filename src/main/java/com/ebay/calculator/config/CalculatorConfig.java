package com.ebay.calculator.config;

import com.ebay.calculator.calculator.Operation;
import com.ebay.calculator.strategy.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.Map;

@Configuration
public class CalculatorConfig {

    @Bean
    public Map<Operation, OperationStrategy> operationStrategies(
            AddOperation addOperation,
            SubtractOperation subtractOperation,
            MultiplyOperation multiplyOperation,
            DivideOperation divideOperation
            // Add new operations here
    ) {
        Map<Operation, OperationStrategy> strategies = new EnumMap<>(Operation.class);
        strategies.put(Operation.ADD, addOperation);
        strategies.put(Operation.SUBTRACT, subtractOperation);
        strategies.put(Operation.MULTIPLY, multiplyOperation);
        strategies.put(Operation.DIVIDE, divideOperation);
        // Add new operations here
        return strategies;
    }
}
