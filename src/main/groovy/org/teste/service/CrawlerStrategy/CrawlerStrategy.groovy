package org.teste.service.CrawlerStrategy

import org.jsoup.nodes.Document

interface CrawlerStrategy {
    int scrape(Document document, List<String> alvos)
}