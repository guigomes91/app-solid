package br.com.solidgomes.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.solidgomes.app.service.OperacaoMatematicaService;

@Configuration
public class OperacaoMathConfig implements CommandLineRunner {

	@Autowired
	private OperacaoMatematicaService runOperacao;
	
	@Override
	public void run(String... args) throws Exception {
		/*
		 * Aplicando os conceitos de inversão de dependência e open closed do SOLID
		 * utilizando Spring (@Primary e @Qualifier)
		 */
		runOperacao.executarOperacaoMatematica();
		runOperacao.logarOperacoes();
	}

}
