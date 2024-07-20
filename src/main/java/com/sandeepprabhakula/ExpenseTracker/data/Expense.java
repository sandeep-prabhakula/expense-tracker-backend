package com.sandeepprabhakula.ExpenseTracker.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document("expenses")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Expense {
    @Id
    private String id;
    private Date date;
    private List<ExpenseDetail> todayExpenses;
    private String uid;
    private double totalSum;
}
