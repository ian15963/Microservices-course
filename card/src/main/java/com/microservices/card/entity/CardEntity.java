package com.microservices.card.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    private String mobileNumber;

    private String cardNumber;

    private String cardType;

    private Integer totalLimit;

    private Integer amountUsed;

    private Integer availableAmount;

}
