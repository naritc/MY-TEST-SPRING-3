package my.tiran.ctable.model.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import my.tiran.ctable.model.dto.ReserveDetail;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Reserve
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class Reserve {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate reserveDate;

  @Valid
  private List<@Valid ReserveDetail> reserveDetails;

  public Reserve reserveDate(LocalDate reserveDate) {
    this.reserveDate = reserveDate;
    return this;
  }

  /**
   * Get reserveDate
   * @return reserveDate
  */
  @Valid 
  @Schema(name = "reserveDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("reserveDate")
  public LocalDate getReserveDate() {
    return reserveDate;
  }

  public void setReserveDate(LocalDate reserveDate) {
    this.reserveDate = reserveDate;
  }

  public Reserve reserveDetails(List<@Valid ReserveDetail> reserveDetails) {
    this.reserveDetails = reserveDetails;
    return this;
  }

  public Reserve addReserveDetailsItem(ReserveDetail reserveDetailsItem) {
    if (this.reserveDetails == null) {
      this.reserveDetails = new ArrayList<>();
    }
    this.reserveDetails.add(reserveDetailsItem);
    return this;
  }

  /**
   * Get reserveDetails
   * @return reserveDetails
  */
  @Valid 
  @Schema(name = "reserveDetails", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("reserveDetails")
  public List<@Valid ReserveDetail> getReserveDetails() {
    return reserveDetails;
  }

  public void setReserveDetails(List<@Valid ReserveDetail> reserveDetails) {
    this.reserveDetails = reserveDetails;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Reserve reserve = (Reserve) o;
    return Objects.equals(this.reserveDate, reserve.reserveDate) &&
        Objects.equals(this.reserveDetails, reserve.reserveDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reserveDate, reserveDetails);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Reserve {\n");
    sb.append("    reserveDate: ").append(toIndentedString(reserveDate)).append("\n");
    sb.append("    reserveDetails: ").append(toIndentedString(reserveDetails)).append("\n");
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

