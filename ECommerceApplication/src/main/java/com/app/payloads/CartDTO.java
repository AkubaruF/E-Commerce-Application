package com.app.payloads;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

	private Long cartId;
	private Double totalPrice = 0.0;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<ProductDTO> products = new ArrayList<>();
}
