package com.sumerge.careertrack.wiki_svc.entities;

import java.util.Date;
import java.util.UUID;

import com.sumerge.careertrack.wiki_svc.entities.enums.ApprovalStatus;
import com.sumerge.careertrack.wiki_svc.entities.enums.ArticleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private UUID author;

    @Column(nullable = false)
    private ArticleType type;

    @Column(nullable = false)
    private Date submissionDate;

    @Column(nullable = false)
    private ApprovalStatus approvalStatus;

    @Column(nullable = false)
    private String body;

    private String comment;
}