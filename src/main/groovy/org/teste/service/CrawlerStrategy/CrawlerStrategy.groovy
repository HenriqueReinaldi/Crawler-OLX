package org.teste.service.CrawlerStrategy

import org.jsoup.nodes.Document
import org.teste.model.Produto

interface CrawlerStrategy {
    List<Produto> scrape(Document document, List<String> alvos)
}