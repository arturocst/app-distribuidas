import { Controller, Get, Param } from '@nestjs/common';
import { CalcLoggerService } from 'src/calc-logger/calc-logger.service';

@Controller('calculadora')
export class CalculadoraController {
    
    constructor(private readonly calcLoggerService: CalcLoggerService) {}

    @Get(':num1/:operator/:num2')
    calculate(  @Param('num1') num1: string, 
                @Param('operator') operator: string, 
                @Param('num2') num2: string): string 
    {
        const num1Value = parseFloat(num1);
        const num2Value = parseFloat(num2);
    
        if (isNaN(num1Value) || isNaN(num2Value)) {
            return 'Error: Invalid number';
        }
    
        let result: number;
        switch (operator) {
        case 'add':
            result = num1Value + num2Value;
            break;
        case 'subtract':
            result = num1Value - num2Value;
            break;
        case 'multiply':
            result = num1Value * num2Value;
            break;
        case 'divide':
            if (num2Value === 0) {
            return 'Error: Division by zero';
            }
            result = num1Value / num2Value;
            break;
        default:
            return 'Error: Invalid operator';
        }


        const operation = `${num1} ${operator} ${num2}`;
        this.calcLoggerService.logOperation(operation, result);

        return `Result: ${result}`;
    }
}

