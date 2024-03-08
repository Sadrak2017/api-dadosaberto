package com.openfinance.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataItem {
  private Participant participant;
  private String type;
  private Fees fees;
  private List<InterestRate> interestRates;
  private List<String> requiredWarranties;
  @JsonProperty("termsConditions")
  private String termsConditionsUrl;
}
