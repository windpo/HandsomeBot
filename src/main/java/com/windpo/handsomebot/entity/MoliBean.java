package com.windpo.handsomebot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: windpo
 * @DateTime: 2022/9/15 16:08
 **/
@NoArgsConstructor
@Data
public class MoliBean {

    @JsonProperty("code")
    private String code;
    @JsonProperty("message")
    private String message;
    @JsonProperty("plugin")
    private Object plugin;
    @JsonProperty("data")
    private List<DataDTO> data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("content")
        private String content;
        @JsonProperty("typed")
        private Integer typed;
        @JsonProperty("remark")
        private Object remark;
    }
}

