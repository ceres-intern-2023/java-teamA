package com.example.demo.controller;

import com.example.demo.dto.NewsDto;
import lombok.Data;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.net.URL;
import java.util.*;
import java.util.stream.IntStream;

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

    private static final String CHATGPT_URL = "https://api.openai.com/v1/chat/completions"; // URLはOpenAIのドキュメントに基づいているので、最新のものを確認してください。
    static final String API_KEY = System.getenv("API_KEY");

    @GetMapping
    public String index() {
        return "hello springboot 3";
    }

    @PostMapping("/news")
    public String news(@RequestBody NewsDto param) throws Exception {
        System.out.println(param.ids);

        List<String> newsTexts = new ArrayList<>();

        for(int id : param.ids){
            String url = newsMap.get(id);
            SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
            for(int i=0;i<3;i++) {
                String description = feed.getEntries().get(i).getDescription().getValue();
                description = description.replaceAll("<.*?>", "");
                description = description.replaceAll("&nbsp;", "");
                description = description.replaceAll("Google ニュースですべての記事を見る", "");
                newsTexts.add(description);
            }
        }

        List<String> summaryTexts = new ArrayList<>();
        for(String text:newsTexts){
            summaryTexts.add(getSummaryText(text));
        }

        String result = IntStream.range(0, summaryTexts.size())
                .mapToObj(i -> {
                    String text = summaryTexts.get(i);

                    // 最初!!
                    if (i == 0) {
                        return "first " + text;
                    }

                    // 最後
                    if (i == summaryTexts.size() - 1) {
                        return "first " + text + " last";
                    }

                    // 中間
                    return "middle " + text;
                }).reduce("", (x, y) -> x + y);

        return result;
    }

    public String getSummaryText(String rawArticle) {
        System.out.println("YO!");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(API_KEY);

        String article = rawArticle.replace("\n", ""); // TODO substringで文字数制限をする("message": "This model's maximum context length is 4097 tokens. However, your messages resulted in 4267 tokens. Please reduce the length of the messages.")

        String requestBody = """
                   {
                     "model": "gpt-3.5-turbo",
                     "messages": [
                         {"role": "user", "content": "次の文を要約して%s"}
                     ],
                     "temperature": 0.7
                   }
                """.formatted(article);;
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Response> response = restTemplate.postForEntity(CHATGPT_URL, entity, Response.class);

        System.out.println("HO!");

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().choices.get(0).message.content;
        } else {
            throw new RuntimeException("Failed to get response from ChatGPT API: " + response.getStatusCode());
        }
    }

    @Data
    static class Choice {
        Message message;
    }

    @Data
    static class Message {
        String content;
    }

    @Data
    static class Response {
        String id;
        List<Choice> choices ;
    }
}
