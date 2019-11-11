package vn.vela.sample.mongodb.service;

import vn.vela.sample.mongodb.dto.NewAddDto;
import vn.vela.sample.mongodb.dto.NewDto;

public interface NewService {

  NewDto addNewService(NewAddDto newAddDto);
}
