package vn.vela.sample.mongodb.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import vn.vela.sample.mongodb.dto.DeviceDto;
import vn.vela.sample.mongodb.entity.Device;
import vn.vela.sample.mongodb.response.ResponseWithPage;
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

  @ApiImplicitParams({
      @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
          value = "Results page you want to retrieve (0..N)", defaultValue = "0"),
      @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
          value = "Number of records per page.", defaultValue = "15"),
      @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string",
          paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "
          + "Default sort order is ascending. Multiple sort criteria are supported.",
          defaultValue = "createdAt,desc")})
  @GetMapping
  public ResponseWithPage<Device> getAll(@ApiIgnore Pageable pageable) {
    Page<Device> page = deviceService.getAll(pageable);
    return ResponseWithPage.<Device>builder().data(page.getContent()).pageIndex(page.getNumber())
        .totalElement(page.getTotalElements()).totalPage(page.getTotalPages()).build();
  }

}
