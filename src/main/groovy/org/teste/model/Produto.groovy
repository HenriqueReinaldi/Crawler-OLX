package org.teste.model

import groovy.transform.TupleConstructor

@TupleConstructor
class Produto {
    String titulo
    int preco
    String endereco
    String link
    int parcelas
    String data_anuncio

    String descricao

    void remover_virgulas_atributos(){
        titulo = titulo.replace("," , ".")
        endereco = endereco.replace("," , ".")
        link = link.replace("," , "%2C")
    }
    
    void representar(){
        println "${titulo}"
        println "    preço: ${preco} | parcelas: ${parcelas}"
        println "    endereco: ${endereco} | data: ${data_anuncio}"
        println "    link: ${link}\n"
    }
    
}
