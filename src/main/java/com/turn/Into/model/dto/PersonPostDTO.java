package com.turn.Into.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.turn.Into.model.WRank;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PersonPostDTO(
        @NotBlank
        String name,
        @NotBlank
        @Size(min = 5, message = "too shoort")
        @Size(max = 10, message = "too long")
        String register,
        @NotNull
        @Positive
        Integer age,
        @NotNull
        @Positive
        BigDecimal weight,
        @NotBlank
        String image
) {
}
