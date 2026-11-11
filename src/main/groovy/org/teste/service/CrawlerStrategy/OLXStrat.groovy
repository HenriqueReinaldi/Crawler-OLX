package org.teste.service.CrawlerStrategy

import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.teste.model.Produto
import org.teste.service.Fetcher

class OLXStrat implements CrawlerStrategy {
    private Fetcher fetcher

    private static boolean produto_valido(String produto){
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
            String link_produto = el.select(".olx-adcard__link").attr("href")
            String titulo_produto = el.select(".olx-adcard__link").attr("title")
            String preco_string = el.select(".olx-adcard__price").text()
            String endereco_produto = el.select(".olx-adcard__location").text()
            String parcelas_string = el.select(".olx-adcard__price-info-list span.typo-caption").text()
            String data = el.select(".olx-adcard__date").text()

            boolean alvo = alvos.any { String alvo -> link_produto  ==~ alvo }
            if (!alvo || ! produto_valido(titulo_produto)) return

            //fetcher.fetch(link_produto)

            int preco = preco_string.replace("R\$", "").trim().replace(".", "").replace(",", ".") as int
            int parcelas = parcelas_string != "" ? parcelas_string.substring(6, parcelas_string.indexOf("x")) as int : 0
            boolean parcelas_juros = !parcelas_string.contains("sem juros") && parcelas > 0

            Produto prod = new Produto(titulo_produto, preco, endereco_produto, link_produto, parcelas, parcelas_juros, data)
            prod.representar()
            produtos << prod
        }

        return produtos
    }

    OLXStrat(Fetcher fetcher) {
        this.fetcher = fetcher
    }

}
