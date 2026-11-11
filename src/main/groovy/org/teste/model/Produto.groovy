package org.teste.model

import groovy.transform.TupleConstructor

@TupleConstructor
class Produto {
    String titulo
    int preco
    String endereco
    String link
    int parcelas
    boolean juros
    String data_anuncio

//    String descricao
//    String detalhes //(Cor, modelo)
//    String info_local // (CEP, muninm bairro...)

    //Valor de entrega do produto baseado no CEP
    //DIas uteis da entrega do produto baseado no CEP

    void remover_virgulas_atributos(){
        titulo = titulo.replace("," , ".")
        endereco = endereco.replace("," , ".")
        link = link.replace("," , "%2C")
    }
    
    void representar(){
        println "${titulo}"
        println "    preço: ${preco} | parcelas: ${parcelas} | juros: ${juros}"
        println "    endereco: ${endereco} | data: ${data_anuncio}"
        println "    link: ${link}\n"
    }
    
}
