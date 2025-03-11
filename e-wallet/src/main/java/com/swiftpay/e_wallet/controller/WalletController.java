package com.swiftpay.e_wallet.controller;

import com.swiftpay.e_wallet.dto.WalletDTO;
import com.swiftpay.e_wallet.model.Wallet;
import com.swiftpay.e_wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    // Endpoint to create or update a wallet
    @PostMapping("/createOrUpdate")
    public ResponseEntity<Wallet> createOrUpdateWallet(@RequestBody WalletDTO walletDTO) {
        Wallet wallet = walletService.createOrUpdateWallet(walletDTO);
        return new ResponseEntity<>(wallet, HttpStatus.CREATED);
    }

    // Endpoint to fetch wallet details by userId
    @GetMapping("/{userId}")
    public ResponseEntity<Wallet> getWalletByUserId(@PathVariable Long userId) {
        Wallet wallet = walletService.getWalletByUserId(userId);
        return ResponseEntity.ok(wallet);
    }

    // Endpoint to add balance to the wallet
    @PostMapping("/addBalance")
    public ResponseEntity<String> addBalance(@RequestParam Long userId, @RequestParam double amount) {
        walletService.addBalance(userId, amount);
        return ResponseEntity.ok("Balance added successfully!");
    }

    // Endpoint to deduct balance from the wallet
    @PostMapping("/deductBalance")
    public ResponseEntity<String> deductBalance(@RequestParam Long userId, @RequestParam double amount) {
        try {
            walletService.deductBalance(userId, amount);
            return ResponseEntity.ok("Balance deducted successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
