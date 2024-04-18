package com.example.rba.model.enums;

public enum CardRequestStatus {
    ERROR,
    PENDING_PROCESSING,
    IN_PROGRESS,
    APPROVED,
    DECLINED,
    UNKNOWN;

    public static CardRequestStatus mapStringToValue(String value) {
        for (CardRequestStatus status : CardRequestStatus.values()) {
            if (status.toString().equals(value)) {
                return status;
            }
        }
        return UNKNOWN;
    }
}
