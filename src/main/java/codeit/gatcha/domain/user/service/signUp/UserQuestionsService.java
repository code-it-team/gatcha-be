package codeit.gatcha.domain.user.service.signUp;

import codeit.gatcha.domain.question.entity.Question;
import codeit.gatcha.domain.question.repo.QuestionRepo;
import codeit.gatcha.domain.user.entity.User;
import codeit.gatcha.domain.user.repo.UserRepo;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class UserQuestionsService {
    private final QuestionRepo questionRepo;
    private final UserRepo userRepo;

    public void addAllExistingQuestionsToUser(User user){
        Iterable<Question> allQuestions = questionRepo.findAll();
        user.getQuestions().addAll(Sets.newHashSet(allQuestions));
        userRepo.save(user);
    }
}
