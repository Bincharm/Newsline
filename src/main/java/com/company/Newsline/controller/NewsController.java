package com.company.Newsline.controller;

import com.company.Newsline.entity.News;
import com.company.Newsline.server.NewsService;
import com.company.Newsline.server.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

//    @RequestMapping(value = "/news-list/page/{page}")
//    public ModelAndView listArticlesPageByPage(@PathVariable("page") int page) {
//        ModelAndView modelAndView = new ModelAndView("news-list-paging");
//        PageRequest pageable = PageRequest.of(page - 1, 15);
//        Page<News> newsPage = newsService.getPaginatedArticles(pageable);
//        int totalPages = newsPage.getTotalPages();
//        if(totalPages > 0) {
//            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
//            modelAndView.addObject("pageNumbers", pageNumbers);
//        }
//        modelAndView.addObject("activeArticleList", true);
//        modelAndView.addObject("newsList", newsPage.getContent());
//        return modelAndView;
//    }



}
