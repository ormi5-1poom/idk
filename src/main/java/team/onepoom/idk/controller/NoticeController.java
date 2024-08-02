package team.onepoom.idk.controller;

import jakarta.annotation.security.RolesAllowed;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import team.onepoom.idk.domain.notice.dto.AllNoticeResponse;
import team.onepoom.idk.domain.notice.dto.CreateNoticeRequest;
import team.onepoom.idk.domain.notice.dto.CreateNoticeResponse;
import team.onepoom.idk.domain.notice.dto.DetailNoticeResponse;
import team.onepoom.idk.domain.notice.dto.FiveNoticeResponse;
import team.onepoom.idk.domain.notice.dto.UpdateNoticeRequest;
import team.onepoom.idk.service.NoticeService;

@RestController
@RequestMapping("/api/notices")
public class NoticeController {

    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed({"ADMIN"})
    public CreateNoticeResponse createNotice(@AuthenticationPrincipal Provider provider,
        @RequestBody CreateNoticeRequest createNoticeRequest) {
        return noticeService.create(createNoticeRequest.toEntity(provider));
    }

    @PutMapping("/{id}")
    @RolesAllowed({"ADMIN"})
    public void updateNotice(@PathVariable long id,
        @RequestBody UpdateNoticeRequest updateNoticeRequest) {
        noticeService.update(id, updateNoticeRequest.toEntity());
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ADMIN"})
    public void deleteNotice(@PathVariable long id) {
        noticeService.delete(id);
    }

    @GetMapping("/{id}")
    public DetailNoticeResponse detailNotice(@PathVariable long id) {
        return noticeService.showDetailNotice(id);
    }

    @GetMapping
    public Page<AllNoticeResponse> allNotice(Pageable pageable) {
        return noticeService.showAllNotice(pageable);
    }

    @GetMapping("/five")
    public List<FiveNoticeResponse> fiveNotice() {
        return noticeService.getFiveNotices(
            PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "createdAt")));
    }
}
