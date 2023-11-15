package br.com.solidgomes.app.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class OperacaoGeometrica {

	private String tipoOperacao;
	private BigDecimal resultado;
	
	public OperacaoGeometrica(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	
	public abstract BigDecimal calcularAreaDoQuadrado(BigDecimal ladoA, BigDecimal ladoB) throws Exception;
	public abstract BigDecimal calcularAreaDoTriangulo(BigDecimal base, BigDecimal altura) throws Exception;
}
