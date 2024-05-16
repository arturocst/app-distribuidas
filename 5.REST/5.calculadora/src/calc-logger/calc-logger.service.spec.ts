import { Test, TestingModule } from '@nestjs/testing';
import { CalcLoggerService } from './calc-logger.service';

describe('CalcLoggerService', () => {
  let service: CalcLoggerService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [CalcLoggerService],
    }).compile();

    service = module.get<CalcLoggerService>(CalcLoggerService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
