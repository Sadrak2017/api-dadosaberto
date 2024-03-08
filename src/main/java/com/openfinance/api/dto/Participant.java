package com.openfinance.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Participant {
  private String brand;
  private String name;
  @JsonProperty("cnpjNumber")
  private String cnpj;
  @JsonProperty("urlComplementaryList")
  private String urlComplementaryList;
}
