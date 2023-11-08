package br.com.solidgomes.app.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Calculadora {

	private String tipoCalculo;
	private BigDecimal x;
	private BigDecimal y;
}
