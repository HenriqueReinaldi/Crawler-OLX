package org.teste.service

import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.teste.model.Produto

import static org.jsoup.Jsoup.connect

import org.teste.service.CrawlerStrategy.CrawlerStrategy


class Crawler {
    private Set<String> visitado = [] as Set<String>

    private List<String> interesses = []
    private List<String> alvos = []
    private CrawlerStrategy crawler_strategy
    private int max_depth
    private int max_retry

    Document request(String url, int tentativa = 1) {
        if (tentativa > max_retry) return null
        if (tentativa > 1) Thread.sleep(1000)

        try {
            Document resultado = connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                    .header("Accept-Language", "pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
                    .timeout(30000)
                    .get()

            println "/!\\ SUCESSO PEGANDO LINK ${url}"
            return resultado
        } catch (Exception e) {
            println "\n/!\\ DEU ERRO COM LINK ${url} : ${e.message}.... TENTANDO DE NOVO! (${tentativa}/${max_retry})/!\\\n"
            return request(url, tentativa+1)
        }
    }

    public List<Produto> produtos = []
    void crawl(String url, int depth = 0) {
        if (depth >= max_depth) return
        Document doc = request(url)
        if (doc == null) return
        visitado.add(url)

        produtos.addAll crawler_strategy.scrape(doc, alvos)

        doc.select("a[href]").each { Element el ->
            String next_link = el.absUrl("href")
            if (visitado.contains(next_link)) return

            boolean continuar = interesses.any { String interesse -> next_link ==~ interesse }
            if (!continuar) return

            println "/!\\ encontrado proximo INTERESSE: ${next_link}"
            crawl(next_link, depth + 1);
        }
    }

    Crawler(CrawlerStrategy crawler_strategy, List<String> interesses = [], List<String> alvos = [], int max_depth = 50) {
        this.crawler_strategy = crawler_strategy
        this.interesses = interesses
        this.alvos = alvos
        this.max_depth = max_depth
        this.max_retry = 5
    }
}
