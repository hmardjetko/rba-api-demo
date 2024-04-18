package com.example.rba.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

/**
 * NewCardRequest
 */
public class NewCardRequest   {
  @JsonProperty("firstName")
  @NotBlank(message = "First name is required")
  private String firstName;

  @JsonProperty("lastName")
  @NotBlank(message = "Last name is required")
  private String lastName;

  @JsonProperty("status")
  private String status;

  @JsonProperty("oib")
  @NotBlank(message = "OIB is required")
  private String oib;

  public NewCardRequest firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
  */
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public NewCardRequest lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
  */
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public NewCardRequest status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public NewCardRequest oib(String oib) {
    this.oib = oib;
    return this;
  }

  /**
   * Get oib
   * @return oib
  */
  public String getOib() {
    return oib;
  }

  public void setOib(String oib) {
    this.oib = oib;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewCardRequest newCardRequest = (NewCardRequest) o;
    return Objects.equals(this.firstName, newCardRequest.firstName) &&
        Objects.equals(this.lastName, newCardRequest.lastName) &&
        Objects.equals(this.status, newCardRequest.status) &&
        Objects.equals(this.oib, newCardRequest.oib);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, status, oib);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewCardRequest {\n");
    
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    oib: ").append(toIndentedString(oib)).append("\n");
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

