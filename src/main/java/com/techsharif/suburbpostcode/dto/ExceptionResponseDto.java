package com.techsharif.suburbpostcode.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponseDto {
    private HttpStatus status;
    private Object reason;

    public void set(HttpStatus status, Object reason) {
        this.status = status;
        this.reason = reason;
    }
}
