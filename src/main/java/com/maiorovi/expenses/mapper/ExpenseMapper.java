package com.maiorovi.expenses.mapper;

import com.maiorovi.expenses.domain.Expense;
import com.maiorovi.expenses.dto.ExpenseDto;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ExpenseMapper implements Mapper<ExpenseDto, Expense> {

    private AtomicInteger counter = new AtomicInteger();

    @Override
    public ExpenseDto toDataTransferObject(Expense expense) {
        return null;
    }

    @Override
    public Expense toDomainObject(ExpenseDto expenseDto) {
        return Expense.builder()
                .owner(expenseDto.getOwner())
                .date(expenseDto.getDate())
                .description(expenseDto.getDescription())
                .id(nextId())
                .build();
    }


    private String nextId() {
        return String.valueOf(counter.incrementAndGet());
    }
}
