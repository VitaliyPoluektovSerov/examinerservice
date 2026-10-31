package pro.skyjava.course2.examinerservice.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pro.skyjava.course2.examinerservice.domain.Question;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final Random random = new Random();
    private final QuestionServices questionService;

    public ExaminerServiceImpl(QuestionServices questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Collection<Question> allQuestions = questionService.getAll();

        if (amount > allQuestions.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Запрошено больше вопросов (" + amount + "), чем доступно (" + allQuestions.size() + ")");
        }

        List<Question> questionList = new ArrayList<>(allQuestions);
        Set<Question> result = new LinkedHashSet<>();

        while (result.size() < amount) {
            int index = random.nextInt(questionList.size());
            result.add(questionList.get(index));
        }

        return result;
    }
}