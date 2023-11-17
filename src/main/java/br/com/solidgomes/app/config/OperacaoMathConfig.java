package br.com.solidgomes.app.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.solidgomes.app.service.OperacaoMatematicaComum;
import br.com.solidgomes.app.service.OperacaoMatematicaService;

@Configuration
public class OperacaoMathConfig implements CommandLineRunner {

	@Autowired
	private OperacaoMatematicaService runOperacao;
	
	@Autowired
	private List<OperacaoMatematicaComum> operacoes;
	
	@Override
	public void run(String... args) throws Exception {
		/*
		 * Aplicando os conceitos de inversão de dependência e open closed do SOLID
		 * utilizando Spring (@Primary e @Qualifier)
		 */
		
		runOperacao.executarOperacaoMatematica(operacoes);
		runOperacao.logarOperacoes(operacoes);
		runOperacao.calcularTaboada(2);
	}

}
