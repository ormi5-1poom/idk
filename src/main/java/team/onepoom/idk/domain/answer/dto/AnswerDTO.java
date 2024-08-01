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
    @QueryProjection
    public AnswerDTO(Answer answer) {
        this.id = answer.getId();
        this.writer = new WriterDTO(answer.getWriter());
        this.content = answer.getContent();
        this.isSelected = answer.isSelected();
        this.createdAt = answer.getCreatedAt();
        this.updatedAt = answer.getUpdatedAt();
        this.reportedAt = answer.getReportedAt();
    }
}
