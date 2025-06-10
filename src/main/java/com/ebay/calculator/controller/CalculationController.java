package com.ebay.calculator.controller;

import com.ebay.calculator.entity.Calculation;
import com.ebay.calculator.model.CalculationRequest;
import com.ebay.calculator.service.CalculationService;
import com.ebay.calculator.service.CalculatorService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/calculations")
public class CalculationController {
    private final CalculationService calculationService;
    private final CalculatorService calculator;

    public CalculationController(CalculationService calculationService, CalculatorService calculator) {
        this.calculationService = calculationService;
        this.calculator = calculator;
    }

    // Create a new calculation
    @PostMapping
    public ResponseEntity<Calculation> createCalculation(@RequestBody CalculationRequest request) {
        Double result = calculator.calculate(request.getOperation(), request.getOperand1(), request.getOperand2())
                .doubleValue();
        Calculation calculation = new Calculation(
                request.getOperand1(),
                request.getOperand2(),
                request.getOperation(),
                result
        );
        Calculation savedCalculation = calculationService.saveCalculation(calculation);
        return ResponseEntity.ok(savedCalculation);
    }

    // Read all calculations
    @GetMapping
    public ResponseEntity<List<Calculation>> getAllCalculations() {
        return ResponseEntity.ok(calculationService.getAllCalculations());
    }

    // Read a calculation by ID
    @GetMapping("/{id}")
    public ResponseEntity<Calculation> getCalculationById(@PathVariable Long id) {
        Optional<Calculation> calculation = Optional.ofNullable(calculationService.getCalculationById(id));
        return calculation.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Update a calculation
    @PutMapping("/{id}")
    public ResponseEntity<Calculation> updateCalculation(
            @PathVariable Long id,
            @RequestBody CalculationRequest request) {
        Double result = calculator.calculate(request.getOperation(), request.getOperand1(), request.getOperand2())
                .doubleValue();
        Calculation calculation = new Calculation(
                request.getOperand1(),
                request.getOperand2(),
                request.getOperation(),
                result
        );
        Calculation updatedCalculation = calculationService.updateCalculation(id, calculation);
        return ResponseEntity.ok(updatedCalculation);
    }

    // Delete a calculation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalculation(@PathVariable Long id) {
        calculationService.deleteCalculation(id);
        return ResponseEntity.noContent().build();
    }
}
