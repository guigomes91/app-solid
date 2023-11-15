package br.com.solidgomes.app.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.solidgomes.app.dto.OperacaoGeometrica;
import jakarta.annotation.Nonnull;

@Service
public class OperacaoMatematicaGeometrica extends OperacaoGeometrica {
	
	@Override
	public BigDecimal calcularAreaDoQuadrado(@Nonnull BigDecimal ladoA, @Nonnull BigDecimal ladoB) throws Exception {
		return ladoA.multiply(ladoB);
	}

}
