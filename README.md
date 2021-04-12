<h1 align="center">LFA-Trabalho-Pratico-1</h1>

## Descrição do Projeto
<p align="center">O trabalho prático consiste na implementação de um programa que permita cadastrar e testar autômatos finitos.</p>
<p align="center">O trabalho foi implementado na linguagem Java.</p>

## Instruções de Teste

O programa é executado por meio do Java Swing e em sua primeira ação exibirá duas opções para o usuario conseguir montar o seu Autômato e testa-lo. 

### Caso a escolha seja do AUTÔMATO DETERMINISTICO:

Será questionado quantos caracteres tem no alfabeto que o usuario criou, e logo após vem a inserção dos mesmos (sendo assim um caracter por vez).

Com o alfabeto definido, a definição da palavra vem em seguida. Sendo um caracter por vez, o usuario pode fazer quantas inserções quiser. No momento que não quiser mais inserir, é teclar ENTER.

Logo após começa a montagem do autômato de fato

(Nota: Será utilizado para identificação de estados o padrão "qx", sendo x o numero do estado)

Informações para a construção:

- 1º - Deseja-se saber qual o estado inicial,
- 2º - Deseja-se saber quantos estados finais existem no autômato,
- 3º - A depender do numero de estados finais, o programa solicitará a inserção de cada um por vez,
- 4º - Deseja-se saber quantas transições existem no autômato.

Montagem do autômato:

(Esse será um processor que será repetido pelo numero de vezes de transições que foi inserido)

- 1º - Deseja-se saber de qual estado está transferindo,
- 2º - Deseja-se saber o valor da transição,
- 3º - Deseja-se saber para qual estado vai com a transição.

Após o autômato montado, o programa irá executar e mostrar em tela se a palavra definida pertence ou não ao autômato definido.

### Caso a escolha seja do AUTÔMATO NÃO-DETERMINISTICO:

Será questionado quantos caracteres tem no alfabeto que o usuario criou, e logo após vem a inserção dos mesmos (sendo assim um caracter por vez).

Com o alfabeto definido, a definição da palavra vem em seguida. Sendo um caracter por vez, o usuario pode fazer quantas inserções quiser. No momento que não quiser mais inserir, é teclar ENTER.

Logo após começa a montagem do autômato de fato

(Nota: Será utilizado para identificação de estados o padrão "qx", sendo x o numero do estado)

Informações para a construção:

- 1º - Deseja-se saber qual o estado inicial,
- 2º - Deseja-se saber quantos estados finais existem no autômato,
- 3º - A depender do numero de estados finais, o programa solicitará a inserção de cada um por vez,
- 4º - Deseja-se saber quantas transições existem no autômato.

Montagem do autômato:

(Esse será um processor que será repetido pelo numero de vezes de transições que foi inserido)

- 1º - Deseja-se saber de qual estado está transferindo,
- 2º - Deseja-se saber o valor da transição,
- 3º - Deseja-se saber para qual estado vai com a transição.

Após o autômato montado, o programa irá executar e mostrar em tela se a palavra definida pertence ou não ao autômato definido.
