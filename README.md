# Comics API

# Sobre o projeto

O projeto é uma API REST para gerenciar Comics (Livros) de usuários. Nessa API pode ser realizado o cadastro de usuários, o cadastro de Comics e a busca por um usuário com a lista de todos seus Comics cadastrados. Para realizar o cadastro do Comics, o sistema consome uma API externa da MARVEL (https://developer.marvel.com/), para obter os dados do Comics. 

Obs: A integração com a API da MARVEL foi feita usando o Spring-Cloud-Feign.

A API também calcula um desconto no preço do Comic baseado no último número do ISBN e no dia da semana que o usuário está fazendo a requisição. 

1.) Dia do desconto deste Comics, baseado no último número do ISBN do comics, considerando as condicionais:
Final 0-1: segunda-feira
Final 2-3: terça-feira
Final 4-5: quarta-feira
Final 6-7: quinta-feira
Final 8-9: sexta-feira

2.) Também existe um atributo de desconto ativo, onde a data atual do sistema é comparada com as condicionais anteriores, onde, quando for o dia ativo do desconto retorna true, caso contrário, false.

Exemplo A: hoje é segunda-feira, o Comics tem o ISBN XXXXXXXX1, ou seja, seu desconto será às segundas-feiras e o atributo de desconto ativo será TRUE.
Exemplo B: hoje é quinta-feira, o Comics tem o ISBN XXXXXXXX1, ou seja, seu desconto será às segundas-feiras e o atributo de desconto ativo será FALSE.

3.) Caso seja o dia de desconto do livro, o preço a ser exibido aparece com 10% a menos que o valor retornado pela API da MARVEL.

# Autor

Gustavo da Silva Cruz

https://www.linkedin.com/in/gustavo-silva-cruz-20b128bb/
