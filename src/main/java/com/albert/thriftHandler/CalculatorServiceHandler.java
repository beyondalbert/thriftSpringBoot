package com.albert.thriftHandler;

import com.albert.service.CalculatorService;
import com.albert.thrift.TCalculatorService;
import com.albert.thrift.TDivisionByZeroException;
import com.albert.thrift.TOperation;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by albert on 16-7-26.
 */

@Component
public class CalculatorServiceHandler implements TCalculatorService.Iface {

    @Autowired
    CalculatorService calculatorService;

    @Override
    public int calculate(int num1, int num2, TOperation op) throws TException {
        switch (op) {
            case ADD:
                return calculatorService.add(num1, num2);
            case SUBTRACT:
                return calculatorService.subtract(num1, num2);
            case MULTIPLY:
                return calculatorService.multiply(num1, num2);
            case DIVIDE:
                try {
                    return calculatorService.divide(num1, num2);
                } catch (IllegalArgumentException e) {
                    throw new TDivisionByZeroException();
                }
            default:
                 throw new TException("Unknown operation " + op);
        }
    }
}
