package com.sumerge.careertrack.wiki_svc.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sumerge.careertrack.wiki_svc.entities.requests.ArticleRequestDTO;
import com.sumerge.careertrack.wiki_svc.entities.responses.ArticleResponseDTO;
import com.sumerge.careertrack.wiki_svc.services.ArticleService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService service;

    @GetMapping
    public List<ArticleResponseDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{articleId}")
    public ArticleResponseDTO getArticle(@PathVariable UUID articleId) {
        return service.findById(articleId);
    }

    @PostMapping
    public ArticleResponseDTO createArticle(@RequestBody ArticleRequestDTO article) {
        return service.create(article);
    }

    @PostMapping("/{articleId}/accept")
    public void approveArticle(@PathVariable UUID articleId) {
        service.approveArticle(articleId);
    }

    @PostMapping("/{articleId}/reject")
    public void rejectArticle(@PathVariable UUID articleId) {
        service.rejectArticle(articleId);
    }

    @PutMapping("/{articleId}")
    public ArticleResponseDTO updateArticle(@PathVariable UUID articleId,
            @RequestBody ArticleRequestDTO updatedArticle) {
        return service.updateArticle(articleId, updatedArticle);
    }

    @DeleteMapping("/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteArticle(@PathVariable UUID articleId) {
        service.deleteArticle(articleId);
    }

}
