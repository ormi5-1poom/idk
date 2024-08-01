package team.onepoom.idk.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.onepoom.idk.common.exception.QuestionNotFoundException;
import team.onepoom.idk.domain.Provider;
import team.onepoom.idk.domain.question.dto.CreateQuestionRequest;
import team.onepoom.idk.domain.question.dto.GetMyQuestionResponse;
import team.onepoom.idk.domain.question.dto.GetQuestionDetailResponse;
import team.onepoom.idk.domain.question.Question;
import team.onepoom.idk.domain.question.dto.GetQuestionResponse;
import team.onepoom.idk.domain.question.dto.ModifyQuestionRequest;
import team.onepoom.idk.domain.user.User;
import team.onepoom.idk.repository.CustomRepository;
import team.onepoom.idk.repository.QuestionRepository;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final CustomRepository customRepository;

    //질문 작성
    @Transactional
    public void createQuestion(Provider provider, CreateQuestionRequest request) {
        Question question = Question.builder()
            .writer(new User(provider.id()))
            .title(request.getTitle())
            .content(request.getContent())
            .build();
        questionRepository.save(question);
    }

    //단일 질문 조회
    public GetQuestionDetailResponse getOneQuestion(Long id) {
        return new GetQuestionDetailResponse(customRepository.findQuestion(id));
    }

    //질문 목록 조회
    public Page<GetQuestionResponse> findQuestions(String title, Pageable pageable) {
        return customRepository.findQuestions(title, pageable);
    }

    //내 질문 조회
    public Page<GetMyQuestionResponse> findMyQuestions(Provider provider, Pageable pageable) {
        return customRepository.findMyQuestions(provider, pageable);
    }


    //질문 수정
    @Transactional
    public void modifyQuestion(Provider provider, long id, ModifyQuestionRequest request) {
        Question question = findQuestion(id);
        question.checkQuestionOwner(provider);
        question.modifyQuestion(request);
    }

    //질문 삭제
    @Transactional
    public void deleteQuestion(Provider provider, Long id) {
        Question question = findQuestion(id);
        question.checkQuestionOwner(provider);
        question.validateDeleteCondition(question);
        questionRepository.delete(question);
    }

    // id로 질문 조회
    public Question findQuestion(Long id) {
        return questionRepository.findById(id)
            .orElseThrow(() -> new QuestionNotFoundException(id));
    }

}
