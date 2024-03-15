package my.tiran.user.model.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import my.tiran.user.model.dto.ValidateError;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * DefaultError
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class DefaultError {

  private String message;

  private String stackTrace;

  @Valid
  private List<@Valid ValidateError> errors;

  public DefaultError message(String message) {
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

  public DefaultError stackTrace(String stackTrace) {
    this.stackTrace = stackTrace;
    return this;
  }

  /**
   * Get stackTrace
   * @return stackTrace
  */
  
  @Schema(name = "stackTrace", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("stackTrace")
  public String getStackTrace() {
    return stackTrace;
  }

  public void setStackTrace(String stackTrace) {
    this.stackTrace = stackTrace;
  }

  public DefaultError errors(List<@Valid ValidateError> errors) {
    this.errors = errors;
    return this;
  }

  public DefaultError addErrorsItem(ValidateError errorsItem) {
    if (this.errors == null) {
      this.errors = new ArrayList<>();
    }
    this.errors.add(errorsItem);
    return this;
  }

  /**
   * Get errors
   * @return errors
  */
  @Valid 
  @Schema(name = "errors", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("errors")
  public List<@Valid ValidateError> getErrors() {
    return errors;
  }

  public void setErrors(List<@Valid ValidateError> errors) {
    this.errors = errors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DefaultError defaultError = (DefaultError) o;
    return Objects.equals(this.message, defaultError.message) &&
        Objects.equals(this.stackTrace, defaultError.stackTrace) &&
        Objects.equals(this.errors, defaultError.errors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, stackTrace, errors);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DefaultError {\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    stackTrace: ").append(toIndentedString(stackTrace)).append("\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
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

