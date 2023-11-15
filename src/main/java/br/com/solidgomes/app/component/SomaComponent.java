package br.com.solidgomes.app.component;

import java.math.BigDecimal;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.com.solidgomes.app.dto.CalculadoraDTO;
import br.com.solidgomes.app.service.OperacaoMatematicaComum;

@Primary
@Component
public class SomaComponent implements OperacaoMatematicaComum {

	@Override
	public BigDecimal realizarCalculo(CalculadoraDTO cal) {
		cal.setTipoCalculo("SOMA");
		BigDecimal soma = cal.getX().add(cal.getY());
		
		return new BigDecimal(soma.doubleValue());
	}

	@Override
	public String toString() {
		return "Operação de soma";
	}
}
