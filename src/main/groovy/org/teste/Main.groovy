package org.teste

import org.teste.model.Produto
import org.teste.persistence.Armazenar

//henrique de figueiredo reinaldi

import org.teste.service.Crawler
import org.teste.service.CrawlerStrategy.OLXStrat
import org.teste.service.Fetcher
import org.teste.view.View

class Main {
    static View view = new View()
    static Fetcher fetcher = new Fetcher(view)

    static void main(String[] args) {
        task_um()
    }

    static void task_um(){
        List<String> interesses = [
            /https:\/\/www\.olx\.com\.br\/estado-go\/goiania\?q=iphone\+11&sp=1&o=2/,
            /https:\/\/www\.olx\.com\.br\/estado-go\/goiania\?q=iphone\+11&sp=1&o=3/,
        ]
        List<String> alvos = [
            /^https:\/\/go\.olx\.com\.br\/grande-goiania-e-anapolis\/celulares\/.*(?i:iphone-11).*/
        ]
        String url = "https://www.olx.com.br/estado-go/goiania?q=iphone+11&sp=1&o=1"

        Crawler crawler = new Crawler(view, fetcher, new OLXStrat(fetcher), interesses, alvos)
        crawler.crawl(url)

        //int media = crawler.produtos.sum { Produto p -> p.preco } / crawler.produtos.size()
        //crawler.produtos = crawler.produtos.findAll { Produto p -> p.preco <= media }
//
//        crawler.produtos.each {Produto p ->
//            p.representar()
//        }
        //new Armazenar(view, crawler.produtos)
    }
}
