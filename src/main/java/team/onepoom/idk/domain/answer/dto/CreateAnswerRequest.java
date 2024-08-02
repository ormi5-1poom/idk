package team.onepoom.idk.domain.answer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import team.onepoom.idk.domain.Provider;
import team.onepoom.idk.domain.answer.Answer;
import team.onepoom.idk.domain.user.User;

public record CreateAnswerRequest(@Positive long questionId, @NotBlank String content) {

    public Answer toEntity(Provider provider) {
        return Answer.builder()
            .questionId(questionId)
            .content(content)
            .writer(new User(provider.id()))
            .build();
    }
}
