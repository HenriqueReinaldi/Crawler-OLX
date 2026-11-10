package org.teste.persistence

import org.teste.model.Produto

class Armazenar {
    private List<Produto> produtos
    private String arquivo_path

    void criar_csv(){
        File arquivo = new File(arquivo_path)
        arquivo.parentFile?.mkdirs()
        arquivo.text = ""

        arquivo << "titulo,valor,endereço,link\n"

        produtos.each { Produto p ->
            p.remover_virgulas_atributos()
            println "${p.titulo} - ${p.preco} || ${p.endereco} || ${p.link}"
            arquivo << "${p.titulo},${p.preco},${p.endereco},${p.link}\n"
        }
    }

    Armazenar(List<Produto> produtos, nome = "celulares.csv", path = "../../../../../resultado") {
        this.produtos = produtos
        this.arquivo_path = path + "/" + nome

        criar_csv()
    }
}
