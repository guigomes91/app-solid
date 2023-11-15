package br.com.solidgomes.app.service;

import java.math.BigDecimal;

import br.com.solidgomes.app.dto.CalculadoraDTO;

public interface OperacaoMatematicaComum {

	BigDecimal realizarCalculo(CalculadoraDTO cal);
	void calcularTaboada(int numero) throws Exception;
}
