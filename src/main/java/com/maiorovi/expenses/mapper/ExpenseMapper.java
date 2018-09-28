package com.maiorovi.expenses.mapper;

import com.maiorovi.expenses.domain.Expense;
import com.maiorovi.expenses.dto.ExpenseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ExpenseMapper implements Mapper<ExpenseDto, Expense> {

    private AtomicInteger counter = new AtomicInteger();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public ExpenseDto toDataTransferObject(Expense expense) {
        String formattedDate = expense.getDate().format(formatter);

        return ExpenseDto.builder()
                .owner(expense.getOwner())
                .date(formattedDate)
                .description(expense.getDescription())
                .build();
    }

    @Override
    public Expense toDomainObject(ExpenseDto expenseDto) {
        String date = expenseDto.getDate();
        LocalDate d = LocalDate.parse(date, formatter);

        return Expense.builder()
                .owner(expenseDto.getOwner())
                .date(d)
                .description(expenseDto.getDescription())
                .id(nextId())
                .build();
    }


    private String nextId() {
        return String.valueOf(counter.incrementAndGet());
    }
}
