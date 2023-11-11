package br.com.solidgomes.app.component;

import java.math.BigDecimal;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.com.solidgomes.app.dto.CalculadoraDTO;
import br.com.solidgomes.app.service.Operacao;
import lombok.extern.slf4j.Slf4j;

@Primary
@Component
@Slf4j
public class SomaComponent implements Operacao {

	@Override
	public BigDecimal realizarCalculo(CalculadoraDTO cal) {
		log.info("## Calculo de Soma ##");
		cal.setTipoCalculo("SOMA");
		BigDecimal soma = cal.getX().add(cal.getY());
		
		return new BigDecimal(soma.doubleValue());
	}

}
