package net.croz.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class JokeForm {
    @NotBlank
    private String content;
}
