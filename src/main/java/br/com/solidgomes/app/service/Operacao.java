package br.com.solidgomes.app.service;

import java.math.BigDecimal;

import br.com.solidgomes.app.model.Calculadora;

public interface Operacao {

	BigDecimal realizarCalculo(Calculadora cal);
}
