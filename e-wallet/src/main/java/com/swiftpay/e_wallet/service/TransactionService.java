package com.swiftpay.e_wallet.service;

import com.swiftpay.e_wallet.dto.TransactionDTO;
import com.swiftpay.e_wallet.execution.WalletNotFoundException;
import com.swiftpay.e_wallet.model.Transaction;
import com.swiftpay.e_wallet.model.Wallet;
import com.swiftpay.e_wallet.repository.TransactionRepository;
import com.swiftpay.e_wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    // Transfer funds securely
    public Transaction transferFunds(TransactionDTO transactionDTO) {
        Wallet senderWallet = walletRepository.findById(transactionDTO.getSenderWalletId())
                .orElseThrow(() -> new WalletNotFoundException("Sender wallet not found"));

        Wallet receiverWallet = walletRepository.findById(transactionDTO.getReceiverWalletId())
                .orElseThrow(() -> new WalletNotFoundException("Receiver wallet not found"));

        if (senderWallet.getBalance() < transactionDTO.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }

        // Deduct from sender and add to receiver
        senderWallet.setBalance(senderWallet.getBalance() - transactionDTO.getAmount());
        receiverWallet.setBalance(receiverWallet.getBalance() + transactionDTO.getAmount());

        // Save updated wallets
        walletRepository.save(senderWallet);
        walletRepository.save(receiverWallet);

        // Record transaction
        Transaction transaction = Transaction.builder()
                .sender(senderWallet)
                .receiver(receiverWallet)
                .amount(transactionDTO.getAmount())
                .timestamp(LocalDateTime.now())
                .build();

        return transactionRepository.save(transaction);
    }
}
