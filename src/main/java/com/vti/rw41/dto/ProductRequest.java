package com.vti.rw41.dto;

import com.vti.rw41.validation.Password;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class ProductRequest {

    @NotNull
//    @NotBlank
    @Email
    private String email;

    @NotNull(message = "Bạn phải nhập tên")
    @Length(min = 5, max = 12)
    private String name;

    @NotNull
    @Positive(message = "Phải lớn hơn 0")
    private Double price;

    @Password
    private String password;

}
