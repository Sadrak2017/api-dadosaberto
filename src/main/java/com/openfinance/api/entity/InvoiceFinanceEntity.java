package com.openfinance.api.entity;

import com.openfinance.api.dto.Links;
import com.openfinance.api.dto.Meta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoice_finances")
public class InvoiceFinanceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "invoice_finance_id")
  @Column(columnDefinition = "TEXT")
  private List<DataItemEntity> data = new ArrayList<>();

  @Embedded
  @Column(columnDefinition = "TEXT")
  private Links links;

  @Embedded
  @Column(columnDefinition = "TEXT")
  private Meta meta;
}