package team.onepoom.idk.domain.question;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import team.onepoom.idk.common.exception.QuestionForbiddenException;
import team.onepoom.idk.domain.BaseEntity;
import team.onepoom.idk.domain.Provider;
import team.onepoom.idk.domain.answer.Answer;
import team.onepoom.idk.domain.question.dto.ModifyQuestionRequest;
import team.onepoom.idk.domain.user.User;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "questions")
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", nullable = false)
    private User writer;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

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

    //관리자 삭제 메서드
    public void report() {
        this.reportedAt = ZonedDateTime.now();
    }

    //답변 채택 시
    public void answerSelected() {
        this.isSelected = true;
    }

    //질문 소유자 권한 체크
    public void checkQuestionOwner(Provider provider) {
        if (provider.id() != this.getWriter().getId()) {
            throw new QuestionForbiddenException(this.getId());
        }
    }

    //조회 수 증가
    public void increaseViews() {
        this.views += 1;
    }
}
