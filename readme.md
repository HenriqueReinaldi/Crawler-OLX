## Crawler OLX

Projeto simples que busca por celulares (iphone 11) na OLX e coloca os resultados em um CSV.


### Rodar:

    ./gradlew run

### Solução:

O crawler tem 3 caracteristicas principais:

    links de interesse -> links que são acessados quando o crawler encontra
    links alvos -> links que o crawler usa para aplicar a estratégia de coleta
    estrategia de coleta -> algoritmo usado para pegar um produto

Antes de iniciar uma busca em um link "raíz", são definidas expressões regulares para tanto links de interesse quanto links alvos. Também é selecionada a estratégia de coleta. <br>
Quando iniciado, o algoritmo executa os seguintes passos:

    1. Acessar o link dado
        1.1 - Se nao encontrar problemas, retorne o conteúdo do link.
        1.2 - Caso contrário, continue tentando pegar o conteúdo por no máximo mais 4 vezes.

    2. Se conseguir acessar o link, anotá-lo para não repetir visitas.

    3. Aplicar a estratégia de coleta no documento e armazenar resultados
        2.1 - No caso, a estratégia procura por cards de produtos e confirma se os dados são válidos comparando o link do produto com os links alvos.

    4. Procurar por links de interesse no documento, para cada encontrado, faça uma chamada recursiva.

Por fim, é construído um arquivo CSV com os resultados da busca.