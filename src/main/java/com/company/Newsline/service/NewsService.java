package com.company.Newsline.service;

import com.company.Newsline.entity.News;
import com.company.Newsline.model.NewsModel;
import com.company.Newsline.repository.NewsRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public List<News> findAllNews(){
        return newsRepository.findAll();
    }

    //inserts a news to DB
    public void saveNewsFromModel(NewsModel newsModel) {
        try {
            News news = News.builder()
                    .headline(newsModel.getHeadline())
                    .newsBody(newsModel.getNewsBody())
                    .image(newsModel.getImage() != null ? newsModel.getImage().getBytes() : null)
                    .build();

            newsRepository.save(news);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //updates an existing news in DB
    public void updateNewsFromModel(NewsModel newsModel){
        News news = findById(newsModel.getId());

        news.setHeadline(newsModel.getHeadline());
        news.setNewsBody(newsModel.getNewsBody());

        if(newsModel.getImage() != null && newsModel.getImage().getOriginalFilename() != null
                && !newsModel.getImage().getOriginalFilename().equals("")){
            try {
                news.setImage(newsModel.getImage().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        newsRepository.save(news);
    }

    //inserts a news to DB if there is no news id
    //or updates an existing news in DB if there is news id
    public void saveOrUpdateNewsFromModel(NewsModel newsModel){
        if(newsModel.getId() == null){
            saveNewsFromModel(newsModel);
        }
        else updateNewsFromModel(newsModel);
    }

    //gets a news with newsId with converted image
    //if the news exists, if not then returns empty News object
    public News findById(Long newsId){
        News news = newsRepository.findById(newsId).orElse(new News());
        return bytesToBase64(news);
    }

    //checks if there is a news with newsId and deletes it
    //if there is no such a news then returns false
    public boolean deleteNews(Long newsId) {
        if (newsRepository.findById(newsId).isPresent()) {
            newsRepository.deleteById(newsId);
            return true;
        }
        return false;
    }

    //gets all paginated news
    public Page<News> getAllPaginatedNews(Pageable pageable){
        Page<News> paginatedNews = newsRepository.findAllByOrderByPostDateDesc(pageable);
        for(News news : paginatedNews){
            bytesToBase64(news);
        }
        return paginatedNews;
    }

    //converts bytes array to string
    public News bytesToBase64(News news){
        if (news.getImage() != null) {
            byte[] encodeBase64 = Base64.encodeBase64(news.getImage());
            String base64Encoded = new String(encodeBase64, StandardCharsets.UTF_8);
            news.setBase64imageFile(base64Encoded);
        }
        return news;
    }


}
