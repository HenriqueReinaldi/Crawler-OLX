package org.teste

import org.teste.service.Crawler
import org.teste.service.CrawlerStrategy.OLXStrat

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

    new Crawler(new OLXStrat(), interesses, alvos)
            .crawl("https://www.olx.com.br/celulares/estado-go/goiania?q=iphone+11&sp=1&o=1")
}