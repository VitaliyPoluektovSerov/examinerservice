package pro.skyjava.course2.examinerservice.service;

import pro.skyjava.course2.examinerservice.domain.Question;

import java.util.Collection;

public interface QuestionServices {
    Question add(String question, String answer);
    Question add(Question question);
    Question remove(Question question);
    Collection<Question> getAll();
    Question getRandomQuestion();
}
