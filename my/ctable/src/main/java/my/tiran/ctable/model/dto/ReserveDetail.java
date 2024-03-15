package my.tiran.ctable.model.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ReserveDetail
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ReserveDetail {

  private String reserveName;

  private String mobileNo;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate reserveDate;

  private String reserveTime;

  private String exitTime;

  private Integer numberCustomer;

  public ReserveDetail reserveName(String reserveName) {
    this.reserveName = reserveName;
    return this;
  }

  /**
   * Get reserveName
   * @return reserveName
  */
  
  @Schema(name = "reserveName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("reserveName")
  public String getReserveName() {
    return reserveName;
  }

  public void setReserveName(String reserveName) {
    this.reserveName = reserveName;
  }

  public ReserveDetail mobileNo(String mobileNo) {
    this.mobileNo = mobileNo;
    return this;
  }

  /**
   * Get mobileNo
   * @return mobileNo
  */
  
  @Schema(name = "mobileNo", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("mobileNo")
  public String getMobileNo() {
    return mobileNo;
  }

  public void setMobileNo(String mobileNo) {
    this.mobileNo = mobileNo;
  }

  public ReserveDetail reserveDate(LocalDate reserveDate) {
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

  public ReserveDetail reserveTime(String reserveTime) {
    this.reserveTime = reserveTime;
    return this;
  }

  /**
   * Get reserveTime
   * @return reserveTime
  */
  @Pattern(regexp = "^\\d{2}:\\d{2}") 
  @Schema(name = "reserveTime", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("reserveTime")
  public String getReserveTime() {
    return reserveTime;
  }

  public void setReserveTime(String reserveTime) {
    this.reserveTime = reserveTime;
  }

  public ReserveDetail exitTime(String exitTime) {
    this.exitTime = exitTime;
    return this;
  }

  /**
   * Get exitTime
   * @return exitTime
  */
  @Pattern(regexp = "^\\d{2}:\\d{2}") 
  @Schema(name = "exitTime", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("exitTime")
  public String getExitTime() {
    return exitTime;
  }

  public void setExitTime(String exitTime) {
    this.exitTime = exitTime;
  }

  public ReserveDetail numberCustomer(Integer numberCustomer) {
    this.numberCustomer = numberCustomer;
    return this;
  }

  /**
   * Get numberCustomer
   * minimum: 1
   * @return numberCustomer
  */
  @Min(1) 
  @Schema(name = "numberCustomer", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("numberCustomer")
  public Integer getNumberCustomer() {
    return numberCustomer;
  }

  public void setNumberCustomer(Integer numberCustomer) {
    this.numberCustomer = numberCustomer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReserveDetail reserveDetail = (ReserveDetail) o;
    return Objects.equals(this.reserveName, reserveDetail.reserveName) &&
        Objects.equals(this.mobileNo, reserveDetail.mobileNo) &&
        Objects.equals(this.reserveDate, reserveDetail.reserveDate) &&
        Objects.equals(this.reserveTime, reserveDetail.reserveTime) &&
        Objects.equals(this.exitTime, reserveDetail.exitTime) &&
        Objects.equals(this.numberCustomer, reserveDetail.numberCustomer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reserveName, mobileNo, reserveDate, reserveTime, exitTime, numberCustomer);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReserveDetail {\n");
    sb.append("    reserveName: ").append(toIndentedString(reserveName)).append("\n");
    sb.append("    mobileNo: ").append(toIndentedString(mobileNo)).append("\n");
    sb.append("    reserveDate: ").append(toIndentedString(reserveDate)).append("\n");
    sb.append("    reserveTime: ").append(toIndentedString(reserveTime)).append("\n");
    sb.append("    exitTime: ").append(toIndentedString(exitTime)).append("\n");
    sb.append("    numberCustomer: ").append(toIndentedString(numberCustomer)).append("\n");
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

