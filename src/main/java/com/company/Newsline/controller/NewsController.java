package com.company.Newsline.controller;

import com.company.Newsline.entity.News;
import com.company.Newsline.model.NewsModel;
import com.company.Newsline.service.NewsServiceImpl;
import com.company.Newsline.util.BasePattern;
import com.company.Newsline.util.PageWrapper;
import com.company.Newsline.util.Pager;
import com.company.Newsline.util.PaginationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsServiceImpl newsServiceImpl;

//    @GetMapping
//    public String newsList(Model model, Pageable pageable){
//        PageWrapper<News> page = new PageWrapper<News>(newsServiceImpl.getAllPaginatedNews(pageable), "/news");
//        model.addAttribute("allNews", newsServiceImpl.findAllNews());
//        model.addAttribute("newsPage", page);
//        return "news";
//    }

//    @GetMapping
//    public ModelAndView newsList(@RequestParam(required = false) Integer page) {
//        ModelAndView modelAndView = new ModelAndView("news.jsp");
//
//
//        Pageable paging = PageRequest.of(page == null ?  0 : page, 2, Sort.by("id"));
////        List<News> news = newsServiceImpl.findAllNews();
//        Page<News> news = newsServiceImpl.getAllPagedNews(paging);
//
//        modelAndView.addObject("allNews", news.getContent());
//
//
//
//        PagedListHolder<News> pagedListHolder = new PagedListHolder<>(news.getContent());
//        pagedListHolder.setPageSize(2);
//        modelAndView.addObject("maxPages", news.getTotalPages());
//
//        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;
//
//        modelAndView.addObject("page", page);
//        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
//            pagedListHolder.setPage(0);
//            modelAndView.addObject("news", pagedListHolder.getPageList());
//        }
//        else if(page <= pagedListHolder.getPageCount()) {
//            pagedListHolder.setPage(page-1);
//            modelAndView.addObject("news", pagedListHolder.getPageList());
//        }
//
//        return modelAndView;
//    }

    @PostMapping
    public String saveNews(@ModelAttribute("newsForm") NewsModel newsModel){
        newsServiceImpl.saveOrUpdateNewsFromModel(newsModel);
        return "redirect:/news/newsV2";
    }

    @GetMapping("/newsFullVersion/{newsId}")
    public String getNewsFullVersion(Model model, @PathVariable("newsId") Long newsId){
        model.addAttribute("news", newsServiceImpl.findById(newsId));
        return "news/newsFullVersion";
    }

    @GetMapping("/newsFullVersionForAll/{newsId}")
    public String getNewsFullVersionForAll(Model model, @PathVariable("newsId") Long newsId){
        model.addAttribute("news", newsServiceImpl.findById(newsId));
        return "news/newsFullVersionForAll";
    }

    @GetMapping("/newsSaving")
    public String getNewsSavingForUpdate(Model model, @RequestParam(value = "newsId", required = false) Long newsId){
        model.addAttribute("news", newsId == null ? new News() : newsServiceImpl.findById(newsId));
        return "news/newsSaving";
    }


//    @GetMapping("/newsSaving")
//    public String getNewsSavingForCreate(Model model){
//        model.addAttribute("news", new Ne)
//        return "news/newsSaving";
//    }

    @GetMapping("/newsV2")
    public ModelAndView getNews2(){
        return getModelAndView(new BasePattern(), "news/newsList");
    }

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

    @GetMapping("/delete/{newsId}")
    public String  deleteNews(@PathVariable("newsId") Long newsId) {
        newsServiceImpl.deleteNews(newsId);
        return "redirect:/news/newsV2";
    }

    private ModelAndView getModelAndView(BasePattern basePattern, String view) {
        ModelAndView modelAndView = new ModelAndView(view);
        int pageSize = basePattern.getPageSize() == null ?
                PaginationConstant.INITIAL_PAGE_SIZE : basePattern.getPageSize();
        int page = (basePattern.getPage() == null || basePattern.getPage() < 1) ?
                PaginationConstant.INITIAL_PAGE : basePattern.getPage() - 1;

        Page<News> news = newsServiceImpl.getAllPaginatedNews(PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "id")));
        Pager pager = new Pager(news.getTotalPages(), news.getNumber(), PaginationConstant.BUTTONS_TO_SHOW);
        modelAndView.addObject("items", news);
        modelAndView.addObject("selectedPageSize", pageSize);
        modelAndView.addObject("pageSizes", PaginationConstant.PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("pattern", basePattern);
        return modelAndView;
    }

}
