package com.example.rba.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ErrorResponse
 */

public class ErrorResponse   {
  @JsonProperty("code")
  private String code;

  @JsonProperty("id")
  private String id;

  @JsonProperty("description")
  private String description;

  public ErrorResponse code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Optional error code for reporting purposes
   * @return code
  */
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ErrorResponse id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier for audit purposes
   * @return id
  */
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ErrorResponse description(String description) {
    this.description = description;
    return this;
  }

  /**
   * The description of the error
   * @return description
  */
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorResponse errorResponse = (ErrorResponse) o;
    return Objects.equals(this.code, errorResponse.code) &&
        Objects.equals(this.id, errorResponse.id) &&
        Objects.equals(this.description, errorResponse.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, id, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorResponse {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

