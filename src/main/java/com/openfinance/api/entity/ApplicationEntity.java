package com.openfinance.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "applications")
public class ApplicationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "interval")
  private String interval;

  @ManyToOne
  @JoinColumn(name = "indexer_id")
  private IndexerEntity indexer;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private CustomersEntity customers;
}