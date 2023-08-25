package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.IntStream;

@RequestMapping("/api")
@RestController
public class TmpController {
    @GetMapping("tmp")
    public String tmp() {

        List<String> summaryTextList = List.of("a", "b", "c" );

        String result = IntStream.range(0, summaryTextList.size())
                .mapToObj(i -> {
                    String text = summaryTextList.get(i);

                    // 最初!!
                    if (i == 0) {
                        return "最初のニュースです, " + text;
                    }

                    // 最後
                    if (i == summaryTextList.size() - 1) {
                        return "次のニュースです," + text + " これでニュースを終わります.";
                    }

                    // 中間
                    return "次のニュースです, " + text;
                }).reduce("", (x, y) -> x + y);

        return result;
    }
}
