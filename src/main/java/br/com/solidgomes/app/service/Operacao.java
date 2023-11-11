package br.com.solidgomes.app.service;

import java.math.BigDecimal;

import br.com.solidgomes.app.dto.CalculadoraDTO;

public interface Operacao {

	BigDecimal realizarCalculo(CalculadoraDTO cal);
}
