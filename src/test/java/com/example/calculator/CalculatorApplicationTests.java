package com.example.calculator;

import com.ebay.calculator.calculator.Operation;
import com.ebay.calculator.exceptions.DivisionByZeroException;
import com.ebay.calculator.exceptions.InvalidOperationException;
import com.ebay.calculator.service.CalculatorService;
import com.ebay.calculator.strategy.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.util.EnumMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalculatorApplicationTests {

	private CalculatorService calculator;

	@BeforeEach
	public void setup() {
		Map<Operation, OperationStrategy> strategies = new EnumMap<>(Operation.class);
		strategies.put(Operation.ADD, new AddOperation());
		strategies.put(Operation.SUBTRACT, new SubtractOperation());
		strategies.put(Operation.MULTIPLY, new MultiplyOperation());
		strategies.put(Operation.DIVIDE, new DivideOperation());
		calculator = new CalculatorService(strategies);
	}

	@Test
	public void testAddition() {
		Number result = calculator.calculate(Operation.ADD, 2, 3);
		assertEquals(5.0, result.doubleValue(), 0.001);
	}

	@Test
	public void testDivisionByZero() {
		Exception exception = assertThrows(DivisionByZeroException.class, () -> {
			calculator.calculate(Operation.DIVIDE, 10, 0);
		});
		assertEquals("Cannot divide by zero.", exception.getMessage());
	}

	@Test
	public void testUnsupportedOperation() {
		Exception exception = assertThrows(InvalidOperationException.class, () -> {
			calculator.calculate(null, 1, 1);
		});
		assertEquals("Operation not supported: null", exception.getMessage());
	}

	@Test
	public void testNullInitialValue() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			calculator.start(null);
		});
		assertEquals("Initial value cannot be null.", exception.getMessage());
	}

	@Test
	public void testNullOperandInPerform() {
		calculator.start(5);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			calculator.perform(Operation.ADD, null);
		});
		assertEquals("Operand cannot be null.", exception.getMessage());
	}

	@Test
	public void testSubtraction() {
		Number result = calculator.calculate(Operation.SUBTRACT, 5, 2);
		assertEquals(3.0, result.doubleValue(), 0.001);
	}

	@Test
	public void testMultiplication() {
		Number result = calculator.calculate(Operation.MULTIPLY, 4, 2.5);
		assertEquals(10.0, result.doubleValue(), 0.001);
	}

	@Test
	public void testDivision() {
		Number result = calculator.calculate(Operation.DIVIDE, 10, 2);
		assertEquals(5.0, result.doubleValue(), 0.001);
	}

	@Test
	public void testChainingOperations() {
		Number result = calculator.start(5)
				.perform(Operation.ADD, 3)
				.perform(Operation.MULTIPLY, 2)
				.getResult();
		assertEquals(16.0, result.doubleValue(), 0.001);
	}

	@Test
	public void testChainingWithoutStart() {
		assertThrows(IllegalStateException.class, () -> {
			calculator.perform(Operation.ADD, 3);
		});
	}
}
