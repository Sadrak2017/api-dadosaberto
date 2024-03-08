package com.openfinance.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "participants")
public class ParticipantEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "cnpj")
  @JsonProperty("cnpjNumber")
  private String cnpj;

  @Column(name = "brand")
  private String brand;

  @Column(name = "name")
  private String name;

  @Column(name = "url_complementary_list")
  @JsonProperty("urlComplementaryList")
  private String urlComplementaryList;
}
