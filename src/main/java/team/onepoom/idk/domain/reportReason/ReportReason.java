package team.onepoom.idk.domain.reportReason;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "report_reasons")
public class ReportReason {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String content;

    public ReportReason(Long id) {
        this.id = id;
    }
}

