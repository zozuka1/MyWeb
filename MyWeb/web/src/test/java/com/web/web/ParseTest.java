package com.web.web;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseTest {
    private Document document;
    private Element element;
    public String getName;
    public String getImage;
    public String getArtist;
    public static String FILE_URL;
    public static String PLAY_URL;
    public static String PLAY_URL_2;
    public static String PLAY_URL_3;
    public static String IMG_URL;