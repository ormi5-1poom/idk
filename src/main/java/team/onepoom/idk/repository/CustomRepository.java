package team.onepoom.idk.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import team.onepoom.idk.domain.Provider;
import team.onepoom.idk.domain.answer.QAnswer;
import team.onepoom.idk.domain.answer.dto.AnswerDTO;
import team.onepoom.idk.domain.question.QQuestion;
import team.onepoom.idk.domain.question.Question;
import team.onepoom.idk.domain.question.dto.GetQuestionDetailResponse;
import team.onepoom.idk.domain.question.dto.GetQuestionResponse;
import team.onepoom.idk.domain.question.dto.QGetQuestionResponse;
import team.onepoom.idk.domain.user.QUser;
import team.onepoom.idk.domain.user.dto.WriterDTO;

@RequiredArgsConstructor
@Repository
public class CustomRepository {

    private final JPAQueryFactory queryFactory;
    QQuestion question = QQuestion.question;
    QAnswer answer = QAnswer.answer;
    QUser user = QUser.user;

    public Page<GetQuestionResponse> findQuestions(String title, Pageable pageable) {
        List<GetQuestionResponse> questions = queryFactory
            .select(Projections.constructor(GetQuestionResponse.class,
                question.id,
                Projections.constructor(WriterDTO.class,
                    user.id,
                    user.email,
                    user.nickname),
                question.title,
                question.content,
                question.isSelected,
                JPAExpressions.select(answer.count())
                    .from(answer)
                    .where(answer.question.id.eq(question.id)),
                question.createdAt,
                question.updatedAt,
                question.reportedAt
            ))
            .from(question)
            .leftJoin(question.writer, user)
            .where(titleContains(title))
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .fetch();

        JPAQuery<Long> count = queryFactory
            .select(question.count())
            .from(question)
            .where(titleContains(title));

        return PageableExecutionUtils.getPage(questions, pageable, count::fetchOne);
    }

    public Page<GetQuestionResponse> findMyQuestions(Provider provider, Pageable pageable) {
        List<GetQuestionResponse> questions = queryFactory
            .select(Projections.constructor(GetQuestionResponse.class,
                question.id,
                Projections.constructor(WriterDTO.class,
                    user.id,
                    user.email,
                    user.nickname),
                question.title,
                question.content,
                question.isSelected,
                JPAExpressions.select(answer.count())
                    .from(answer)
                    .where(answer.question.id.eq(question.id)),
                question.createdAt,
                question.updatedAt,
                question.reportedAt
            ))
            .from(question)
            .leftJoin(question.writer, user)
            .where(question.writer.id.eq(provider.id()))
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .fetch();

        JPAQuery<Long> count = queryFactory
            .select(question.count())
            .from(question)
            .where(question.writer.id.eq(provider.id()));

        return PageableExecutionUtils.getPage(questions, pageable, count::fetchOne);
    }

    public Question findQuestion(long id) {
        return queryFactory
            .selectFrom(question)
            .leftJoin(question.writer, user).fetchJoin()
            .leftJoin(question.answers, answer).fetchJoin()
            .where(question.id.eq(id))
            .fetchOne();


    }

    private BooleanExpression titleContains(String title) {

        return title != null ? question.title.contains(title) : null;
    }
}
