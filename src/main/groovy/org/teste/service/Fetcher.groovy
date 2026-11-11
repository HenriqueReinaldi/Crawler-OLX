package org.teste.service

import org.jsoup.nodes.Document
import org.teste.view.View

import static org.jsoup.Jsoup.connect

class Fetcher {
    private View view
    private int max_retry = 5

    Document fetch(String url, int tentativa = 1) {
        if (tentativa > max_retry) return null
        if (tentativa > 1) Thread.sleep(1000)

        try {
            Document resultado = connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                    .header("Accept-Language", "pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
                    .timeout(30000)
                    .get()

            view.message "/!\\ SUCESSO PEGANDO LINK ${url}"
            return resultado
        } catch (Exception e) {
            view.message "\n/!\\ DEU ERRO COM LINK ${url} : ${e.message}.... TENTANDO DE NOVO! (${tentativa}/${max_retry})/!\\\n"
            return request(url, tentativa+1)
        }
    }

    Fetcher(View view, max_retry = 5) {
        this.view = view
        this.max_retry = max_retry
    }
}
