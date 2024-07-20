package com.sandeepprabhakula.ExpenseTracker.services;

import com.sandeepprabhakula.ExpenseTracker.data.Expense;
import com.sandeepprabhakula.ExpenseTracker.data.ExpenseDetail;
import com.sandeepprabhakula.ExpenseTracker.dto.GetExpenseDTO;
import org.springframework.http.ResponseEntity;

public interface ExpenseService {
    ResponseEntity<?> addNewExpense(Expense expense);
    ResponseEntity<?> deleteExpense(Expense expense);
    ResponseEntity<?> updateExpenseDetail(Expense expense);

    ResponseEntity<?> listAllExpensesByUID(String email);
    ResponseEntity<?> getOneExpense(GetExpenseDTO expenseDTO);
    ResponseEntity<?> getTotalSumOfExpensesOfaUser(String email);
}
