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
public class GetQuestionResponse {

    private long id;
    private WriterDTO writer;
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
    public GetQuestionResponse(Question question, Long answerCount) {
        this.id = question.getId();
        this.writer = new WriterDTO(question.getWriter());
        this.title = question.getReportedAt() == null ? question.getTitle() : "신고된 게시물입니다." ;
        this.content = question.getReportedAt() == null ? question.getContent() : "신고된 게시물입니다.";
        this.selected = question.isSelected();
        this.answerCount = answerCount;
        this.views = question.getViews();
        this.createdAt = question.getCreatedAt();
        this.updatedAt = question.getUpdatedAt();
        this.reportedAt = question.getReportedAt();
    }
}
