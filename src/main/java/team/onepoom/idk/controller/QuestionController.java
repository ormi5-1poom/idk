package team.onepoom.idk.controller;

import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.onepoom.idk.common.annotation.SuspendDenied;
import team.onepoom.idk.domain.Provider;
import team.onepoom.idk.domain.question.dto.CreateQuestionRequest;
import team.onepoom.idk.domain.question.dto.CreateQuestionResponse;
import team.onepoom.idk.domain.question.dto.GetMyQuestionResponse;
import team.onepoom.idk.domain.question.dto.GetQuestionDetailResponse;
import team.onepoom.idk.domain.question.dto.GetQuestionResponse;
import team.onepoom.idk.domain.question.dto.ModifyQuestionRequest;
import team.onepoom.idk.service.QuestionService;


@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
class QuestionController {

    private final QuestionService questionService;

    @RolesAllowed({"USER"})
    @PostMapping
    public CreateQuestionResponse createQuestion(@AuthenticationPrincipal Provider provider, @RequestBody CreateQuestionRequest request) {
        return questionService.createQuestion(provider, request);
    }

    @RolesAllowed({"USER"})
    @PutMapping("/{id}")
    public void modify(@AuthenticationPrincipal Provider provider, @PathVariable long id, @RequestBody ModifyQuestionRequest request) {
        questionService.modifyQuestion(provider, id, request);
    }

    @RolesAllowed({"USER"})
    @DeleteMapping("/{id}")
    public void delete(@AuthenticationPrincipal Provider provider, @PathVariable long id) {
        questionService.deleteQuestion(provider, id);
    }

    @SuspendDenied
    @GetMapping("/{id}")
    public GetQuestionDetailResponse getQuestion(@PathVariable long id) {
        return questionService.getOneQuestion(id);
    }

    @SuspendDenied
    @GetMapping
    public Page<GetQuestionResponse> findQuestions(@RequestParam(value = "title", required = false) String title, Pageable pageable) {
        return questionService.findQuestions(title, pageable);
    }

    @RolesAllowed({"USER"})
    @GetMapping("/me")
    public Page<GetMyQuestionResponse> findMyQuestions(@AuthenticationPrincipal Provider provider, Pageable pageable) {
        return questionService.findMyQuestions(provider, pageable);
    }
}
