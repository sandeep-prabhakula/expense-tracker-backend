package com.sandeepprabhakula.ExpenseTracker.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ExpenseDetail {
    private double price;
    private ExpenseType expenseType;
}
