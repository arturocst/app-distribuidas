import { PartialType } from '@nestjs/mapped-types';
import { CreateOperacioneDto } from './create-operacione.dto';

export class UpdateOperacioneDto extends PartialType(CreateOperacioneDto) {}
