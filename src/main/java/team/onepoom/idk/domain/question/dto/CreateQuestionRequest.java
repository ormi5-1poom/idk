package team.onepoom.idk.domain.question.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
public class CreateQuestionRequest {

    @NotBlank
    @Length(min = 2)
    private String title;
    @NotBlank
    private String content;

    @Builder
    public CreateQuestionRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}