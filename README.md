# Sistema de controle de vendas para imobiliárias.
O sistema foi proposto como um trabalho final da disciplina de Computação Orientada a Objetos, do curso de Ciência da computação, da Universidade Federal de Itajubá - UNIFEI. Este trabalho foi desenvolvido em equipe e teve uma nota final de 98 pontos de 100.

## Descrição do sistema:
A imobiliária ItaHouse contratou seu grupo para desenvolver um sistema para controle
de venda de imóveis, de forma a facilitar a gerência de seu negócio. A ItaHouse trabalha
apenas com a venda de imóveis. Os tipos de imóvel comercializados são casas,
apartamentos, lotes, salas comerciais e propriedades rurais.
A imobiliária possui um grupo de corretores de imóveis que realizam o processo de
intermediação entre os vendedores e os compradores. No processo de compra e venda,
inicialmente os compradores procuram a imobiliária para registrar os imóveis que
desejam vender. Inicialmente, os vendedores devem ser registrados, com nome, cpf,
email e fone, indicando o contato preferencial (email, telefone ou whatsapp). Em
seguida, é possível cadastrar o(s) imóvel(is) à venda.

Cada imóvel deve ser registrado com as seguintes informações: código, tipo, descrição,
endereço, arquivoFoto, estado, preço, comissão, data de inclusão e vendedor. O código
é um número interno da imobiliária que identifica unicamente um imóvel. Os tipos de
imóveis, como já mencionado, são: casa, apartamento, lote, sala e propriedade rural. O
atributo arquivoFoto deve salvar o nome do arquivo que contém a foto do imóvel. O
estado identifica se o imóvel está disponível para venda, já foi vendido ou está
temporariamente inativo. O preço identifica o preço de venda solicitado pelo vendedor,
em geral, sujeito a negociação. A comissão é um valor percentual que varia de 1 a 5%. A
data de inclusão é a data do dia do cadastramento do imóvel. Finalmente, vendedor
identifica quem está vendendo o imóvel (já foi cadastrado previamente). Deve-se criar
uma tela para o cadastro de imóveis que permita, opcionalmente, fazer o upload de uma
imagem (foto) que exiba o imóvel.

Além do cadastramento de vendedores, o sistema deve fornecer telas para permitir o
cadastramento de corretores e compradores. Os corretores devem ser cadastrados com
nome, cpf, email, fone, percentual de comissão e CRECI, que é o número do conselho
de corretores de imóveis. O percentual de corretagem indica o valor que o corretor
recebe sobre o valor recebido pela imobiliária como comissão na venda de um imóvel.
Assim, se o percentual de corretagem do corretor que vendeu o imóvel é 40%, o
corretor fica com 40% do valor da comissão e a imobiliária fica com 60% desse valor.
Os compradores devem ser cadastrados com nome, cpf, email e fone, indicando o
contato preferencial (email, telefone ou whatsapp).

Os imóveis cadastrados e com status de “Ativo” compõe o cadastro de imóveis da
imobiliária. Assim, deve ser possível visualizar o catálogo, o qual deve ser organizado
por tipo de imóvel; deve haver um combo box que permita selecionar os tipos de
imóveis. Ao selecionar um tipo, o sistema deve incluir em um objeto JList o código e
uma descrição sucinta de todos os imóveis do tipo selecionado. O usuário deve poder
interagir com o JList de forma a selecionar imóveis de seu interesse. Ao selecionar um
imóvel, deve-se exibir um painel (na mesma ou em outra janela) contendo a foto do
imóvel selecionado à esquerda e suas informações – código, preço, descrição, endereço
e valor – à direita. Abaixo das informações, deve haver dois botões “Agendar visita” e
“Fazer proposta”.

Clicar no botão “Agendar visita” deve levar a uma tela na qual seja possível fazer o 
agendamento de uma visita no imóvel. Esta tela deve conter campos para a identificação
do visitante (comprador), do corretor que irá acompanhá-lo na visita, bem como a data e
hora agendada para a visita. Já o botão “Fazer proposta” deve levar a uma tela na qual
seja possível cadastrar uma proposta de compra do imóvel. Esta tela deve permitir a
identificação do comprador, do corretor que está intermediando o negócio, da data da
proposta e do valor da mesma.

O sistema deve ainda oferecer uma forma de se visualizar os imóveis que possuem
propostas pendentes de avaliação. Para tanto, deve-se implementar um mecanismo
parecido ao catálogo de imóveis, sem entretanto, dividir os imóveis por tipo. Ao acessar
a opção do menu que permite avaliar as propostas, o sistema deve-se exibir todos os
imóveis que estão nessa situação num JList. Ao selecionar um imóvel da lista, deve-se
visualizar todas as propostas pendentes de avaliação (cujo estado = “Submetida”). Deve
ser possível aceitar ou rejeitar cada proposta. Quando uma proposta é aceita, seu estado
passa para “Aceita” e o estado do imóvel passa para “Vendido”. Quando uma proposta é
rejeitada, seu estado passa para “Rejeitada”.

O sistema deve ainda possibilitar a mudança do estado de um imóvel para “Inativo”,
caso o vendedor desista de realizar a venda. Neste caso, o imóvel permanece no
cadastro, mas seu estado inativo faz com que ele não apareça no catálogo de venda.
O sistema deve permitir a obtenção de informações que facilitem o gerenciamento do
seu negócio, a saber:

1) Valor total faturado em um dado período. Trata-se do valor total obtido em
comissão em um período. Deve-se mostrar o valor que ficou com a imobiliária e
o valor que foi repassado para os corretores. Deve-se ler a data inicial e final e
calcular esse valor.
2) Valor total faturado por corretor em um dado período. Trata-se do valor total
obtido em comissão em um período para um corretor específico. Deve-se ler a
data inicial e final, bem como os dados do corretor, e calcular esse valor.
3) Visitas por corretor por período. Deve-se ler a data inicial e final e exibir os
imóveis que foram visitados pelo corretor no período. Deve-se informar também
o nome do comprador que esteve em cada visita.
4) Eventos por imóvel por período. Deve-se ler a data inicial e final e o código do
imóvel e listar as visitas que o imóvel recebeu, mostrando a data, o nome do
corretor e do comprador. Deve mostrar também todas as propostas recebidas,
informando o nome do comprador, do corretor, o valor da proposta e seu estado.
5) Relatório de vendas por período. Deve-se ler a data inicial e final e exibir os
dados dos imóveis que tenham sido vendidos no período, informando o valor de
venda e o valor da comissão paga à imobiliária.
6) Listagem de imóveis por vendedor. Deve-se ler o cpf do vendedor e listar todos
os seus imóveis que estão cadastrados para venda, mostrando o estado do
imóvel.

## Descrição dos arquivos:

O sistema foi desenvolvido usando a IDE NetBeans com JDK Bundle. Por isso, o projeto tem alguns arquivos de configuração e algumas pastas criadas pela própria IDE. Os códigos fonte do projeto se encontra na pasta ```scr```.

Dentro da pasta ```scr``` encontra se os arquivos organizados da seguinte forma:

![Tutorialj4](https://user-images.githubusercontent.com/46981155/90416692-2ee32180-e089-11ea-8e5a-7d21c9088dce.PNG)

O sistema foi desenvolvido em MVC (Model-View-Controller), sendo assim, o projeto está organizado da seguinte forma:

* **Pasta Modelo:**
    Contém todos os ``` Model``` do programa;
* **Pasta Controle:**
    Contém todos os ```Controller``` do programa;
* **Pasta Limite:**
    Contém todos os ```View``` do programa;
* **Pasta Utilitário:**
    Contém um arquivo com algumas definições para facilitar o desenvolvimento;
* **aplicacao.java**
    Neste arquivo está a MAIN do programa;


## Como executar
O sistema foi desenvolvido usando a IDE NetBeans 8.2 com JDK Bundle. O NetBeans funciona em **Windows**, **Linux** e **MAC**, é gratuito e pode ser baixado por esse link: https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk-netbeans-jsp-3413153-ptb.html

### 1. Após baixar e instalar , basta importar o projeto no NetBeans 8.2

![tutorialj1](https://user-images.githubusercontent.com/46981155/90416287-a82e4480-e088-11ea-8108-cee35a153710.png)
_________________________________________________________________________________________
### 2. Agora, procurar pelo projeto e seleciona-lo:

![tutorialj2](https://user-images.githubusercontent.com/46981155/90416321-b41a0680-e088-11ea-9c38-042f0ae79c19.png)
_________________________________________________________________________________________
### 3. Basta clicar no botão para executar o programa:

![tutorialj3](https://user-images.githubusercontent.com/46981155/90416354-bed49b80-e088-11ea-88aa-59960accdc67.png)
