package my.tiran.ctable.model.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ResponseReserve
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ResponseReserve {

  private String message;

  private Integer prepareTableNumber;

  public ResponseReserve message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  */
  
  @Schema(name = "message", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ResponseReserve prepareTableNumber(Integer prepareTableNumber) {
    this.prepareTableNumber = prepareTableNumber;
    return this;
  }

  /**
   * Get prepareTableNumber
   * @return prepareTableNumber
  */
  
  @Schema(name = "prepareTableNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prepareTableNumber")
  public Integer getPrepareTableNumber() {
    return prepareTableNumber;
  }

  public void setPrepareTableNumber(Integer prepareTableNumber) {
    this.prepareTableNumber = prepareTableNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResponseReserve responseReserve = (ResponseReserve) o;
    return Objects.equals(this.message, responseReserve.message) &&
        Objects.equals(this.prepareTableNumber, responseReserve.prepareTableNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, prepareTableNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseReserve {\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    prepareTableNumber: ").append(toIndentedString(prepareTableNumber)).append("\n");
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

