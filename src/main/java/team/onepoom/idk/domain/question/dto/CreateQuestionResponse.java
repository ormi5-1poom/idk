package team.onepoom.idk.domain.question.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateQuestionResponse {

    private long id;

    @Builder
    public CreateQuestionResponse(long id) {
        this.id = id;
    }
}
