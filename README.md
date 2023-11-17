# Projeto app-solid
Este projeto é referente aos meus estudos sobre os princípios do **SOLID** e o que já venho utilizando no meu dia a dia. 
Aqui vou implementar e explicar cada princípio dos 5 que existem.

Ferramentas e tecnologias utilizadas: 
- Spring Boot na versão **3.1.5**
- Hibernate
- PostgreSQL
- Programação orientada a objetos
- Boas práticas de programação com separação de camadas, pacotes e programação orientada a interfaces.
- Java 17
- IDE STS

![S](https://github.com/guigomes91/app-solid/blob/master/src/main/resources/static/assets/S_BG.png)RP

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

![O](https://github.com/guigomes91/app-solid/blob/master/src/main/resources/static/assets/O_BG.png)CP

No primeiro commit [OCP Impl](https://github.com/guigomes91/app-solid/commit/865ea4fc5244968d8bec768f9139abf88d415339#diff-bb2848a183e7299ddae9d28f9c750c17468172083578132930a1f9f52a6a350f) podemos ver o conceito do OCP (Open/Closed Principle), onde crio uma interface Operacao e nela tem o método realizarCalculo.
Foi criado dois componentes que implementam a interface, um componente com @Primary e o outro com **@Qualifier("divisaoComponent")**, assim o Spring sabe se virar com a injeção da 
abstração (Ioc).
Desta forma, eu pude aplicar o conceito de classes que estão fechadas para modificação porém abertas para extensão. Também pode-se perceber que um strategy é aplicado
como design pattern. Agora se eu quero calcular uma multiplicação, basta eu criar uma nova classe e implementar a interface.

![L](https://github.com/guigomes91/app-solid/blob/master/src/main/resources/static/assets/L_BG.png)SP

Para revisar a premissa do LSP, vou citá-la logo abaixo:

>Se para cada objeto x1 do tipo S há um objeto x2 do tipo T de tal forma que, para todos os programas P definidos em termos de T,
>o comportamento de P não muda quando x1 é substituído por x2 então S é um subtipo de T.

Exemplo mais prático é, você criar uma classe **Animal** com os métodos comer(), andar(), latir(), miar().
A classe **Cachorro** extende de **Animal**, desta forma, terá que implementar o método **miar()**, mas um cachorro não mia rsrs.

Assumimos que a aplicação só realiza operação matemática simples como soma, subtração, divisão e multiplicação. No commit [Violação do LSP](https://github.com/guigomes91/app-solid/commit/ca09858bfcdd9577eeae97dd9e9ded220d835bd0) a classe **CalculadoraDTO** extende de 
**OperacaoGeometrica**, com isso a classe base foi obrigada a implementar o método **calcularAreaDoQuadrado**, porém não é possível realizar essa nova operação, a aplicação retorna uma exceção não esperada, violando o principo da substituição de Liskov.
Corrigimos a violação do LSP no commit [Ajuste LSP](https://github.com/guigomes91/app-solid/commit/6aca9ea441a608f8d90aece1b11dcd19a3b1504a), agora vejamos que extendemos para uma classe que é responsável por realizar cálculos mais complexos, onde podemos aumentar os métodos para outros tipos de cálculos, separando também a responsabilidades das classes.
No commit [Removido extensão de CalculadoraDTO](https://github.com/guigomes91/app-solid/commit/5fee06d20350a8a42fa6dbf27119989b5be407d2) foi removido a extensão que violava o LSP.

Classe **OperacaoMatematicaGeometrica**

```
@Service
public class OperacaoMatematicaGeometrica extends OperacaoGeometrica {
	
	@Override
	public BigDecimal calcularAreaDoQuadrado(BigDecimal ladoA, BigDecimal ladoB) throws Exception {
		return ladoA.multiply(ladoB);
	}

}
```

![I](https://github.com/guigomes91/app-solid/blob/master/src/main/resources/static/assets/I_BG.png)SP

Interfaces devem ser enxutas, mais especificas. Precisa ter coerência ao criar a interface, fazendo sentido para a implementação, não gerando classes "gordas" e obrigando a implementar métodos que não são da classe e necessários para ela. 
Interfaces com muitos comportamentos são complexas e difíceis de evoluir.

No commit [Violando ISP](https://github.com/guigomes91/app-solid/commit/5f1e64e735159b599adda48a1b93f975f4cba302) podemos verificar a violação da premissa ISP. Adicionei na interface **OperacaoMatematicaComum**
a assinatura **calcularTaboada**, desta forma obrigando os componentes **DivisaoComponent** e **SomaComponent** a implementar o método, sendo que esses componentes só realizam operações básicas da matemática e a implementação lança uma execeção não esperada.

```
@Primary
@Component
public class SomaComponent implements OperacaoMatematicaComum {

	@Override
	public BigDecimal realizarCalculo(CalculadoraDTO cal) {
		cal.setTipoCalculo("SOMA");
		BigDecimal soma = cal.getX().add(cal.getY());
		
		return new BigDecimal(soma.doubleValue());
	}

	@Override
	public String toString() {
		return "Operação de soma";
	}

	@Override
	public void calcularTaboada(int numero) throws Exception {
		throw new Exception("Method not allow");
	}
}
```
Agora foi ajustado para fazer sentido as interface. No commit [Ajuste ISP](https://github.com/guigomes91/app-solid/commit/d88c3fdbc389bfc7e632aa83b8ec8889a796ce28), o método **calcularTaboada** foi definido na 
interface **OperacaoMatematicaService**, visto que a classe **OperacaoMatematicaServiceImpl** é responsável por executar as operações matemáticas tanto básicas como geometricas. Fazendo mais sentido para aplicação.
Mas agora começamos a ter um novo problema, essa classe tende a crescer se existerem novos tipos de cálculos matemáticos, então ela é forte candidata a ser refatorada novamente.

![D](https://github.com/guigomes91/app-solid/blob/master/src/main/resources/static/assets/D_BG.png)IP

Acoplamento entre interfaces ao invés de classe concreta, é fácil de crescer o código e dar manutenção. 

No commit [Violando DIP](https://github.com/guigomes91/app-solid/commit/aaf22270e2b918872d06f3287c780580bac74965) eu passei uma classe concreta para o método **executarOperacaoMatematica**, repare como a classe **OperacaoMatematicaServiceImpl** fica totalmente 
acoplada ao componente **DivisaoComponent**. Ficando completamente dificil de extender essa classe, impossibilitando o reuso, desacoplamento e aumentar para novas funcionalidades.

