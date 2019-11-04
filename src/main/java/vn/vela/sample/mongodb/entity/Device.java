package vn.vela.sample.mongodb.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Device {

  @Id
  private String id;
  private String name;
  private String alias;
  private String producer;
  private BigDecimal price;

}
