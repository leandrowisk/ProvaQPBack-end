package com.qpLeandro.QPProva.DTO;
import java.util.List;


public record NationalizeDTO(
        String name,
        List<Country> country
) {
    public record Country(
            String country_id,
            double probability
    ) {}
}