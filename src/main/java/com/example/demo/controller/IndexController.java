package com.example.demo.controller;

import com.example.demo.dto.NewsDto;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api")
@RestController
public class IndexController {
    Map<Integer,String> newsMap = new HashMap<Integer, String>(){{
        put(0,"");
        put(1,"");
        put(2,"");
        put(3,"");
        put(4,"");
    }};

    @GetMapping
    public String index() {
        return "hello springboot 3";
    }

    @PostMapping("/news")
    public String news(@RequestBody NewsDto param) {
        System.out.println(param.ids);

        //TODO: 取得したIDを元にRSSからニュース記事を引っ張ってくる

        String[] newsText = {"ニュース本文1","ニュース本文2","ニュース本文3"};

        //TODO: newsTextの中にある本文をChatGPTに送信し，要約したものを返す

        String[] summaryText = {"ニュース要約文1","ニュース要約文2","ニュース要約文3"};

        //TODO: 要約された文章を結合し，返す．

        return "POST /api/news";
    }
}
