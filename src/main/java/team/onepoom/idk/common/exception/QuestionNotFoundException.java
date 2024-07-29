package team.onepoom.idk.common.exception;

public class QuestionNotFoundException extends NotFoundException {

    public QuestionNotFoundException(long id) {
        super("존재하지 않는 질문입니다. id: " + id);
    }
}
