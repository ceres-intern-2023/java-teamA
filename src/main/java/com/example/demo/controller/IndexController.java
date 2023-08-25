package com.example.demo.controller;

import com.example.demo.dto.NewsDto;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api")
@RestController
public class IndexController {
    Map<Integer,String> newsMap = new HashMap<Integer, String>(){{
        put(0,"https://news.google.com/news/rss/headlines/section/topic/WORLD?hl=ja&gl=JP&ceid=JP:ja");
        put(1,"https://news.google.com/news/rss/headlines/section/topic/NATION?hl=ja&gl=JP&ceid=JP:ja");
        put(2,"https://news.google.com/news/rss/headlines/section/topic/BUSINESS?hl=ja&gl=JP&ceid=JP:ja");
        put(3,"https://news.google.com/news/rss/headlines/section/topic/TECHNOLOGY?hl=ja&gl=JP&ceid=JP:ja");
        put(4,"https://news.google.com/news/rss/headlines/section/topic/ENTERTAINMENT?hl=ja&gl=JP&ceid=JP:ja");
        put(5,"https://news.google.com/news/rss/headlines/section/topic/SPORTS?hl=ja&gl=JP&ceid=JP:ja");
        put(6,"https://news.google.com/news/rss/headlines/section/topic/SCIENCE?hl=ja&gl=JP&ceid=JP:ja");
        put(7,"https://news.google.com/news/rss/headlines/section/topic/HEALTH?hl=ja&gl=JP&ceid=JP:ja");
    }};

    @GetMapping
    public String index() {
        return "hello springboot 3";
    }

    @PostMapping("/news")
    public String news(@RequestBody NewsDto param) throws Exception {
        System.out.println(param.ids);

        List<String> newsText = new ArrayList<>();

        for(int id : param.ids){
            String url = newsMap.get(id);
            SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
            for(int i=0;i<3;i++) {
                String description = feed.getEntries().get(i).getDescription().getValue();
                description = description.replaceAll("<.*?>", "");
                description = description.replaceAll("&nbsp;", "");
                description = description.replaceAll("Google ニュースですべての記事を見る", "");
                newsText.add(description);
            }
        }

        //TODO: newsTextの中にある本文をChatGPTに送信し，要約したものを返す

        String[] summaryText = {"ニュース要約文1","ニュース要約文2","ニュース要約文3"};

        //TODO: 要約された文章を結合し，返す．

        return "POST /api/news";
    }
}
