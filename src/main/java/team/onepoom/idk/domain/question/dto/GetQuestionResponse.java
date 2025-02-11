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
    private int views;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private ZonedDateTime reportedAt;

    @QueryProjection
    public GetQuestionResponse(long id, long writerId, String email, String nickname, String title, String content,
        boolean selected, int views, ZonedDateTime createdAt,
        ZonedDateTime updatedAt, ZonedDateTime reportedAt, long answerCount) {
        this.id = id;
        this.writer = new WriterDTO(writerId, email, nickname);
        this.title = reportedAt == null ? title : "신고된 게시물입니다." ;
        this.content = reportedAt == null ? content : "신고된 게시물입니다.";
        this.selected = selected;
        this.answerCount = answerCount;
        this.views = views;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.reportedAt = reportedAt;
    }
}
