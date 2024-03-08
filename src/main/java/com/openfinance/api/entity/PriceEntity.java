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
@Table(name = "prices")
public class PriceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "interval")
  private String interval;

  @Column(name = "value")
  private String value;

  @Column(name = "currency")
  private String currency;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private CustomersEntity customers;
}
