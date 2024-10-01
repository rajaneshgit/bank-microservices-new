package com.raja.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "Response",
        description = "Schema to hold successfull response information"
)
@Data
@AllArgsConstructor
public class ResponseDto {
    @Schema(
            description = "Http Status Code in the response"
    )
    private String statusCode;
    @Schema(
            description = "Http Status Message in the response"
    )
    private String statusMessage;
}
