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

        List<String> summaryTextList = List.of("a", "b", "c", "d", "e");

        String result = IntStream.range(0, summaryTextList.size())
                .mapToObj(i -> {
                    String text = summaryTextList.get(i);

                    // 最初!!
                    if (i == 0) {
                        return "first " + text;
                    }

                    // 最後
                    if (i == summaryTextList.size() - 1) {
                        return "first " + text + " last";
                    }

                    // 中間
                    return "middle " + text;
                }).reduce("", (x, y) -> x + y);

        return result;
    }
}
