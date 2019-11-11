package vn.vela.sample.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.vela.sample.mongodb.dto.NewAddDto;
import vn.vela.sample.mongodb.dto.NewDto;
import vn.vela.sample.mongodb.dto.NewEditDto;
import vn.vela.sample.mongodb.service.NewService;

@RestController
@RequestMapping("/api/v1/news")
public class NewController {

  @Autowired
  private NewService newService;

  @PostMapping
  public NewDto add(@RequestBody NewAddDto newAddDto) {
    return newService.addNewService(newAddDto);
  }

  @PutMapping
  public NewDto edit(@RequestBody NewEditDto newEditDto) {
    return new NewDto();
  }

}
