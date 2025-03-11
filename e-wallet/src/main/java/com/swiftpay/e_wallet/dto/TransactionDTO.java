package com.swiftpay.e_wallet.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDTO {
    private Long senderWalletId;
    private Long receiverWalletId;
    private double amount;
}
