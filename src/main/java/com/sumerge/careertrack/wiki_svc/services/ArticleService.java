package com.sumerge.careertrack.wiki_svc.services;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import com.sumerge.careertrack.wiki_svc.entities.Article;
import com.sumerge.careertrack.wiki_svc.entities.enums.ApprovalStatus;
import com.sumerge.careertrack.wiki_svc.entities.requests.ArticleRequestDTO;
import com.sumerge.careertrack.wiki_svc.entities.responses.ArticleResponseDTO;
import com.sumerge.careertrack.wiki_svc.exceptions.DoesNotExistException;
import com.sumerge.careertrack.wiki_svc.mappers.ArticleMapper;
import com.sumerge.careertrack.wiki_svc.repositories.ArticleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository repository;
    private final ArticleMapper mapper;

    public List<ArticleResponseDTO> findAll() {
        List<Article> articles = repository.findAll();
        return articles.stream().map(mapper::toDto).toList();
    }

    public ArticleResponseDTO findById(UUID articleId) {
        Article article = repository.findById(articleId)
                .orElseThrow(() -> new DoesNotExistException(
                        DoesNotExistException.ARTICLE_ID, articleId));

        return mapper.toDto(article);
    }

    public List<ArticleResponseDTO> findByAuthorId(UUID authorId) {
        List<Article> articles = repository.findByAuthor(authorId);

        return articles.stream().map(mapper::toDto).toList();
    }

    public List<ArticleResponseDTO> findByBatchAuthorId(List<UUID> authorIds) {
        List<Article> articles = authorIds.stream().map(repository::findByAuthor)
                .flatMap(articleList -> articleList.stream()).toList();

        return articles.stream().map(mapper::toDto).toList();
    }

    public ArticleResponseDTO create(ArticleRequestDTO articleDTO) {
        Article articleObj = mapper.toArticle(articleDTO);
        Article savedArticle = repository.save(articleObj);
        return mapper.toDto(savedArticle);
    }

    public ArticleResponseDTO updateArticle(UUID articleId, ArticleRequestDTO dto) {
        Article article = repository.findById(articleId)
                .orElseThrow(() -> new DoesNotExistException(
                        DoesNotExistException.ARTICLE_ID, articleId));

        updateIfPresent(dto.getAuthor(), article::setAuthor);
        updateIfPresent(dto.getApprovalStatus(), article::setApprovalStatus);
        updateIfPresent(dto.getBody(), article::setBody);
        updateIfPresent(dto.getSubmissionDate(), article::setSubmissionDate);
        updateIfPresent(dto.getComment(), article::setComment);
        updateIfPresent(dto.getTitle(), article::setTitle);
        updateIfPresent(dto.getType(), article::setType);

        repository.save(article);
        return mapper.toDto(article);
    }

    public void approveArticle(UUID articleId, String comment) {
        Article article = repository.findById(articleId)
                .orElseThrow(() -> new DoesNotExistException(
                        DoesNotExistException.ARTICLE_ID, articleId));

        article.setApprovalStatus(ApprovalStatus.APPROVED);
        article.setComment(comment);
        repository.save(article);
    }

    public void rejectArticle(UUID articleId, String comment) {
        Article article = repository.findById(articleId)
                .orElseThrow(() -> new DoesNotExistException(
                        DoesNotExistException.ARTICLE_ID, articleId));

        article.setApprovalStatus(ApprovalStatus.REJECTED);
        article.setComment(comment);
        repository.save(article);
    }

    private <T> void updateIfPresent(T value, Consumer<T> updater) {
        if (value != null) {
            updater.accept(value);
        }
    }

    public void deleteArticle(UUID articleId) {
        if (!repository.existsById(articleId)) {
            throw new DoesNotExistException(DoesNotExistException.ARTICLE_ID, articleId);
        }

        repository.deleteById(articleId);
    }

}
