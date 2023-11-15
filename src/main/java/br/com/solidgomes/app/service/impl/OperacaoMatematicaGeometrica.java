package br.com.solidgomes.app.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.solidgomes.app.dto.OperacaoGeometrica;

@Service
public class OperacaoMatematicaGeometrica extends OperacaoGeometrica {
	
	@Override
	public BigDecimal calcularAreaDoQuadrado(BigDecimal ladoA, BigDecimal ladoB) throws Exception {
		return ladoA.multiply(ladoB);
	}

}
