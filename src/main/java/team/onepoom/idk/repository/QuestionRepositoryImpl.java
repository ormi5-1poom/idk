package team.onepoom.idk.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import team.onepoom.idk.domain.Provider;
import team.onepoom.idk.domain.question.dto.FindQuestionQuery;
import team.onepoom.idk.domain.question.dto.GetQuestionResponse;
import team.onepoom.idk.domain.question.QQuestion;
import team.onepoom.idk.domain.question.dto.QGetQuestionResponse;


@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<GetQuestionResponse> findQuestions(String title, Pageable pageable) {

        List<GetQuestionResponse> questions = queryFactory
            .select(new QGetQuestionResponse(QQuestion.question))
            .from(QQuestion.question)
            .where(titleContains(title))
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .fetch();

        return new PageImpl<>(questions, pageable, questions.size());
    }

    @Override
    public Page<GetQuestionResponse> findMyQuestions(Provider provider, Pageable pageable) {
        List<GetQuestionResponse> questions = queryFactory
            .select(new QGetQuestionResponse(QQuestion.question))
            .from(QQuestion.question)
            .where(QQuestion.question.writer.id.eq(provider.id()))
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .fetch();

        return new PageImpl<>(questions, pageable, questions.size());
    }

    private BooleanExpression titleContains(String title) {
        return title != null ? QQuestion.question.title.contains(title) : null;
    }
}
