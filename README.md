Este repositório contém um simples bot para coletar dados do site do governo na página da Agência Nacional de Saúde.

### Explicação: 

Para as tarefas necessárias, tentei criar um sistema "generalizado"<br>
O crawler principal segue o princípio de DFS, mas visita apenas URLS permitidos, definidos como "interesses". Temos também URLS "alvos", esses quando encontrados pelo crawler sofrem alguma ação:
    
1. Download -> Autoexplicativo; O nome do arquivo é definido por uma expressão regex que pega tudo depois da ultima barra do URL:

        URL : https://www.gov.br/ans/pt-br/assuntos/prestadores/arquivo_txt.zip

        nome: arquivo_txt.zip

2. Tabulação:

    Encontra a primeira tabela do URL e processa de acordo com:<br>
    1. Colunas Interesse:<br> Pega apenas as colunas da tabela que servem algum valor<br><br>
    2. Processar tabela :<br> Uma closure que recebe como entrada a tabela(pós seleção de colunas interesse) para modificações mais "complexas", como pegar todas as entradas onde o valor da coluna "competência" é a partir de "jan/2016"


Tanto URLS alvos como interesses são definidos por expressões REGEX