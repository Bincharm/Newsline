package com.company.Newsline.controller;

import com.company.Newsline.entity.News;
import com.company.Newsline.server.NewsService;
import com.company.Newsline.server.NewsServiceImpl;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsServiceImpl newsServiceImpl;

    @GetMapping
    public String newsList(Model model){
        model.addAttribute("allNews", newsServiceImpl.findAllNews());
        return "news";
    }

    @PostMapping
    public String saveNews(@ModelAttribute("newsForm") @Valid News newsForm, @RequestParam("image") MultipartFile image){
        newsServiceImpl.saveNews(newsForm, image);
        return "redirect:/news";
    }

    @GetMapping("/newsFullVersion/{newsId}")
    public String getNewsFullVersion(Model model, @PathVariable("newsId") Long newsId){
        model.addAttribute("fullNews", newsServiceImpl.findById(newsId));
        return "newsFullVersion";
    }


}
