package com.sandeepprabhakula.ExpenseTracker.services.serviceImpl;

import com.sandeepprabhakula.ExpenseTracker.data.Expense;
import com.sandeepprabhakula.ExpenseTracker.data.User;
import com.sandeepprabhakula.ExpenseTracker.dto.GetExpenseDTO;
import com.sandeepprabhakula.ExpenseTracker.repositories.ExpenseRepository;
import com.sandeepprabhakula.ExpenseTracker.repositories.UserRepository;
import com.sandeepprabhakula.ExpenseTracker.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    @Override
    public ResponseEntity<?> addNewExpense(Expense expense) {
        try {
            return new ResponseEntity<>(expenseRepository.save(expense), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> deleteExpense(Expense expense) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Expense> optionalExpense = expenseRepository.findById(expense.getId());
            if (optionalExpense.isEmpty()) {
                response.put("status", 400);
                response.put("message", "expense with id " + expense.getId() + " not found.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            expenseRepository.delete(expense);
            response.put("status", 200);
            response.put("message", "expense deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("status", 400);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> updateExpenseDetail(Expense expense) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Expense> optionalExpense = expenseRepository.findById(expense.getId());
            if (optionalExpense.isEmpty()) {
                response.put("status", 400);
                response.put("message", "expense with id " + expense.getId() + " not found.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            Expense existingExpense = optionalExpense.get();
            existingExpense.setTodayExpenses(expense.getTodayExpenses());
            existingExpense.setTotalSum(expense.getTotalSum());
            return new ResponseEntity<>(expenseRepository.save(existingExpense), HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", 400);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> listAllExpensesByUID(String id) {
        Map<String, Object> response = new HashMap<>();
        try{
            Optional<User> optionalUser = userRepository.findById(id);
            if(optionalUser.isEmpty()){
                response.put("status", 400);
                response.put("message", "user not found.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            else {

                List<Expense>allExpenses = expenseRepository.findAllByUid(id, Sort.by(Sort.Direction.DESC,"date"));
                return new ResponseEntity<>(allExpenses,HttpStatus.OK);
            }
        }catch (Exception e){
            response.put("status", 400);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getOneExpense(GetExpenseDTO expenseDTO) {
        Map<String, Object> response = new HashMap<>();
        try{
            Optional<Expense> optionalExpense = expenseRepository.findById(expenseDTO.getExpenseId());
            if(optionalExpense.isEmpty()){
                response.put("status", 400);
                response.put("message", "expense with id " + expenseDTO.getExpenseId() + " not found.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            Expense existingExpense = optionalExpense.get();
            if(!expenseDTO.getUid().equals(existingExpense.getUid())){
                response.put("status", 401);
                response.put("message", "Access denied.");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(existingExpense,HttpStatus.OK);
        }catch (Exception e){
            response.put("status", 400);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getTotalSumOfExpensesOfaUser(String id) {
        Map<String, Object> response = new HashMap<>();
        try{
            Optional<User> optionalUser = userRepository.findById(id);
            if(optionalUser.isEmpty()){
                response.put("status", 400);
                response.put("message", "user not found.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            else {

                List<Expense>allExpenses = expenseRepository.findAllByUid(id,Sort.by(Sort.Direction.DESC,"date"));
                double sum = 0;
                for(Expense e:allExpenses)sum+=e.getTotalSum();
                response.put("status", 200);
                response.put("message", "fetched data successfully.");
                response.put("data",sum);
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
        }catch (Exception e){
            response.put("status", 400);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
