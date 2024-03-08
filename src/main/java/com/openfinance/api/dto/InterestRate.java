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
public class InterestRate {
  @JsonProperty("referentialRateIndexer")
  private String referentialRateIndexer;
  private double rate;
  private List<Application> applications;
  @JsonProperty("minimumRate")
  private double minimumRate;
  @JsonProperty("maximumRate")
  private double maximumRate;
}
