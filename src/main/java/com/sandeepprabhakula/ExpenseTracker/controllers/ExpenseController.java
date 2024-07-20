package com.sandeepprabhakula.ExpenseTracker.controllers;

import com.sandeepprabhakula.ExpenseTracker.data.Expense;
import com.sandeepprabhakula.ExpenseTracker.dto.GetExpenseDTO;
import com.sandeepprabhakula.ExpenseTracker.services.serviceImpl.ExpenseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expenses")
@CrossOrigin("*")
public class ExpenseController {
    private final ExpenseServiceImpl expenseService;
    @PostMapping("/add-expense")
    public ResponseEntity<?> addExpense(@RequestBody Expense expense){
        try{
            return expenseService.addNewExpense(expense);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/update-expense-detail")
    public ResponseEntity<?> updateExpenseDetail(@RequestBody Expense expense){
        try {
            return expenseService.updateExpenseDetail(expense);
        }catch (Exception e){
            return  new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-all-expenses/{id}")
    public ResponseEntity<?> getAllExpensesByEmail(@PathVariable("id")String id){
        try{
            return expenseService.listAllExpensesByUID(id);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-one-expense")
    public ResponseEntity<?> getOneExpense(@RequestBody GetExpenseDTO expenseDTO){
        try{
            return expenseService.getOneExpense(expenseDTO);
        }catch (Exception e){
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-expense")
    public ResponseEntity<?> deleteExpense(@RequestBody Expense expense){
        try {
            return expenseService.deleteExpense(expense);
        }catch (Exception e){
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/total-expense/{id}")
    public ResponseEntity<?> getSum(@PathVariable String id){
        try {
            return expenseService.getTotalSumOfExpensesOfaUser(id);
        }catch (Exception e){
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }
}
