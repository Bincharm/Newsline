package com.company.Newsline.repository;

import com.company.Newsline.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findAllByOrderByPostDateDesc();
    Page<News> findAllByOrderByPostDateDesc(Pageable pageable); //gets all news with order by post date desc
}
