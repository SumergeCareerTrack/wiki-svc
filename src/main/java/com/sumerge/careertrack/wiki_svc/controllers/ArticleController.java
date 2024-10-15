package com.sumerge.careertrack.wiki_svc.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.sumerge.careertrack.wiki_svc.entities.requests.ArticleRequestDTO;
import com.sumerge.careertrack.wiki_svc.entities.responses.ArticleResponseDTO;
import com.sumerge.careertrack.wiki_svc.services.ArticleService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
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

    @PostMapping("/{managerId}")
    public ArticleResponseDTO createArticle(@RequestBody ArticleRequestDTO article, @PathVariable String managerId){
        return service.create(article,managerId);
    }

    @PostMapping("/{articleId}/accept/{managerId}")
    public void approveArticle(@PathVariable UUID articleId, @PathVariable String managerId) {
        service.approveArticle(articleId,managerId);
    }

    @PostMapping("/{articleId}/reject/{managerId}")
    public void rejectArticle(@PathVariable UUID articleId, @PathVariable String managerId) {
        service.rejectArticle(articleId,managerId);
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
