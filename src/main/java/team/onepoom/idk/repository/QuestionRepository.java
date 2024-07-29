package team.onepoom.idk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.onepoom.idk.domain.question.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionRepositoryCustom {

}
