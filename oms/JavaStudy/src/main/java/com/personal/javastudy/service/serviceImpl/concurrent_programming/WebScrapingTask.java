package com.personal.javastudy.service.serviceImpl.concurrent_programming;

import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

@AllArgsConstructor
public class WebScrapingTask implements Callable<String> {
    private final String url;

    @Override
    public String call() throws Exception {
        String htmlContent = fetchHTML(url);
        return parseTitle(htmlContent);
    }

    private String fetchHTML(String urlString) throws Exception {
        // Open a connection to the URL
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        // Read the response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    private String parseTitle(String htmlContent) {
        // Use Jsoup to parse HTML and extract the title
        Document document = Jsoup.parse(htmlContent);
        return document.title();
    }
}

