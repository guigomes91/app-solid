package br.com.solidgomes.app.service;

import java.util.List;

public interface OperacaoMatematicaService {

	void executarOperacaoMatematica(List<OperacaoMatematicaComum> operacoes);
	void logarOperacoes(List<OperacaoMatematicaComum> operacoes);
	void calcularTaboada(int numero);
}
