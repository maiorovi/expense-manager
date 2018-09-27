package com.maiorovi.expenses.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Expense {
    private String id;
    private String owner;
    private LocalDate date;
    private String description;

}
