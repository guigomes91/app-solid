package br.com.solidgomes.app.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import br.com.solidgomes.app.dto.CalculadoraDTO;
import br.com.solidgomes.app.service.Operacao;
import br.com.solidgomes.app.service.OperacaoMatematicaService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OperacaoMatematicaServiceImpl implements OperacaoMatematicaService {

	@Autowired
	private List<Operacao> operacoes;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void executarOperacaoMatematica() {
		CalculadoraDTO calc = new CalculadoraDTO("", new BigDecimal(10), new BigDecimal(2));
		
		operacoes.forEach(operacao -> {
			BigDecimal retorno = operacao.realizarCalculo(calc);
			log.info("Valor calculado: {}", retorno);
			
			StringBuilder sql = new StringBuilder();
			sql.append("insert into calculadora (id, tipo_calculo, valor_x, valor_y, resultado) ");
			sql.append("values ('");
			sql.append(UUID.randomUUID()).append("', '");
			sql.append(calc.getTipoCalculo()).append("',");
			sql.append(calc.getX()).append(",");
			sql.append(calc.getY()).append(",");
			sql.append(retorno).append(")");
			
			jdbcTemplate.execute(sql.toString());
		});
	}
}
