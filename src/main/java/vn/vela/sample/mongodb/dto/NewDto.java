package vn.vela.sample.mongodb.dto;

import java.time.LocalDateTime;
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
public class NewDto {

  @Id
  private String id;
  private String title;
  private String content;
  private String author;
  private String alias;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String md5;

}
