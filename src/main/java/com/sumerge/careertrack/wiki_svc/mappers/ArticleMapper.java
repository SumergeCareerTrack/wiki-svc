package com.sumerge.careertrack.wiki_svc.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sumerge.careertrack.wiki_svc.entities.Article;
import com.sumerge.careertrack.wiki_svc.entities.requests.ArticleRequestDTO;
import com.sumerge.careertrack.wiki_svc.entities.responses.ArticleResponseDTO;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    @Mapping(target = "id", ignore = true)
    Article toArticle(ArticleRequestDTO dto);

    ArticleResponseDTO toDto(Article article);

}
