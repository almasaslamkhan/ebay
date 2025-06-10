package com.example.calculator.service;

import com.ebay.calculator.calculator.Operation;
import com.ebay.calculator.controller.CalculationController;
import com.ebay.calculator.entity.Calculation;
import com.ebay.calculator.model.CalculationRequest;
import com.ebay.calculator.service.CalculationService;
import com.ebay.calculator.service.CalculatorService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class CalculationControllerTest {
    @Mock
    private CalculationService calculationService;

    @Mock
    private CalculatorService calculator;

    @InjectMocks
    private CalculationController calculationController;

    @Test
    public void testCreateCalculation() {
        CalculationRequest request = new CalculationRequest();
        request.setOperand1(10.0);
        request.setOperand2(5.0);
        request.setOperation(Operation.SUBTRACT);

        when(calculator.calculate(Operation.SUBTRACT, 10.0, 5.0)).thenReturn(5.0);

        Calculation calculation = new Calculation(10.0, 5.0, Operation.SUBTRACT, 5.0);
        when(calculationService.saveCalculation(any(Calculation.class))).thenReturn(calculation);

        ResponseEntity<Calculation> response = calculationController.createCalculation(request);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(5.0, response.getBody().getResult());
    }
}
