package pro.skyjava.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.skyjava.course2.examinerservice.domain.Question;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Set;

@Service
public class JavaQuestionService implements QuestionServices {

    private final Set<Question> questions = new java.util.HashSet<>();
    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        Question q = new Question(question, answer);
        questions.add(q);
        return q;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (questions.remove(question)) {
            return question;
        }
        throw new IllegalArgumentException("Вопрос не найден: " + question);
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new IllegalStateException("Список вопросов пуст");
        }
        int index = random.nextInt(questions.size());
        return (Question) questions.toArray()[index];
    }
}