package org.aws.demo.exceptionpkg;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Builder
@Data
public class ExceptionResponse {
    private String path;
    private HttpStatus httpStatus;
    private Date timestamp;
    private String message;
}
