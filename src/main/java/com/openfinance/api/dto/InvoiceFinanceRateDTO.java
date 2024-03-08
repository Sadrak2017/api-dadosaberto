package com.openfinance.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvoiceFinanceRateDTO {
  public String participant;
  public String type;
  public String referentialRateIndexer;
  public double rate;
  public double minimum;
  public double maximum;
}
