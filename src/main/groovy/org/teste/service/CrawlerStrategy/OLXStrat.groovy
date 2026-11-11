package org.teste.service.CrawlerStrategy

import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.teste.model.Produto
import org.teste.service.Fetcher

class OLXStrat implements CrawlerStrategy {
    private Fetcher fetcher

    private static boolean filtro(String produto){
        //tem "iphone 11", nao tem "carcaça", nao tem "caixa" ou "cabo" como prefixo
        String filtro = /(?i)^(?!.*carcaça)(?=.*iphone 11)(?!.*(?:caixa|cabo).*iphone 11).*/
        boolean match = (produto =~ filtro).find()
        //println match.toString() + " " +  produto
        return match
    }

    @Override
    List<Produto> scrape(Document document, List<String> alvos) {
        List<Produto> produtos = []

        document.select(".olx-adcard__content").each { Element el ->
            String preco_string = el.select(".olx-adcard__price").text()
            String link_produto = el.select(".olx-adcard__link").attr("href")
            String titulo_produto = el.select(".olx-adcard__link").attr("title")
            String endereco_produto = el.select(".olx-adcard__location").text()

            boolean alvo = alvos.any { String alvo -> link_produto  ==~ alvo }
            if (!alvo) return

            boolean valido = filtro(titulo_produto)
            if (!valido) return

            fetcher.fetch(link_produto)

            produtos << new Produto(titulo_produto, preco_string, endereco_produto, link_produto)
        }

        return produtos
    }

    OLXStrat(Fetcher fetcher) {
        this.fetcher = fetcher
    }

}
