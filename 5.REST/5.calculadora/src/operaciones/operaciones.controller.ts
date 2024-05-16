import { Controller, Get, Post, Body, Patch, Param, Delete } from '@nestjs/common';
import { OperacionesService } from './operaciones.service';
import { CreateOperacioneDto } from './dto/create-operacione.dto';
import { UpdateOperacioneDto } from './dto/update-operacione.dto';

@Controller('operaciones')
export class OperacionesController {
  constructor(private readonly operacionesService: OperacionesService) {}

  @Post()
  create(@Body() createOperacioneDto: CreateOperacioneDto) {
    return this.operacionesService.create(createOperacioneDto);
  }

  @Get()
  findAll() {
    return this.operacionesService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.operacionesService.findOne(+id);
  }

  @Patch(':id')
  update(@Param('id') id: string, @Body() updateOperacioneDto: UpdateOperacioneDto) {
    return this.operacionesService.update(+id, updateOperacioneDto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.operacionesService.remove(+id);
  }
}
