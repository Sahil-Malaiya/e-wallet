package com.swiftpay.e_wallet.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String fullName;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
}
