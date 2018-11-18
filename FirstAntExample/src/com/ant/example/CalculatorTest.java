package com.ant.example;

public class CalculatorTest {

	public static void main(String[] args) {

		Calculator calc = new Calculator();
		System.out.println(calc.add(15, 20));
		System.out.println(calc.subtract(15, 20));
		System.out.println(calc.multiply(15, 20));
		System.out.println(calc.divide(15, 20));
	}

}
