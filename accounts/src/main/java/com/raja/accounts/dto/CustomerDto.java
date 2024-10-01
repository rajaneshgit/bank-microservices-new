package com.raja.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {
    @Schema(
            description = "Name of the customer",
            example = "John"
    )
    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 5, max = 30, message = "Name must be between 5 and 30 characters")
    private String name;
    @Schema(
            description = "Email of the customer",
            example = "aU0Lj@example.com"
    )
    @NotEmpty(message = "Email can not be a null or empty")
    @Email(message = "Email must be a valid email address")
    private String email;
    @Schema(
            description = "Mobile number of the customer",
            example = "1234567890"
    )
    @NotEmpty(message = "MobileNumber can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "MobileNumber must be 10 digits")
    private String mobileNumber;
    @Schema(
            description = "Account information of the Customer"
    )
    private AccountsDto accountsDto;

}
