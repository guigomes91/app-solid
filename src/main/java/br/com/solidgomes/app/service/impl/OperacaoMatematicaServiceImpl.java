package br.com.solidgomes.app.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.solidgomes.app.dto.CalculadoraDTO;
import br.com.solidgomes.app.model.CalculadoraEntity;
import br.com.solidgomes.app.repository.CalculadoraRepository;
import br.com.solidgomes.app.service.Operacao;
import br.com.solidgomes.app.service.OperacaoMatematicaService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OperacaoMatematicaServiceImpl implements OperacaoMatematicaService {

	@Autowired
	private List<Operacao> operacoes;
	
	@Autowired
	private CalculadoraRepository repository;
	
	@Override
	public void executarOperacaoMatematica() {
		CalculadoraDTO calc = new CalculadoraDTO("", new BigDecimal(10), new BigDecimal(2));
		
		operacoes.forEach(operacao -> {
			BigDecimal retorno = operacao.realizarCalculo(calc);
			log.info("Valor calculado: {}", retorno);
			
			CalculadoraEntity calcEntity = new CalculadoraEntity();
			calcEntity.setResultado(retorno);
			calcEntity.setTipoCalculo(calc.getTipoCalculo());
			calcEntity.setX(calc.getX());
			calcEntity.setY(calc.getY());
			
			repository.save(calcEntity);
		});
	}
}
