# app-solid
Este projeto é referente aos meus estudos sobre SOLID.

## SRP
A classe **OperacaoMatematicaServiceImpl** é responsável por chamar os calculos através da abstração **Operacao**, desta forma desacoplamos a classe e blindamos a mesma 
para não permitir alterações mas sim, estar aberta a extenções, respeitando o principio OCP. Mas, podemos ver no trecho abaixo, que ela está quebrando o principio **SRP**:

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

No commit [SRP](https://github.com/guigomes91/app-solid/commit/b915013f2ce0c2241a0f3c00b525afbd60cdbb98), foi inserido salvar os resultados dos cálculos no banco de dados dentro da classe **OperacaoMatematicaServiceImpl**, desta forma, precisamos
separar as responsábilidades desta classe, ela não precisa conhecer a regra e lógica para inserir os cálculos no banco de dados, já que ela é uma classe de fachada apenas que chama a execução e retorna os valores.

## OCP
No primeiro commit podemos ver o conceito do OCP (Open/Closed Principle), onde crio uma interface Operacao e nela tem o método realizarCalculo.
Foi criado dois componentes que implementam a interface, um componente com @Primary e o outro com @Qualifies, assim o Spring sabe se virar com a injeção da 
abstração (Ioc).
Desta forma, eu pude aplicar o conceito de classes que estão fechadas para modificação porém abertas para extensão. Também pode-se perceber que um strategy é aplicado
como design pattern. Agora se eu quero calcular uma multiplicação, basta eu criar uma nova classe e implementar a interface.
