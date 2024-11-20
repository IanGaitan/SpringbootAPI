package com.menumasterpro.menumasterpro.Common;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class CustomException extends RuntimeException {

    private String timestamp;
    private Integer status;
    private String error;
    private String motive;

    public CustomException(int status, String motive) {
        super(motive);
        this.timestamp = LocalDate.now().toString();
        this.status = status;
        this.error = HttpStatus.valueOf(status).getReasonPhrase();
        this.motive = motive;
    }

    public Integer getStatus() {
        return this.status != null ? this.status : 500;
    }

    @Override
    public String toString() {
        return "{" +
                "\"timestamp\": \"" + timestamp + "\"," +
                "\"status\": " + status + "," +
                "\"error\": \"" + error + "\"," +
                "\"motive\": \"" + motive + "\"" +
                "}";
    }
}
