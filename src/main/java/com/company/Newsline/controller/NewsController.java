package com.company.Newsline.controller;

import com.company.Newsline.entity.News;
import com.company.Newsline.model.NewsModel;
import com.company.Newsline.service.NewsService;
import com.company.Newsline.util.BasePattern;
import com.company.Newsline.util.Pager;
import com.company.Newsline.util.PaginationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;


    //saves or updates a news. NewsModel class is required to convert image to bytes array
    //accessible only for admin
    @PostMapping
    public String saveNews(@ModelAttribute("newsForm") NewsModel newsModel){
        newsService.saveOrUpdateNewsFromModel(newsModel);
        return "redirect:/news/newsV2";
    }

    //gets full info about selected news
    //and there is no availability to change the data of news on the page
    //accessible only for admin
    @GetMapping("/newsFullVersion/{newsId}")
    public String getNewsFullVersion(Model model, @PathVariable("newsId") Long newsId){
        model.addAttribute("news", newsService.findById(newsId));
        return "news/newsFullVersion";
    }

    //gets  full info about selected news
    //and there is no availability to change the data of news on the page
    //accessible for everyone
    //this page has style that is more appropriate for non-admin users
    @GetMapping("/newsFullVersionForAll/{newsId}")
    public String getNewsFullVersionForAll(Model model, @PathVariable("newsId") Long newsId){
        model.addAttribute("news", newsService.findById(newsId));
        return "news/newsFullVersionForAll";
    }

    //gets page for adding a news or for updating an existing news, it depends on the presence
    //of request parameter
    //accessible only for admin
    @GetMapping("/newsSaving")
    public String getNewsSavingForUpdate(Model model, @RequestParam(value = "newsId", required = false) Long newsId){
        model.addAttribute("news", newsId == null ? new News() : newsService.findById(newsId));
        return "news/newsSaving";
    }


    //gets list of news in style for admin
    //accessible only for admin
    @GetMapping("/newsV2")
    public ModelAndView getNews2(){
        return getModelAndView(new BasePattern(), "news/newsList");
    }

    //gets list of news in a non-admin style
    //accessible for anyone
    @GetMapping("/newsV2ForAll")
    public ModelAndView getNews2ForAll(){
        return getModelAndView(new BasePattern(), "news/newsListForAll");
    }

    @PostMapping(value = "/search")
    public ModelAndView search(@ModelAttribute BasePattern pattern) {
        return getModelAndView(pattern, "news/newsList");
    }

    @PostMapping(value = "/searchForAll")
    public ModelAndView searchForAll(@ModelAttribute BasePattern pattern) {
        return getModelAndView(pattern, "news/newsListForAll");
    }

    //deletes a news with newsId
    @GetMapping("/delete/{newsId}")
    public String  deleteNews(@PathVariable("newsId") Long newsId) {
        newsService.deleteNews(newsId);
        return "redirect:/news/newsV2";
    }

    //pagination
    private ModelAndView getModelAndView(BasePattern basePattern, String view) {
        ModelAndView modelAndView = new ModelAndView(view);
        int pageSize = basePattern.getPageSize() == null ?
                PaginationConstant.INITIAL_PAGE_SIZE : basePattern.getPageSize();
        int page = (basePattern.getPage() == null || basePattern.getPage() < 1) ?
                PaginationConstant.INITIAL_PAGE : basePattern.getPage() - 1;

        Page<News> news = newsService.getAllPaginatedNews(PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "id")));
        Pager pager = new Pager(news.getTotalPages(), news.getNumber(), PaginationConstant.BUTTONS_TO_SHOW);
        modelAndView.addObject("items", news);
        modelAndView.addObject("selectedPageSize", pageSize);
        modelAndView.addObject("pageSizes", PaginationConstant.PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("pattern", basePattern);
        return modelAndView;
    }

}
