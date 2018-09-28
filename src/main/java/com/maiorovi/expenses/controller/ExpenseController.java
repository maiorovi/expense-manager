package com.maiorovi.expenses.controller;

import com.maiorovi.expenses.domain.Expense;
import com.maiorovi.expenses.dto.ExpenseDto;
import com.maiorovi.expenses.mapper.ExpenseMapper;
import com.maiorovi.expenses.service.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ExpenseController {

    private static final Logger LOG = LoggerFactory.getLogger(ExpenseController.class);

    private ExpenseMapper expenseMapper;
    private ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService, ExpenseMapper expenseMapper) {
        this.expenseMapper = expenseMapper;
        this.expenseService = expenseService;
    }

    @GetMapping(path = "hello")
    public Mono<String> hello() {

        return Mono.just("hello there");
    }

    @PostMapping(path = "expenses", consumes = "application/json")
    public Mono<ResponseEntity<String>> expense(@RequestBody ExpenseDto expenseData) {
        LOG.info("Processing new expense");

        Expense expense = expenseMapper.toDomainObject(expenseData);

        return expenseService.processNewExpense(expense)
                            .map(id -> ResponseEntity.created(newURI("expenses/" + id)).build());

    }

    private URI newURI(String path) {
        final String httpAddress = "http://localhost:8080";

        try {
            return new URI(httpAddress + "/"+ path);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

//    @GetMapping(path = "expenses/{id}")
//    public Mono<ServerResponse> expenseById(@PathVariable("id") int id) {
//        Mono<Expense> expenseById = expenseService.getExpenseById(String.valueOf(id));
//        return ServerResponse.ok()
//                .body(expenseService::getExpenseById, ExpenseDto.class);
//    }

}
