package team.onepoom.idk.domain.question.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.onepoom.idk.domain.question.Question;
import team.onepoom.idk.domain.user.dto.WriterDTO;

@Getter
@NoArgsConstructor
public class GetMyQuestionResponse {

    private long id;
    private String title;
    private String content;
    @JsonProperty("isSelected")
    private boolean selected;
    private long answerCount;
    private long views;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private ZonedDateTime reportedAt;

    @QueryProjection
    public GetMyQuestionResponse(Question question, Long answerCount) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.selected = question.isSelected();
        this.answerCount = answerCount;
        this.views = question.getViews();
        this.createdAt = question.getCreatedAt();
        this.updatedAt = question.getUpdatedAt();
        this.reportedAt = question.getReportedAt();
    }
}
