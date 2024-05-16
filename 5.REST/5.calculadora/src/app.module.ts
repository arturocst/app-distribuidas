import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { OperacionesModule } from './operaciones/operaciones.module';
import { CalculadoraController } from './calculadora/calculadora.controller';
import { CalcLoggerService } from './calc-logger/calc-logger.service';

@Module({
  imports: [OperacionesModule],
  controllers: [AppController, CalculadoraController],
  providers: [AppService, CalcLoggerService],
})
export class AppModule {}
