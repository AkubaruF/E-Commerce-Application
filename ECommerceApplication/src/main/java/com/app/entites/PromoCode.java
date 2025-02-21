package com.app.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "promo_codes")
@NoArgsConstructor
@AllArgsConstructor
public class PromoCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promoId;

    @NotBlank
    @Column(unique = true)
    private String promoCode;

    @DecimalMin("0.0")
    private double discountPercentage;

    private int quota;
}
