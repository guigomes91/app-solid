package br.com.solidgomes.app.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.solidgomes.app.component.DivisaoComponent;
import br.com.solidgomes.app.dto.CalculadoraDTO;
import br.com.solidgomes.app.model.CalculadoraEntity;
import br.com.solidgomes.app.repository.CalculadoraRepository;
import br.com.solidgomes.app.service.OperacaoMatematicaComum;
import br.com.solidgomes.app.service.OperacaoMatematicaGeometrica;
import br.com.solidgomes.app.service.OperacaoMatematicaService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OperacaoMatematicaServiceImpl implements OperacaoMatematicaService {

	@Autowired
	private List<OperacaoMatematicaComum> operacoes;
	
	@Autowired
	private OperacaoMatematicaGeometrica operacaoGeometrica;
	
	@Autowired
	private CalculadoraRepository repository;
	
	@Override
	public void executarOperacaoMatematica(DivisaoComponent divisaoComponent) {
		CalculadoraDTO calc = new CalculadoraDTO("", new BigDecimal(10), new BigDecimal(2));
		
		BigDecimal retorno = divisaoComponent.realizarCalculo(calc);
		log.info("Valor calculado: {}", retorno);
		
		CalculadoraEntity calcEntity = new CalculadoraEntity();
		calcEntity.setResultado(retorno);
		calcEntity.setTipoCalculo(calc.getTipoCalculo());
		calcEntity.setX(calc.getX());
		calcEntity.setY(calc.getY());
		
		repository.save(calcEntity);
		
		try {
			BigDecimal base = BigDecimal.valueOf(15);
			BigDecimal altura = BigDecimal.valueOf(6);
			BigDecimal areaDoTriangulo = operacaoGeometrica.calcularAreaDoTriangulo(base, altura);
			BigDecimal areaDoQuadrado = operacaoGeometrica.calcularAreaDoQuadrado(base, altura);
			
			log.info("Area do triangulo {} com base {} e altura {}", areaDoTriangulo, base, altura);
			log.info("Area do quadrado {} com lado A {} e lado B {}", areaDoQuadrado, base, altura);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void logarOperacoes() {
		operacoes.forEach(op -> {
			log.info("Tipo de operações realizadas -> {}", op.toString());
		});	
	}

	@Override
	public void calcularTaboada(int numero) {
		List<Integer> numeros = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		
		log.info("Taboada de {}", numero);
		numeros.forEach(num -> {
			System.out.println(numero + " * " + num + " = " + (num * numero));
		});
	}
}
