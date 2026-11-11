package org.teste.model

import groovy.transform.TupleConstructor

@TupleConstructor
class Produto {
    String titulo
    String preco
    String endereco
    String link
    String data_anuncio
    String descricao
    int quantia_parcelas = 0

    void remover_virgulas_atributos(){
        titulo = titulo.replace("," , ".")
        preco = preco.replace("," , ".")
        endereco = endereco.replace("," , ".")
        link = link.replace("," , "%2C")
    }
}
