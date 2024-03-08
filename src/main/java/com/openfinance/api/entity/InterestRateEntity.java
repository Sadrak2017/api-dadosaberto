package com.openfinance.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "interest_rate")
public class InterestRateEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "referential_rate_indexer")
  private String referentialRateIndexer;

  @Column(name = "rate")
  private double rate;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "interest_rate_id")
  private List<ApplicationEntity> applications;

  @Column(name = "minimum_rate")
  private double minimumRate;

  @Column(name = "maximum_rate")
  private double maximumRate;
}
