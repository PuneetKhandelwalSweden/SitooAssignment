package com.sitoo.warehouse.models.json;

import java.util.Objects;

public class ErrorResponse {
    private long errorCode;
    private String errorText;

    public ErrorResponse(long errorCode, String errorText) {
        this.errorCode = errorCode;
        this.errorText = errorText;
    }

    public ErrorResponse() {
    }

    public long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(long errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorResponse that = (ErrorResponse) o;
        return errorCode == that.errorCode && Objects.equals(errorText, that.errorText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorCode, errorText);
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "errorCode=" + errorCode +
                ", errorText='" + errorText + '\'' +
                '}';
    }
}
