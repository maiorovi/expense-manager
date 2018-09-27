package com.maiorovi.expenses.repository;

import com.maiorovi.expenses.domain.Expense;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExpenseRepository {

     void save(Expense expense);

     Mono<Expense> findById(String id);

     Flux<Expense> findAll();
}
