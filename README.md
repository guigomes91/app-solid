# app-solid
Este projeto é referente aos meus estudos sobre SOLID.

## OCP
No primeiro commit podemos ver o conceito do OCP (Open/Closed Principle), onde crio uma interface Operacao e nela tem o método realizarCalculo.
Foi criado dois componentes que implementam a interface, um componente com @Primary e o outro com @Qualifies, assim o Spring sabe se virar com a injeção da 
abstração (Ioc).
Desta forma, eu pude aplicar o conceito de classes que estão fechadas para modificação porém abertas para extensão. Também pode-se perceber que um strategy é aplicado
como design pattern. Agora se eu quero calcular uma multiplicação, basta eu criar uma nova classe e implementar a interface.
