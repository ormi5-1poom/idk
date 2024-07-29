package team.onepoom.idk.domain.question.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.onepoom.idk.domain.Provider;
import team.onepoom.idk.domain.question.Question;

@Getter
@NoArgsConstructor
public class GetQuestionDetailResponse {

    private long id;
    private Provider writer;
    private String title;
    private String content;
    @JsonProperty("isSelected")
    private boolean select;
    private int answerCount;
//    private List<AnswerDTO> answers; //todo 답변 엔티티 개발 시 작성
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private ZonedDateTime reportedAt;

    @QueryProjection
    public GetQuestionDetailResponse(Question question) {
        this.id = question.getId();
        this.writer = question.getWriter().toProvider();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.select = question.isSelected();
        this.answerCount = 0; //todo 답변 엔티티 개발 시 작성
        this.createdAt = question.getCreatedAt();
        this.updatedAt = question.getUpdatedAt();
        this.reportedAt = question.getReportedAt();
    }
}
