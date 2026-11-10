package org.teste.service.CrawlerStrategy

import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class OLXStrat implements CrawlerStrategy {

    private boolean filtro(String produto){
        return true
    }

    @Override
    int scrape(Document document, List<String> alvos) {

        document.select(".olx-adcard__content").each { Element el ->
            String preco_string = el.select(".olx-adcard__price").text()
            String link_produto = el.select(".olx-adcard__link").attr("href")
            String titulo_produto = el.select(".olx-adcard__link").attr("title")
            String endereco_produto = el.select(".olx-adcard__location").text()

            boolean alvo = alvos.any { String alvo -> link_produto  ==~ alvo }
            if (!alvo) return

            println "${preco_string} - ${titulo_produto} (${endereco_produto}) | ${link_produto}"
        }

        return 0
    }
}
