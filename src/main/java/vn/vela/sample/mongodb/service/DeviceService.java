package vn.vela.sample.mongodb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.vela.sample.mongodb.dto.DeviceDto;
import vn.vela.sample.mongodb.entity.Device;

public interface DeviceService {

  Device add(DeviceDto deviceDto);

  Page<Device> getAll(Pageable pageable);

  Device edit(DeviceDto deviceDto, String id);
}
