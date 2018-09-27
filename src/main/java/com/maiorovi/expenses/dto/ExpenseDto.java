package com.maiorovi.expenses.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ExpenseDto {

    private final String owner;
    private final LocalDate date;
    private final String description;

}
