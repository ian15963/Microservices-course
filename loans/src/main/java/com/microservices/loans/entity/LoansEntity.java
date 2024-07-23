package com.microservices.loans.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "loans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoansEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loanId;

    private String mobileNumber;

    private String loanNumber;

    private String loanType;

    private Integer totalLoan;

    private Integer amountPaid;

    private Integer outstandingAmount;

}

