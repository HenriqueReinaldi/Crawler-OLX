package org.teste

//henrique de figueiredo reinaldi

import org.teste.persistence.Armazenar
import org.teste.service.Crawler
import org.teste.service.CrawlerStrategy.OLXStrat

class Main {
    static void main(String[] args) {
        task_um()
    }

    static void task_um(){
        List<String> interesses = [
            /https:\/\/www\.olx\.com\.br\/celulares\/estado-go\/goiania\?q=iphone\+11&sp=1&o=2/,
            /https:\/\/www\.olx\.com\.br\/celulares\/estado-go\/goiania\?q=iphone\+11&sp=1&o=3/,
        ]
        List<String> alvos = [
            /^https:\/\/go\.olx\.com\.br\/grande-goiania-e-anapolis\/celulares\/.*(?i:iphone-11).*/
        ]
        String url = "https://www.olx.com.br/celulares/estado-go/goiania?q=iphone+11&sp=1&o=1"

        Crawler crawler = new Crawler(new OLXStrat(), interesses, alvos)
        crawler.crawl(url)
        new Armazenar(crawler.produtos)
    }
}
