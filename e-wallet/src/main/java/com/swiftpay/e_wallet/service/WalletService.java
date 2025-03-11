package com.swiftpay.e_wallet.service;

import com.swiftpay.e_wallet.dto.WalletDTO;
import com.swiftpay.e_wallet.execution.WalletNotFoundException;
import com.swiftpay.e_wallet.model.Wallet;
import com.swiftpay.e_wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    // ✅ Get Wallet by User ID
    public Wallet getWalletByUserId(Long userId) {
        return walletRepository.findByUserId(userId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found for user ID: " + userId));
    }

    // ✅ Create or Update Wallet
    public Wallet createOrUpdateWallet(WalletDTO wallet) {
        return walletRepository.save(wallet);
    }

    // ✅ Add Balance to Wallet
    public void addBalance(Long userId, double amount) {
        Wallet wallet = getWalletByUserId(userId);
        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);
    }

    // ✅ Deduct Balance from Wallet
    public void deductBalance(Long userId, double amount) {
        Wallet wallet = getWalletByUserId(userId);
        if (wallet.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance in wallet!");
        }
        wallet.setBalance(wallet.getBalance() - amount);
        walletRepository.save(wallet);
    }

    // ✅ Transfer Funds
    public void transferFunds(Long senderId, Long receiverId, double amount) {
        Wallet senderWallet = getWalletByUserId(senderId);
        Wallet receiverWallet = getWalletByUserId(receiverId);

        if (senderWallet.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance in sender's wallet!");
        }

        senderWallet.setBalance(senderWallet.getBalance() - amount);
        receiverWallet.setBalance(receiverWallet.getBalance() + amount);

        walletRepository.save(senderWallet);
        walletRepository.save(receiverWallet);
    }
}
