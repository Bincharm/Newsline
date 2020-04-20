package com.company.Newsline.server;

import com.company.Newsline.entity.News;
import com.company.Newsline.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Book;
import java.io.IOException;
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

    public void saveNews(News news, MultipartFile image){

        try {
            Byte[] byteObjects = new Byte[image.getBytes().length];

            int i = 0;

            for (byte b : image.getBytes()) {
                byteObjects[i++] = b;
            }

            news.setImage(byteObjects);
            newsRepository.save(news);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public News findById(Long newsId){
        if(!newsRepository.findById(newsId).isPresent())
            return new News();
        return newsRepository.findById(newsId).get();
    }

}
