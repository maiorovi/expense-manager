package com.maiorovi.expenses.repository;

import com.maiorovi.expenses.domain.Expense;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MapBasedExpenseRepository implements ExpenseRepository {

    private final Map<String, Expense> expenses = new ConcurrentHashMap<>();

    {
        expenses.put("1", Expense.builder()
                    .id("1")
                    .description("My Expense")
                    .date(LocalDate.of(2018, 9, 12))
                    .owner("yehor")
                    .build());
    }

    @Override
    public void save(Expense expense) {
        expenses.putIfAbsent(expense.getId(), expense);
    }

    @Override
    public Mono<Expense> findById(String id) {
        return Mono.justOrEmpty(expenses.get(id));
    }

    @Override
    public Flux<Expense> findAll() {
        return Flux.fromIterable(expenses.values());
    }
}
