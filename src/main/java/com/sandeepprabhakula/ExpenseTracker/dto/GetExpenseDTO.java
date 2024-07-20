package com.sandeepprabhakula.ExpenseTracker.dto;

import lombok.*;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class GetExpenseDTO {
    private String uid;
    private String expenseId;
}
