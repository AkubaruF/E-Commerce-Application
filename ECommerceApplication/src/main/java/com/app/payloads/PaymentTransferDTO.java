package com.app.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTransferDTO {
	private Long paymentId;
	private String paymentMethod;

	private Integer accountNumber;

}
