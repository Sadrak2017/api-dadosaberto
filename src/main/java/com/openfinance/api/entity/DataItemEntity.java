package com.openfinance.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "data_item")
public class DataItemEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "participant_id", referencedColumnName = "id")
  public ParticipantEntity participant;

  @Column(name = "type")
  private String type;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fees_id", referencedColumnName = "id")
  private FeesEntity fees;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "data_item_id")
  private List<InterestRateEntity> interestRates;

  @ElementCollection
  @CollectionTable(name = "data_item_required_warranties", joinColumns = @JoinColumn(name = "data_item_id"))
  @Column(name = "warranty", columnDefinition = "TEXT")
  private List<String> requiredWarranties;

  @Column(name = "terms_conditions_url", columnDefinition = "TEXT")
  @JsonProperty("termsConditions")
  private String termsConditionsUrl;
}
