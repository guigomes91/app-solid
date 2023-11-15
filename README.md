# Projeto app-solid
Este projeto é referente aos meus estudos sobre SOLID e o que já venho utilizando no meu dia a dia. Aqui vou implementar e explicar cada principio dos 5 que existem.
O projeto é feito com SPRING BOOT na versão **3.1.5**, hibernate, postgreSQL e boas práticas de programação com separação de camadas, pacotes e programação orientada a interfaces.
Utilizei o Java 17 para o projeto.

![S](https://github.com/guigomes91/app-solid/blob/master/src/main/resources/static/assets/S_BG.png) ##RP

A classe **OperacaoMatematicaServiceImpl** é responsável por chamar os calculos através da abstração **Operacao**, desta forma desacoplamos a classe e blindamos a mesma 
para não permitir alterações mas sim, estar aberta a extenções, respeitando o principio OCP. Mas, podemos ver no trecho abaixo, que ela está violando o principio **SRP**:

```
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
```

No commit [SRP Violado](https://github.com/guigomes91/app-solid/commit/b915013f2ce0c2241a0f3c00b525afbd60cdbb98), foi salvo os resultados dos cálculos no banco de dados dentro da classe **OperacaoMatematicaServiceImpl**, desta forma, precisamos
separar as responsábilidades desta classe, ela não precisa conhecer a regra e lógica para inserir os cálculos no banco de dados, já que ela é uma classe service de fachada apenas que chama a execução e retorna os valores.
Desta forma, no commit [Refatoração SRP](https://github.com/guigomes91/app-solid/commit/cb8b1b5686479bb831b306cb47e290e6b8a54f60) foi criado um repository para a entidade **CalculadoraEntity**, injetado o repositório no service e realizado a chamada para salvar 
a entidade com o resultado dos cálculos, podemos ver a redução do código e a responsabilidade sendo separada.

![O](https://github.com/guigomes91/app-solid/blob/master/src/main/resources/static/assets/O_BG.png) ##CP

No primeiro commit [OCP Impl](https://github.com/guigomes91/app-solid/commit/865ea4fc5244968d8bec768f9139abf88d415339#diff-bb2848a183e7299ddae9d28f9c750c17468172083578132930a1f9f52a6a350f) podemos ver o conceito do OCP (Open/Closed Principle), onde crio uma interface Operacao e nela tem o método realizarCalculo.
Foi criado dois componentes que implementam a interface, um componente com @Primary e o outro com **@Qualifier("divisaoComponent")**, assim o Spring sabe se virar com a injeção da 
abstração (Ioc).
Desta forma, eu pude aplicar o conceito de classes que estão fechadas para modificação porém abertas para extensão. Também pode-se perceber que um strategy é aplicado
como design pattern. Agora se eu quero calcular uma multiplicação, basta eu criar uma nova classe e implementar a interface.

L <a href="https://www.flaticon.com/br/icones-gratis/eu" title="eu ícones">L</a>
## LSP
Assumimos que a aplicação só realiza operação matemática simples como soma, subtração, divisão e multiplicação. No commit [Violação do LSP](https://github.com/guigomes91/app-solid/commit/ca09858bfcdd9577eeae97dd9e9ded220d835bd0) a classe **CalculadoraDTO** extende de 
**OperacaoGeometrica**, com isso a classe base foi obrigada a implementar o método **calcularAreaDoQuadrado**, porém não é possível realizar essa nova operação, a aplicação retorna uma exceção não esperada, violando o principo da substituição de Liskov.

I <a href="https://www.flaticon.com/br/icones-gratis/letra-i" title="letra i ícones">Letra i ícones criados por rizal2109 - Flaticon</a>

D <a href="https://www.flaticon.com/br/icones-gratis/letra-d" title="letra d ícones">Letra d ícones criados por Freepik - Flaticon</a>
