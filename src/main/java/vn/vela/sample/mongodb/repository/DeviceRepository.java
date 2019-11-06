package vn.vela.sample.mongodb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.vela.sample.mongodb.entity.Device;

@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {

  Page<Device> findAll(Pageable pageable);

}
