package team.onepoom.idk.domain.answer.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.onepoom.idk.domain.answer.Answer;
import team.onepoom.idk.domain.user.dto.WriterDTO;

@Getter
@NoArgsConstructor
public class AnswerDTO {

    private long id;
    private WriterDTO writer;
    private String content;
    private boolean isSelected;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private ZonedDateTime reportedAt;

    @Builder
    public AnswerDTO(Answer answer) {
        this.id = answer.getId();
        this.writer = new WriterDTO(answer.getWriter());
        this.content = answer.getContent();
        this.isSelected = answer.isSelected();
        this.createdAt = answer.getCreatedAt();
        this.updatedAt = answer.getUpdatedAt();
        this.reportedAt = answer.getReportedAt();
    }

    @QueryProjection
    public AnswerDTO(long id, WriterDTO writer, String content, boolean isSelected,
        ZonedDateTime createdAt, ZonedDateTime updatedAt, ZonedDateTime reportedAt) {
        this.id = id;
        this.writer = writer;
        this.content = content;
        this.isSelected = isSelected;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.reportedAt = reportedAt;
    }
}
