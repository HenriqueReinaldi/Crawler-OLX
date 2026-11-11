package org.teste.service

import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.teste.model.Produto
import org.teste.view.View

import org.teste.service.CrawlerStrategy.CrawlerStrategy


class Crawler {
    private View view
    private Fetcher fetcher

    private Set<String> visitado = [] as Set<String>
    private List<String> interesses = []
    private List<String> alvos = []
    private CrawlerStrategy crawler_strategy
    private int max_depth

    public List<Produto> produtos = []
    void crawl(String url, int depth = 0) {
        if (depth >= max_depth) return
        Document doc = fetcher.fetch(url)
        if (doc == null) return
        visitado.add(url)

        produtos.addAll crawler_strategy.scrape(doc, alvos)

        doc.select("a[href]").each { Element el ->
            String next_link = el.absUrl("href")
            if (visitado.contains(next_link)) return

            boolean continuar = interesses.any { String interesse -> next_link ==~ interesse }
            if (!continuar) return

            view.message "/!\\ encontrado proximo INTERESSE: ${next_link}"
            crawl(next_link, depth + 1);
        }
    }

    Crawler(View view, Fetcher fetcher, CrawlerStrategy crawler_strategy, List<String> interesses = [], List<String> alvos = [], int max_depth = 50) {
        this.view = view
        this.fetcher = fetcher

        this.crawler_strategy = crawler_strategy
        this.interesses = interesses
        this.alvos = alvos
        this.max_depth = max_depth
    }
}
