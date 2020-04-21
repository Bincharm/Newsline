package com.company.Newsline.service;

import com.company.Newsline.entity.News;
import com.company.Newsline.model.NewsModel;
import com.company.Newsline.repository.NewsRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

    public News findById(Long newsId){
        News news = newsRepository.findById(newsId).orElse(new News());
        if (news.getImage() != null) {
            byte[] encodeBase64 = Base64.encodeBase64(news.getImage());
            String base64Encoded = new String(encodeBase64, StandardCharsets.UTF_8);
            news.setBase64imageFile(base64Encoded);
        }
        return news;
    }

}
