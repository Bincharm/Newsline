package com.company.Newsline.server;

import com.company.Newsline.entity.News;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface NewsService {
//    public Page<News> findPaginated(Pageable pageable);
    public Page<News> getPaginatedNews(Pageable pageable);
}
