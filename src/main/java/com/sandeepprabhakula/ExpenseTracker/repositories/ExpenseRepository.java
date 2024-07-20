package com.sandeepprabhakula.ExpenseTracker.repositories;

import com.sandeepprabhakula.ExpenseTracker.data.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense,String> {
    List<Expense> findAllByUid(String email);
}
