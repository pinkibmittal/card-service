package com.publicis.card.repository;

import com.publicis.card.dao.domain.CreditCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends CrudRepository<CreditCard, Long> {

    Optional<CreditCard> findByCardNumber(final String cardNumber);

    @Override
    Iterable<CreditCard> findAll();
}
