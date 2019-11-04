package vn.vela.sample.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.vela.sample.mongodb.dto.DeviceDto;
import vn.vela.sample.mongodb.entity.Device;
import vn.vela.sample.mongodb.service.DeviceService;

@RestController
@RequestMapping("/api/v1/devices")
public class DeviceController {

  @Autowired
  private DeviceService deviceService;

  @PostMapping
  public Device add(@RequestBody DeviceDto deviceDto) {
    return deviceService.add(deviceDto);
  }

}
