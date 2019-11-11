package vn.vela.sample.mongodb.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class NewAddDto {

  private String title;
  private String content;
  private String author;
  private String alias;

}
