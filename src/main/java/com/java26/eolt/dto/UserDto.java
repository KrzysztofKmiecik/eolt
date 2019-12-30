package com.java26.eolt.dto;

import com.java26.eolt.myEnum.EoltRole;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    private Long id;

    @NotBlank(message = "{form.validation.NotBlank.message}")
    @Size(min = 2, max = 255, message = "{form.validation.Size.message}")
    private String firstName;

    @NotBlank(message = "{form.validation.NotBlank.message}")
    @Size(min = 4, max = 255, message = "{form.validation.Size.message}")
    private String username;

    @NotBlank(message = "{form.validation.NotBlank.message}")
    @Size(min = 6, max = 255, message = "{form.validation.Size.message}")
    private String password;

    private EoltRole role;
}
