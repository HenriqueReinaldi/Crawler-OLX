package org.teste.service.FetcherStrategy

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.teste.view.View

class SeleniumFetcher implements Fetcher{
    private View view
    private int max_retry = 5
    private WebDriver driver

    @Override
    Document fetch(String url, int tentativa = 1) {
        if (tentativa > max_retry) return null
        if (tentativa > 1) Thread.sleep(1000)

        try {
            driver.get(url)
            Document resultado = Jsoup.parse(driver.getPageSource(), driver.getCurrentUrl())

            view.message "/!\\ SUCESSO PEGANDO LINK ${url}"
            return resultado
        } catch (Exception e) {
            view.message "\n/!\\ DEU ERRO COM LINK ${url} : ${e.message}.... TENTANDO DE NOVO! (${tentativa}/${max_retry})/!\\\n"
            return fetch(url, tentativa+1)
        }
    }

    SeleniumFetcher(View view, max_retry = 5) {
        ChromeOptions opcoes = new ChromeOptions()
        opcoes.setPageLoadStrategy(PageLoadStrategy.EAGER)
        driver = new ChromeDriver(opcoes)

        this.view = view
        this.max_retry = max_retry
    }

    void quit(){
        driver.quit()
    }
}
