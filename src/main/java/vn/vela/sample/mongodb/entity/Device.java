package vn.vela.sample.mongodb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Device implements Serializable {

  private String name;
  private String alias;
  private String producer;
  private BigDecimal price;

}
