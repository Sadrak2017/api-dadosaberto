package com.openfinance.api.repository;

import com.openfinance.api.entity.InvoiceFinanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceFinanceRepository extends JpaRepository<InvoiceFinanceEntity, Long> { }
