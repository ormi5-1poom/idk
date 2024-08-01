package team.onepoom.idk.service;

import java.time.ZonedDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.onepoom.idk.common.exception.CompletedReportException;
import team.onepoom.idk.common.exception.QuestionReportNotFoundException;
import team.onepoom.idk.domain.Provider;
import team.onepoom.idk.domain.questionReport.QuestionReport;
import team.onepoom.idk.domain.questionReport.dto.CreateQuestionReportRequest;
import team.onepoom.idk.domain.questionReport.dto.GetQuestionReportResponse;
import team.onepoom.idk.repository.QuestionReportRepository;

@Service
@RequiredArgsConstructor
public class QuestionReportService {

    private final QuestionReportRepository questionReportRepository;
    private final QuestionService questionService;
    private final UserService userService;

    @Transactional
    public void create(Provider provider, CreateQuestionReportRequest request) {
        questionService.findQuestion(request.questionId());
        questionReportRepository.save(request.toEntity(provider));
    }

    @Transactional(readOnly = true)
    public Page<GetQuestionReportResponse> getQuestionsReports(Provider provider,
        Pageable pageable) {
        userService.checkAdminAuthority(provider);
        return questionReportRepository.findAllByCompletedAtIsNull(pageable)
            .map(GetQuestionReportResponse::from);
    }

    @Transactional
    public void handleQuestionReport(long id) {
        QuestionReport questionReport = getValidatedQuestionReport(id);
        //신고 처리 날짜 (게시물 가리기용)
        questionReport.getQuestion().reported();
        //유저 정지
        userService.suspend(questionReport.getQuestion().getWriter().getId());
        //신고 처리 완료
        questionReport.completed();
    }

    private QuestionReport findQuestionReport(long id) {
        return questionReportRepository.findById(id)
            .orElseThrow(() -> new QuestionReportNotFoundException(id));
    }

    @Transactional
    public void deleteQuestionReport(long id) {
        QuestionReport questionReport = getValidatedQuestionReport(id);
        //신고 처리 완료
        questionReport.completed();
    }

    //처리 또는 삭제된 신고일 경우 예외처리
    private QuestionReport getValidatedQuestionReport(long id) {
        QuestionReport questionReport = findQuestionReport(id);
        if (questionReport.getCompletedAt() != null) {
            throw new CompletedReportException();
        }
        return questionReport;
    }
}
