package com.albert.service;

import org.springframework.stereotype.Component;

/**
 * Created by albert on 16-7-26.
 */

@Component
public class CalculatorService {
    public int add(int num1, int num2) {
        return num1 + num2;
    }

    public int subtract(int num1, int num2) {
        return num1 - num2;
    }

    public int multiply(int num1, int num2) {
        return num1 * num2;
    }

    public int divide(int num1, int num2) {
        if (num2 == 0) {
            throw new IllegalArgumentException("num2 must not be zero!");
        }

        return num1 / num2;
    }
}
