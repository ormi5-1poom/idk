package team.onepoom.idk.domain.question.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindQuestionQuery {

    private String title;

    @Builder
    public FindQuestionQuery(String title) {
        this.title = title;
    }
}
