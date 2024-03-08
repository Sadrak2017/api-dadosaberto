package com.openfinance.api.repository;

import com.openfinance.api.entity.DataItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataItemRepository extends JpaRepository<DataItemEntity, Long> { }
