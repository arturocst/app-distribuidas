import { Injectable } from '@nestjs/common';
import { CreateOperacioneDto } from './dto/create-operacione.dto';
import { UpdateOperacioneDto } from './dto/update-operacione.dto';

@Injectable()
export class OperacionesService {
  create(createOperacioneDto: CreateOperacioneDto) {
    return 'This action adds a new operacione';
  }

  findAll() {
    return `This action returns all operaciones`;
  }

  findOne(id: number) {
    return `This action returns a #${id} operacione`;
  }

  update(id: number, updateOperacioneDto: UpdateOperacioneDto) {
    return `This action updates a #${id} operacione`;
  }

  remove(id: number) {
    return `This action removes a #${id} operacione`;
  }
}
