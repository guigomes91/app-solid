  package br.com.solidgomes.app.component;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.solidgomes.app.dto.CalculadoraDTO;
import br.com.solidgomes.app.service.OperacaoMatematicaComum;
import lombok.extern.slf4j.Slf4j;

@Qualifier("divisaoComponent")
@Component
@Slf4j
public class DivisaoComponent implements OperacaoMatematicaComum {

	@Override
	public BigDecimal realizarCalculo(CalculadoraDTO cal) {
		cal.setTipoCalculo("DIVISAO");
		
		try {
			if (cal.getY().compareTo(BigDecimal.ZERO) == 0) {
				throw new Exception("Operação não permitida, valor zerado no divisor!");
			}
			
			BigDecimal divisao = cal.getX().divide(cal.getY());
			return new BigDecimal(divisao.doubleValue());
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return BigDecimal.ZERO;
	}
	
	@Override
	public String toString() {
		return "Operação de divisão";
	}

}
