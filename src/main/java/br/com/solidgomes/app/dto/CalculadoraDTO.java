package br.com.solidgomes.app.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalculadoraDTO extends OperacaoGeometrica {

	private String tipoCalculo;
	private BigDecimal x;
	private BigDecimal y;
	private BigDecimal resultado;
	
	public CalculadoraDTO(String tipoCalculo, BigDecimal x, BigDecimal y) {
		this.tipoCalculo = tipoCalculo;
		this.x = x;
		this.y = y;
	}

	@Override
	public void calcularAreaDoQuadrado(BigDecimal ladoA, BigDecimal ladoB) throws Exception {
		throw new Exception("Method not allow");
	}
}
