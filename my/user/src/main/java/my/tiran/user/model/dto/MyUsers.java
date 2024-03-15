package my.tiran.user.model.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import my.tiran.user.model.dto.MyUser;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * MyUsers
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class MyUsers {

  @Valid
  private List<@Valid MyUser> myUsers;

  public MyUsers myUsers(List<@Valid MyUser> myUsers) {
    this.myUsers = myUsers;
    return this;
  }

  public MyUsers addMyUsersItem(MyUser myUsersItem) {
    if (this.myUsers == null) {
      this.myUsers = new ArrayList<>();
    }
    this.myUsers.add(myUsersItem);
    return this;
  }

  /**
   * Get myUsers
   * @return myUsers
  */
  @Valid 
  @Schema(name = "myUsers", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("myUsers")
  public List<@Valid MyUser> getMyUsers() {
    return myUsers;
  }

  public void setMyUsers(List<@Valid MyUser> myUsers) {
    this.myUsers = myUsers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MyUsers myUsers = (MyUsers) o;
    return Objects.equals(this.myUsers, myUsers.myUsers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(myUsers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MyUsers {\n");
    sb.append("    myUsers: ").append(toIndentedString(myUsers)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

