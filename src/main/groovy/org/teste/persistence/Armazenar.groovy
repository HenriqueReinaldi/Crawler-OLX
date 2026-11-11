package org.teste.persistence

import org.teste.model.Produto
import org.teste.view.View

class Armazenar {
    private View view
    private List<Produto> produtos
    private String arquivo_path

    void criar_csv(){
        File arquivo = new File(arquivo_path)
        arquivo.parentFile?.mkdirs()
        arquivo.text = ""

        arquivo << "titulo,valor,endereço,link\n"

        produtos.each { Produto p ->
            p.remover_virgulas_atributos()
            arquivo << "${p.titulo},${p.preco},${p.endereco},${p.link}\n"
        }
    }

    Armazenar(View view, List<Produto> produtos, nome = "celulares.csv", path = "resultado") {
        this.view = view
        this.produtos = produtos
        this.arquivo_path = path + "/" + nome

        if (produtos.size() > 0) criar_csv()
    }
}
