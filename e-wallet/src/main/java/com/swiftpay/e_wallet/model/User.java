//package com.swiftpay.e_wallet.model;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Table(name = "users")
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String fullName;  // Added 'fullName' attribute
//
//    @Column(nullable = false, unique = true)
//    private String username;
//
//    @Column(nullable = false)
//    private String password;
//
//    @Column(nullable = false)
//    private String email;
//
//    @Enumerated(EnumType.STRING)  // Role changed from String to Enum
//    @Column(nullable = false)
//    private Role role;
//
//    @Column(nullable = false)
//    private boolean isActive;  // Added 'isActive' for user status control
//}

//package com.swiftpay.e_wallet.model;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Table(name = "users")
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, unique = true)
//    private String username;
//
//    @Column(nullable = false)
//    private String password;
//
//    @Column(nullable = false)
//    private String email;
//
//    @Enumerated(EnumType.STRING)  // Enum mapping for Role
//    @Column(nullable = false)
//    private Role role;
//
//    public String getUsername() {
//        return username;
//    }
//}
//package com.swiftpay.e_wallet.model;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Table(name = "users")
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, unique = true)
//    private String username;
//
//    @Column(nullable = false)
//    private String password;
//
//    @Column(nullable = false)
//    private String fullname;
//
//    @Column(nullable = false)
//    private String email;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Role role;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "wallet_id", referencedColumnName = "id") // Foreign key for wallet
//    private Wallet wallet;  // Add this Wallet field
//
//    // Getter for getWallet
//    public Wallet getWallet() {
//        return wallet;
//    }
//
//}
package com.swiftpay.e_wallet.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)  // ✅ Added `fullName`
    private String fullname;

    @Column(nullable = false, unique = true)  // ✅ Added `phoneNumber`
    private String phoneNumber;

    @Column(nullable = false)  // ✅ Added `isActive` (for account status)
    private boolean isActive;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id", referencedColumnName = "id") // Foreign key for wallet
    private Wallet wallet;

    // Getter for getWallet
    public Wallet getWallet() {
        return wallet;
    }

    // Getter for fullName
    public String getFullName() {
        return fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    // ✅ Setter for isActive (if needed for activation/deactivation logic)
    public void setActive(boolean active) {
        isActive = active;
    }
}

