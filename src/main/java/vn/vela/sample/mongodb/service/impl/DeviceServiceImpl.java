package vn.vela.sample.mongodb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vela.sample.mongodb.dto.DeviceDto;
import vn.vela.sample.mongodb.entity.Device;
import vn.vela.sample.mongodb.repository.DeviceRepository;
import vn.vela.sample.mongodb.service.DeviceService;

@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

  @Autowired
  private DeviceRepository deviceRepository;

  @Override
  public Device add(DeviceDto deviceDto) {
    return deviceRepository
        .save(Device.builder().name(deviceDto.getName()).alias(deviceDto.getAlias())
            .producer(deviceDto.getProducer()).price(deviceDto.getPrice()).build());
  }

  @Override
  public Page<Device> getAll(Pageable pageable) {
    return deviceRepository.findAll(pageable);
  }
}
