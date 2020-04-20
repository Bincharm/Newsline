package com.company.Newsline.server;

import com.company.Newsline.entity.News;
import com.company.Newsline.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.awt.print.Book;
import java.util.Collections;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{

    @Autowired
    private NewsRepository newsRepository;

//    public List<News> findAll(){
//        return newsRepository.findAll();
//    }

//    public Page<News> findPaginated(Pageable pageable){
//        return null;
//    }

    @Override
    public Page<News> getPaginatedNews(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    public List<News> findAllNews(){
        return newsRepository.findAll();
    }


}
