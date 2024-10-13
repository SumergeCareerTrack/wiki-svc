package com.sumerge.careertrack.wiki_svc.entities.requests;

import java.util.Date;
import java.util.UUID;

import com.sumerge.careertrack.wiki_svc.entities.enums.ApprovalStatus;
import com.sumerge.careertrack.wiki_svc.entities.enums.ArticleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequestDTO {

    private String title;

    private UUID author;

    private ArticleType type;

    private Date submissionDate;

    private ApprovalStatus approvalStatus;

    private String comment;

    private String body;

}
