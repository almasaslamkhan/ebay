package com.ebay.calculator.controller;

import com.ebay.calculator.model.CalculationRequest;
import com.ebay.calculator.model.ChainRequest;
import com.ebay.calculator.model.Response;
import com.ebay.calculator.service.CalculatorService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/v1/calculator")
public class CalculatorController {
    private final CalculatorService calculator;

    public CalculatorController(CalculatorService calculator) {
        this.calculator = calculator;
    }

     
    @GetMapping(path = "/calculate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> calculate(
    		@RequestBody CalculationRequest calculateRequest) {
        
    	Number result = calculator.calculate(calculateRequest.getOperation(), calculateRequest.getOperand1(), calculateRequest.getOperand2());
        Response res =  Response.builder().code("200").message("Success").data(result).build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }

    @GetMapping(path = "/chain" ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> chainOperations(@RequestBody ChainRequest request) {
        calculator.start(request.getInitialValue());
        request.getOperations().forEach(op -> 
            calculator.perform(op.getOperation(), op.getOperand())
        );
       
        Number result = calculator.getResult();
        Response res = new Response.Builder().code("200").data(result).message("Success").build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }
}
