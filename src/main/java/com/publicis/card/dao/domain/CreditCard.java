package com.publicis.card.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "CREDIT_CARD", uniqueConstraints = @UniqueConstraint(columnNames = {"CARD_NUMBER"}))
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CARD_NUMBER")
    private String cardNumber;

    @Column(name = "CARD_HOLDER_NAME")
    private String cardHolderName;

    @Column(name = "CARD_LIMIT")
    private BigDecimal cardlimit;

    @Column(name = "CARD_BALANCE")
    private BigDecimal cardBalance;
}
