package com.example.rba.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class CardRequestStatusUpdateRequest {
    @JsonProperty("requestId")
    @NotBlank(message = "requestId is required")
    private String requestId;
    @JsonProperty("status")
    @NotBlank(message = "Status is required")
    private String status;
    private String description;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardRequestStatusUpdateRequest that = (CardRequestStatusUpdateRequest) o;
        return Objects.equals(requestId, that.requestId) && Objects.equals(status, that.status) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, status, description);
    }

    @Override
    public String toString() {
        return "CardRequestStatusUpdateRequest{" +
                "requestId='" + requestId + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
