package team.onepoom.idk.domain.question.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.onepoom.idk.domain.answer.dto.AnswerDTO;
import team.onepoom.idk.domain.question.Question;
import team.onepoom.idk.domain.user.dto.WriterDTO;

@Getter
@NoArgsConstructor
public class GetQuestionDetailResponse {

    private long id;
    private WriterDTO writer;
    private String title;
    private String content;
    private List<AnswerDTO> answers;
    @JsonProperty("isSelected")
    private boolean selected;
    private long answerCount;
    private long views;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private ZonedDateTime reportedAt;

    @QueryProjection
    public GetQuestionDetailResponse(Question question) {
        this.id = question.getId();
        this.writer = new WriterDTO(question.getWriter());
        this.title = question.getTitle();
        this.content = question.getContent();
        this.answers = question.getAnswers().stream()
            .map(AnswerDTO::new)
            .collect(Collectors.toList());
        this.selected = question.isSelected();
        this.answerCount = question.getAnswers().size();
        this.views = question.getViews();
        this.createdAt = question.getCreatedAt();
        this.updatedAt = question.getUpdatedAt();
        this.reportedAt = question.getReportedAt();
    }
}
