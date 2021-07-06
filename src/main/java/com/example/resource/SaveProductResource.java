package com.example.resource;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SaveProductResource {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String date;

    @NotNull
    @NotBlank
    private String action;
}
