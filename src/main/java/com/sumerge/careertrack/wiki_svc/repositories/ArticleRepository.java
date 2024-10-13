package com.sumerge.careertrack.wiki_svc.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sumerge.careertrack.wiki_svc.entities.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, UUID> {

    public boolean existsById(UUID id);

}
