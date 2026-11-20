package org.teste.service.FetcherStrategy

import org.jsoup.nodes.Document

interface Fetcher {
    Document fetch(String url, int tentativa)
}