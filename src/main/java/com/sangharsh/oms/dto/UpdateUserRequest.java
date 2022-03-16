package com.sangharsh.oms.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class UpdateUserRequest {

    @NotBlank
    private String name;
    private Set<String> authorities;

}
