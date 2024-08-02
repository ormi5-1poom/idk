package team.onepoom.idk.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.onepoom.idk.domain.Provider;
import team.onepoom.idk.domain.answer.dto.AnswerDTO;
import team.onepoom.idk.domain.answer.dto.CreateAnswerRequest;
import team.onepoom.idk.domain.answer.dto.ModifyAnswerRequest;
import team.onepoom.idk.domain.answer.dto.MyAnswerResponse;
import team.onepoom.idk.service.AnswerService;

@RestController
@RequestMapping("api/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping
    @RolesAllowed({"USER"})
    @ResponseStatus(HttpStatus.CREATED)
    public AnswerDTO create(@AuthenticationPrincipal Provider provider,
        @RequestBody CreateAnswerRequest request) {
        return answerService.create(provider, request);
    }

    @RolesAllowed({"USER", "ADMIN"})
    @PutMapping("{id}")
    public void modify(@AuthenticationPrincipal Provider provider, @Positive @PathVariable long id,
        @RequestBody ModifyAnswerRequest request) {
        answerService.modify(provider, id, request);
    }

    @RolesAllowed({"USER"})
    @DeleteMapping("{id}")
    public void delete(@AuthenticationPrincipal Provider provider, @PathVariable long id) {
        answerService.delete(provider, id);
    }

    @RolesAllowed({"USER"})
    @PostMapping("{id}/selections")
    public void select(@AuthenticationPrincipal Provider provider, @PathVariable long id) {
        answerService.select(provider, id);
    }

    @RolesAllowed({"USER", "ADMIN"})
    @GetMapping("/me")
    public Page<MyAnswerResponse> getMyAnswers(@AuthenticationPrincipal Provider provider,
        Pageable pageable) {
        return answerService.getMyAnswers(provider, pageable);
    }

}
