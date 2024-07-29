package team.onepoom.idk.domain.question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.onepoom.idk.common.exception.QuestionForbiddenException;
import team.onepoom.idk.domain.BaseEntity;
import team.onepoom.idk.domain.Provider;
import team.onepoom.idk.domain.question.dto.ModifyQuestionRequest;
import team.onepoom.idk.domain.user.Role;
import team.onepoom.idk.domain.user.User;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "questions")
public class Question extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", nullable = false)
    private User writer;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    private boolean isSelected;

    @Column(nullable = false)
    private int views;

    private ZonedDateTime reportedAt;

    public Question(Long id) {
        this.id = id;
    }

    //생성 메서드
    @Builder
    public Question(User writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.isSelected = false;
        this.views = 0;
    }

    //수정 메서드
    public void modifyQuestion(ModifyQuestionRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }

    //답변 채택 시
    public void answerSelected() {
        this.isSelected = true;
    }

    //질문 소유자 권한 체크
    public void checkQuestionOwner(Provider provider, Question question) {
        if (provider.id() != question.getWriter().getId() || provider.roles().contains(Role.ADMIN)) {
            throw new QuestionForbiddenException(question.getId());
        }
    }

    //Todo 질문 삭제 조건 검증
    public void validateDeleteCondition(Question question) {
        //질문의 답변이 존재할 시 Exception
    }
}
