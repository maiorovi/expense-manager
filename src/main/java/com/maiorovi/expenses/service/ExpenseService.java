package com.maiorovi.expenses.service;

import com.maiorovi.expenses.domain.Expense;
import com.maiorovi.expenses.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ExpenseService {

    private ExpenseRepository expenseRepository;

    public ExpenseService(@Autowired ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void processNewExpense(Expense expense) {
        expenseRepository.save(expense);
    }


    public Mono<Expense> getExpenseById(String id) {
        return expenseRepository.findById(id);
    }

}
