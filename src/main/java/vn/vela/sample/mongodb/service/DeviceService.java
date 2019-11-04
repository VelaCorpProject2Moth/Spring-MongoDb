package vn.vela.sample.mongodb.service;

import vn.vela.sample.mongodb.dto.DeviceDto;
import vn.vela.sample.mongodb.entity.Device;

public interface DeviceService {

  Device add(DeviceDto deviceDto);
}
