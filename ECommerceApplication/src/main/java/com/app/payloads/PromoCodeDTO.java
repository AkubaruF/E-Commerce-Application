package com.app.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromoCodeDTO {
    private Long codeId;
    private String promoCode;
    private double discountPercentage;
    private int quota;

}
