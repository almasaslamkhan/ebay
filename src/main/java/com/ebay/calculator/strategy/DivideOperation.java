package com.ebay.calculator.strategy;

import org.springframework.stereotype.Component;

import com.ebay.calculator.exceptions.DivisionByZeroException;

@Component
public class DivideOperation implements OperationStrategy {
    @Override
    public Number apply(Number num1, Number num2) {
        if (num2.doubleValue() == 0) {
            throw new DivisionByZeroException();
        }
        return num1.doubleValue() / num2.doubleValue();
    }
}
