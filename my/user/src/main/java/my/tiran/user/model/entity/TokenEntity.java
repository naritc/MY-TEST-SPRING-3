package my.tiran.user.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;
import my.tiran.user.common.constant.TokenType;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TokenEntity {

  @Id
  @GeneratedValue
  @Column(name = "token_id")
  public Long tokenId;

  @Column(name = "token", unique = true)
  public String token;

  @Enumerated(EnumType.STRING)
  @Column(name = "token_type")
  public TokenType tokenType;

  @Column(name = "created_datetime")
  public LocalDateTime createdDatetime;

  @Column(name = "revoked")
  public boolean revoked;

  @Column(name = "expired")
  public boolean expired;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "my_user_id")
  public MyUserEntity myUser;
}
