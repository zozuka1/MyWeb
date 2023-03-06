package com.web.web.domain;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SearchUsers {
    private Document document;
    private Element element;
    public String getName;
    public String getImage;
    public static String valid_URL;
    public static String FILE_URL;
    public static String PLAY_URL;
    public static String PLAY_URL_2;
    public static String PLAY_URL_3;
    public static String IMG_URL;
    public static String IMG_NAME = "";
    public static String FILE_NAME = "";

    public void getSearch(String TRACK_NAME, int index) throws IOException {

        try {
            document = Jsoup.connect
                    ("https://ru.hitmotop.com/search?q=" + TRACK_NAME).get();
            Elements anchors = document.getElementsByClass("track__download-btn");

            String links = anchors.attr("href");
            FILE_URL = links;
            String tepm = FILE_URL.replace("https://ru.hitmotop.com/","");
            PLAY_URL = "https://cdn3.deliciouspeaches.com/"+tepm;
            PLAY_URL_3 = "https://cdn2.deliciouspeaches.com/"+tepm;
            PLAY_URL_2 = "https://ds.cdn1.deliciouspeaches.com/"+tepm;
            //System.out.println("--> " + tepm);
            Elements anchors1 = document.getElementsByClass("track__title");
            getName = anchors1.eachText().get(index);
            Elements anchors2 = document.getElementsByClass("track__desc");
            Elements anchors3 = document.getElementsByClass("track__img");
            String img = anchors3.attr("style");
            String newStr = img.replaceAll("background-image: url", "");
            String imgCut = newStr.replaceAll("\\p{P}", "/");

            String cut = "https://ru.hitmotop.com" + imgCut.substring(2, 25) + ".jpg";
            IMG_URL = cut;
            //System.out.println(cut);
            System.out.println(FILE_URL + " --> " + " " + getName);
            try (BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME)) {
                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            } catch (IOException e) {
                // handle exception
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println("200OK");
        }

    }
    public void validator() throws IOException {
        String s = "200";
        URL url = new URL(PLAY_URL);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        String statusCode = String.valueOf(http.getResponseCode());
        System.out.println("server 1:" + statusCode);
        if (statusCode != s) {
            URL url2 = new URL(PLAY_URL_2);
            HttpURLConnection http2 = (HttpURLConnection) url2.openConnection();
            String statusCode2 = String.valueOf(http2.getResponseCode());
            System.out.println("server 2:" + statusCode2);
            valid_URL = PLAY_URL_2;
            if (statusCode2 != s) {
                URL url3 = new URL(PLAY_URL_3);
                HttpURLConnection http3 = (HttpURLConnection) url3.openConnection();
                String statusCode3 = String.valueOf(http3.getResponseCode());
                System.out.println("server 3:" + statusCode3);
                valid_URL = PLAY_URL_3;
            }
        }
    }
}
