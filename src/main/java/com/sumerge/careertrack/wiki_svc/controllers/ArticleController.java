package com.sumerge.careertrack.wiki_svc.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.sumerge.careertrack.wiki_svc.entities.requests.ArticleRequestDTO;
import com.sumerge.careertrack.wiki_svc.entities.responses.ArticleResponseDTO;
import com.sumerge.careertrack.wiki_svc.services.ArticleService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@CrossOrigin
@RequestMapping("/articles")
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
    @GetMapping("/author/{authorId}")
    public List<ArticleResponseDTO> getByAuthorId(@PathVariable UUID authorId) {
        return service.findByAuthorId(authorId);
    }
      @PostMapping("/author/batch")
    public List<ArticleResponseDTO> getByBatchAuthorId(@RequestBody List<UUID> authorIds) {
        List<ArticleResponseDTO> articles = service.findByBatchAuthorId(authorIds);

        return articles;
    }


    @PostMapping("/{managerId}")
    public ArticleResponseDTO createArticle(@RequestBody ArticleRequestDTO article, @PathVariable String managerId){
        return service.create(article,managerId);
    }

    @PostMapping("/{articleId}/accept/{managerId}")
    public void approveArticle(@PathVariable UUID articleId, @PathVariable String managerId,@RequestBody String comment) {
        service.approveArticle(articleId,managerId,comment);
    }

    @PostMapping("/{articleId}/reject/{managerId}")
    public void rejectArticle(@PathVariable UUID articleId, @PathVariable String managerId,@RequestBody String comment) {
        service.rejectArticle(articleId,managerId,comment);

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
