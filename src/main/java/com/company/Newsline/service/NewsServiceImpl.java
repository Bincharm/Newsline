package com.company.Newsline.service;

import com.company.Newsline.entity.News;
import com.company.Newsline.model.NewsModel;
import com.company.Newsline.repository.NewsRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

//    @Override
//    public Page<News> getPaginatedNews(Pageable pageable) {
//        return newsRepository.findAll(pageable);
//    }

    public List<News> findAllNews(){
        return newsRepository.findAll();
    }

//    public void saveNews(News news, MultipartFile image){
//
//        try {
//            Byte[] byteObjects = new Byte[image.getBytes().length];
//
//            int i = 0;
//
//            for (byte b : image.getBytes()) {
//                byteObjects[i++] = b;
//            }
//
//            //news.setImage(byteObjects);
//            newsRepository.save(news);
//        } catch(IOException e){
//            e.printStackTrace();
//        }
//    }

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

    public void saveOrUpdateNewsFromModel(NewsModel newsModel){
        if(newsModel.getId() == null){
            saveNewsFromModel(newsModel);
        }
        else updateNewsFromModel(newsModel);
    }

    public News findById(Long newsId){
        News news = newsRepository.findById(newsId).orElse(new News());
        return bytesToBase64(news);
    }

    public boolean deleteNews(Long newsId) {
        if (newsRepository.findById(newsId).isPresent()) {
            newsRepository.deleteById(newsId);
            return true;
        }
        return false;
    }

    @Override
    public Page<News> getAllPaginatedNews(Pageable pageable){
        Page<News> paginatedNews = newsRepository.findAllByOrderByPostDateDesc(pageable);
        for(News news : paginatedNews){
            bytesToBase64(news);
        }
        return paginatedNews;
    }

    public News bytesToBase64(News news){
        if (news.getImage() != null) {
            byte[] encodeBase64 = Base64.encodeBase64(news.getImage());
            String base64Encoded = new String(encodeBase64, StandardCharsets.UTF_8);
            news.setBase64imageFile(base64Encoded);
        }
        return news;
    }

    public Page<News> getAllPagedNews(Pageable paging)
    {

        Page<News> pagedResult = newsRepository.findAll(paging);
        return pagedResult;
//        if(pagedResult.hasContent()) {
//            return pagedResult.getContent();
//        } else {
//            return new ArrayList<News>();
//        }
    }

}
