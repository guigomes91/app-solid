package br.com.solidgomes.app.service;

import br.com.solidgomes.app.component.DivisaoComponent;

public interface OperacaoMatematicaService {

	void executarOperacaoMatematica(DivisaoComponent divisaoComponent);
	void logarOperacoes();
	void calcularTaboada(int numero);
}
