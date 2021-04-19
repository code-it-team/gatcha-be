package codeit.gatcha;

import codeit.gatcha.domain.answer.entity.Answer;
import codeit.gatcha.domain.question.entity.Question;
import codeit.gatcha.domain.question.repo.QuestionRepo;
import codeit.gatcha.domain.question.service.QuestionCreationService;
import lombok.AllArgsConstructor;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@TestPropertySource(
		locations = "classpath:application-dev.properties")
class GatchaApplicationTests {
	private final QuestionRepo questionRepo;
	private final QuestionCreationService questionCreationService;

	@Autowired
	public GatchaApplicationTests(QuestionRepo questionRepo, QuestionCreationService questionCreationService) {
		this.questionRepo = questionRepo;
		this.questionCreationService = questionCreationService;
	}

	@Test
	void givenQuestionWith3Answers_SuccessfullySavedToDB() {
		Answer answer1 = Answer.builder().body("answer1").build();
		Answer answer2 = Answer.builder().body("answer2").build();
		Answer answer3 = Answer.builder().body("answer3").build();

		Question question = questionCreationService.
				createQuestionWithAnswers("question1",
						new HashSet<Answer>(Arrays.asList(answer1, answer2, answer3)));

		questionRepo.save(question);

		List<Question> questionsFromDB = Lists.newArrayList(questionRepo.findAll().iterator());
		assertEquals(1, questionsFromDB.size());
		assertEquals("question1", questionsFromDB.get(0).getBody());
		assertEquals(3, questionsFromDB.get(0).getAnswers().size());
	}

}
