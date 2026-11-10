package org.teste.service.CrawlerStrategy

import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class OLXStrat implements CrawlerStrategy {

    @Override
    int scrape(Document document, List<String> alvos) {

        document.select(".olx-adcard__content").each { Element el ->
            String preco_string = el.select(".olx-adcard__price").text()

            el.select("a[href]").each { Element subel ->
                String prod_link = subel.absUrl("href")
                boolean alvo = alvos.any { String alvo -> prod_link ==~ alvo }
                if (alvo) {
                    if (preco_string == "" || preco_string == null) println el
                    else println "${preco_string} | ${prod_link}"
                }
            }
        }

        return 0
    }
}
