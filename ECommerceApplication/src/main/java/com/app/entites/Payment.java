package com.app.entites;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.app.exceptions.ResourceNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	private static final Map<String, Integer> bankList = new HashMap<>();

	static {
		bankList.put("BCAA", 1234567890);
		bankList.put("BNII", 1231231231);
		bankList.put("BRII", 1234509876);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;

	@OneToOne(mappedBy = "payment", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Order order;

	@NotBlank
	@Size(min = 4, message = "Payment method must contain atleast 4 characters")
	private String paymentMethod;

	private Integer accountNumber;

	public void setPayment(String paymentMethod) {
		if (!bankList.containsKey(paymentMethod)) {
			throw new ResourceNotFoundException("Bank", "name", paymentMethod);
		}
		this.paymentMethod = paymentMethod;
		this.accountNumber = bankList.get(paymentMethod);
	}

}
