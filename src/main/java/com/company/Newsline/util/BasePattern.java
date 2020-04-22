package com.company.Newsline.util;

import lombok.Data;

@Data
public class BasePattern {
    private Long id;
    private Integer pageSize;
    private Integer page;
    private String name;
    private String answer;
    private String statusRequest;
}
