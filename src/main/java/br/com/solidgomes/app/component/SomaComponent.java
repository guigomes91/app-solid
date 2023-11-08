package br.com.solidgomes.app.component;

import java.math.BigDecimal;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.com.solidgomes.app.model.Calculadora;
import br.com.solidgomes.app.service.Operacao;
import lombok.extern.slf4j.Slf4j;

@Primary
@Component
@Slf4j
public class SomaComponent implements Operacao {

	@Override
	public BigDecimal realizarCalculo(Calculadora cal) {
		log.info("## Calculo de Soma ##");
		BigDecimal soma = cal.getX().add(cal.getY());
		
		return new BigDecimal(soma.doubleValue());
	}

}
