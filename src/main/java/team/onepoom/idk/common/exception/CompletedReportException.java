package team.onepoom.idk.common.exception;

public class CompletedReportException extends ConflictException {

    public CompletedReportException() {
        super("이미 처리 또는 삭제된 신고입니다.");
    }
}
