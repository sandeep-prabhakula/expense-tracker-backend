package com.sandeepprabhakula.ExpenseTracker.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
@Data
@Getter
@Setter
public class User {
    @Id
    private String id;
    private String email;
    private String name;
    private String password;
    private double salaryPerMonth;
}
