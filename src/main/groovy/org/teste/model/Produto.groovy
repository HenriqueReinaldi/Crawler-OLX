package org.teste.model

import groovy.transform.TupleConstructor

@TupleConstructor
class Produto {
    String titulo
    int preco
    String endereco
    String link
    String parcelas
    String data_anuncio

    String descricao

    void remover_virgulas_atributos(){
        titulo = titulo.replace("," , ".")
        endereco = endereco.replace("," , ".")
        link = link.replace("," , "%2C")
    }
    
    void representar(){
        println "${titulo} - [${preco}]"
    }
    
}
