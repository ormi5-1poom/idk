package team.onepoom.idk.controller;

import jakarta.annotation.security.RolesAllowed;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.onepoom.idk.domain.reportReason.dto.AllReportReasonResponse;
import team.onepoom.idk.domain.reportReason.dto.CreateReportReasonRequest;
import team.onepoom.idk.service.ReportReasonService;

@RestController
@RequestMapping("/api/report-reasons")
public class ReportReasonController {
    private final ReportReasonService reportReasonService;

    @Autowired
    public ReportReasonController(ReportReasonService reportReasonService) {
        this.reportReasonService = reportReasonService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed({"ADMIN"})
    public void createReportReason(@RequestBody CreateReportReasonRequest createReportReasonRequest) {
        reportReasonService.create(createReportReasonRequest.toEntity());
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ADMIN"})
    public void deleteReportReason(@PathVariable("id") long id) {
        reportReasonService.delete(id);
    }

    @GetMapping
    public List<AllReportReasonResponse> allReportReason() {
        return reportReasonService.findAllReportReasons();
    }
}
