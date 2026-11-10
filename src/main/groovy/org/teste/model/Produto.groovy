package org.teste.model

import groovy.transform.TupleConstructor

@TupleConstructor
class Produto {
    String titulo
    String preco
    String endereco
    String link

    void remover_virgulas_atributos(){
        titulo = titulo.replace("," , ".")
        preco = preco.replace("," , ".")
        endereco = endereco.replace("," , ".")
        link = link.replace("," , "%2C")
    }
}
