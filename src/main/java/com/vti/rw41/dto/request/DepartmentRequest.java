package com.vti.rw41.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DepartmentRequest {

    @Length(min = 5, max = 15)
    @NotNull
    @NotBlank
    private String departmentName;

}
