package com.raja.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
public class ErrorResponseDto {
    @Schema(
            description = "Api Path invoked by Client"
    )
    private String apiPath;
    @Schema(
            description = "Error Code representing the type of error"
    )
    private HttpStatus errorCode;
    @Schema(
            description = "Error Message describing the error"
    )
    private String errorMessage;
    @Schema(
            description = "Time when the error occurred"
    )
    private LocalDateTime errorTime;


}
