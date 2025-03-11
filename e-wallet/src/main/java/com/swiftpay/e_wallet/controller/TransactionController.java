package com.swiftpay.e_wallet.controller;

import com.swiftpay.e_wallet.dto.TransactionDTO;
import com.swiftpay.e_wallet.model.Transaction;
import com.swiftpay.e_wallet.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    // Transfer Funds
    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(@RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = transactionService.transferFunds(transactionDTO);
        return new ResponseEntity<>("Transaction successful! Transaction ID: " + transaction.getId(), HttpStatus.OK);
    }
}
