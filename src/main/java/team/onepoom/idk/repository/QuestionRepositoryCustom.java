package team.onepoom.idk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.onepoom.idk.domain.Provider;
import team.onepoom.idk.domain.question.dto.FindQuestionQuery;
import team.onepoom.idk.domain.question.dto.GetQuestionResponse;


public interface QuestionRepositoryCustom {

    Page<GetQuestionResponse> findQuestions(String title, Pageable pageable);

    Page<GetQuestionResponse> findMyQuestions(Provider provider, Pageable pageable);
}
