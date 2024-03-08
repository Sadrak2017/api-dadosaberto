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
@Table(name = "service")
public class ServicesEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "code")
  private String code;

  @Column(name = "charging_trigger_info")
  private String chargingTriggerInfo;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "service_id")
  private List<PriceEntity> prices;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "maximum_id", referencedColumnName = "id")
  private MaximumEntity maximum;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "minimum_id", referencedColumnName = "id")
  private MinimumEntity minimum;
}