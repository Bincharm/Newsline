package com.company.Newsline.controller;

import com.company.Newsline.entity.News;
import com.company.Newsline.model.NewsModel;
import com.company.Newsline.service.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

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
    public String saveNews(@ModelAttribute("newsForm") NewsModel newsModel){
        newsServiceImpl.saveNewsFromModel(newsModel);
        return "redirect:/news";
    }

    @GetMapping("/newsFullVersion/{newsId}")
    public String getNewsFullVersion(Model model, @PathVariable("newsId") Long newsId){
        model.addAttribute("fullNews", newsServiceImpl.findById(newsId));
        return "newsFullVersion";
    }

    @GetMapping("/newsSaving/{newsId}")
    public String getNewsSavingForUpdate(Model model, @PathVariable("newsId") Long newsId){
        model.addAttribute("news", newsServiceImpl.findById(newsId));
        return "newsSaving";
    }

    @GetMapping("/newsSaving")
    public String getNewsSavingForCreate(){
        return "newsSaving";
    }


}
