package com.valyx.pdfdatamanager.repository;

import com.valyx.pdfdatamanager.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface Repository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByDateLessThanEqualAndDateGreaterThanEqual(LocalDate toDate, LocalDate fromDate);
    Transaction findFirstByDateEquals(LocalDate date);
}
