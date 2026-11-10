package org.teste

import org.jsoup.nodes.Document
import org.jsoup.nodes.Element


import static groovyx.net.http.HttpBuilder.configure

class Crawler {
    final Set<String> visitado = [] as Set<String>

    List<String> interesses = []
    List<String> alvos = []
    String resultado_path = "../../../../../Resultado"

//    static Document request(String url){
//        try{
//            Document doc = configure {
//                request.uri = url
//                execution.proxy '188.164.201.197', 666, Proxy.Type.HTTP, true
//
//                request.headers['User-Agent'] = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36'
//                request.headers['Accept'] = 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8'
//                request.headers['Accept-Language'] = 'pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7'
//            } .get(Document) {}
//
//            return doc
//        } catch (Exception e){
//            e.printStackTrace()
//        }
//        return null
//    }
    static Document request(String url){
        String chave = "GRC92YKSUN8AVSFQ1EF1LJHRBAMQW07VXAAWBR4R7YYDAC2X9G2TQ1E9S3JME9EJQBYWZKS8MHPP2JHO"
        //String chave = "2HYQDC2QMVZQ2BQNSR0P9GS1A7CO03LYUDDBWWKI848HF52V8VU0QTSABWNLCMTGGNPMYYAY0XQWTRJY"
        try{
            String asdf = URLEncoder.encode(url, "UTF-8")
            String alvo = "https://app.scrapingbee.com/api/v1/?api_key=${chave}&url=${asdf}&render_js=true&premium_proxy=true&country_code=br"

            Document doc = configure {
                request.raw = alvo
            }.get(Document) {}

            return doc
        } catch (Exception e){
            e.printStackTrace()
        }
        return null
    }

    void crawl(String url, int max_depth = 0, int depth = 0){
        if (depth >= max_depth) return
        Document doc = request(url)
        if (doc == null) return
        visitado.add(url)


        doc.select(".olx-adcard__content").each { Element el ->
            String preco_string = el.select(".olx-adcard__price-info span span").text()
            if (preco_string == "" || preco_string == null){
                preco_string = el.select(".olx-adcard__price").text()
            }

            el.select("a[href]").each { Element subel -> String prod_link = subel.absUrl("href")
                boolean alvo = alvos.any {String alvo -> prod_link ==~ alvo}
                if (alvo) {
                    if (preco_string == "" || preco_string == null) println el
                    else println "${preco_string} | ${prod_link}"
                }
            }
        }

        doc.select("a[href]").each { Element el -> String next_link = el.absUrl("href")
            if (visitado.contains(next_link)) return

            boolean continuar = interesses.any {String interesse -> next_link ==~ interesse}
            if (!continuar) return

            println "\n/!\\encontrado proximo linl: ${next_link} /!\\\n"
            crawl(next_link, max_depth, depth+1);
        }

    }
}
