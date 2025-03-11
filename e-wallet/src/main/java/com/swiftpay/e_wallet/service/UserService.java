package com.swiftpay.e_wallet.service;

import com.swiftpay.e_wallet.dto.UserDTO;
import com.swiftpay.e_wallet.execution.WalletNotFoundException;
import com.swiftpay.e_wallet.model.Role;
import com.swiftpay.e_wallet.model.User;
import com.swiftpay.e_wallet.execution.WalletNotFoundException;

import com.swiftpay.e_wallet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // Register new user
    public User registerUser(UserDTO userDTO) {
        User newUser = User.builder()
                .fullname(userDTO.getFullName())  // Corrected fullName method
                .username(userDTO.getUsername())  // Added username as per User model
                .email(userDTO.getEmail())
                .phoneNumber(userDTO.getPhoneNumber())
                .password(passwordEncoder.encode(userDTO.getPassword()))  // Password encryption
                .role(Role.USER)  // Corrected Role usage (Enum fix)
                .isActive(true)
                .build();

        return userRepository.save(newUser);
    }

    // Find user by email
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new WalletNotFoundException("User not found with email: " + email));
    }

    // Add funds to user's wallet
    public void addFunds(Long userId, double amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new WalletNotFoundException("User not found with ID: " + userId));

        user.getWallet().setBalance(user.getWallet().getBalance() + amount);
        userRepository.save(user);
    }

    // Deduct funds from user's wallet
    public void deductFunds(Long userId, double amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new WalletNotFoundException("User not found with ID: " + userId));

        if (user.getWallet().getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        user.getWallet().setBalance(user.getWallet().getBalance() - amount);
        userRepository.save(user);
    }
}
