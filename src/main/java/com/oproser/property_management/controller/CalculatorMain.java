package com.oproser.property_management.controller;


public class CalculatorMain {
    public static void main(String[] args) {
        CalculatorController calculatorController = new CalculatorController();
        Double result = calculatorController.add(10.0, 20.0);
        System.out.println(result);
    }
}
