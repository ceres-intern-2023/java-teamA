package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NewsDto {
    @JsonProperty("ids")
    public List<Integer> ids;
}
