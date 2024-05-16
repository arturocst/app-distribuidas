import { Module } from '@nestjs/common';
import { OperacionesService } from './operaciones.service';
import { OperacionesController } from './operaciones.controller';

@Module({
  controllers: [OperacionesController],
  providers: [OperacionesService],
})
export class OperacionesModule {}
