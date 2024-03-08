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
public class Services {
  private String name;
  private String code;
  @JsonProperty("chargingTriggerInfo")
  private String chargingTriggerInfo;
  @JsonProperty("prices")
  private List<Prices> prices;
  private Maximum maximum;
  private Minimum minimum;
}
