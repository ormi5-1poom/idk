package team.onepoom.idk.common.exception;

public class QuestionHasAnswersException extends ConflictException {

    public QuestionHasAnswersException() {
        super("답변이 존재하는 질문은 수정 또는 삭제할 수 없습니다.");
    }
}
