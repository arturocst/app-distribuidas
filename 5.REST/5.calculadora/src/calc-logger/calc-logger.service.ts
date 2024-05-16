import { Injectable } from '@nestjs/common';
import * as fs from 'fs';

@Injectable()
export class CalcLoggerService {
    private readonly logFilePath = 'calc-operations.log';

  logOperation(operation: string, result: number): void {
    const logMessage = `${operation} = ${result}\n`;
    fs.appendFileSync(this.logFilePath, logMessage);
  }
}
