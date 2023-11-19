package com.turn.Into.model.dto;

import com.turn.Into.model.Person;
import com.turn.Into.model.WRank;

import java.math.BigDecimal;

public record PersonResponseDTO(
        Long id,
        String name,
        String register,
        Integer age,
        BigDecimal weight,
        WRank wRank,
        String image
) {
    public PersonResponseDTO(Person response) {
        this(response.getId(),
                response.getName(),
                response.getRegister(),
                response.getAge(),
                response.getWeight(),
                response.getWRank(),
                response.getImage());
    }
}
