package vn.vela.sample.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vela.sample.mongodb.entity.Device;

public interface DeviceRepository extends MongoRepository<Device, String> {

}
