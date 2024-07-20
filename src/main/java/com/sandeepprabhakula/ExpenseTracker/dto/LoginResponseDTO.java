package com.sandeepprabhakula.ExpenseTracker.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginResponseDTO {
    private String jwtToken;
    private String uid;
    private String email;
    private String name;
}
