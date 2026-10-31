package pro.skyjava.course2.examinerservice.service.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;
import pro.skyjava.course2.examinerservice.domain.Question;
import pro.skyjava.course2.examinerservice.service.ExaminerServiceImpl;
import pro.skyjava.course2.examinerservice.service.JavaQuestionService;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class ExaminerServiceImplTest {

    private JavaQuestionService questionService;
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        questionService = new JavaQuestionService();
        examinerService = new ExaminerServiceImpl(questionService);
    }

    @Test
    void getQuestions_shouldReturnRequestedAmount() {
        questionService.add("Q1", "A1");
        questionService.add("Q2", "A2");
        questionService.add("Q3", "A3");

        Collection<Question> result = examinerService.getQuestions(2);
        assertEquals(2, result.size());
    }

    @Test
    void getQuestions_shouldReturnUniqueQuestions() {
        questionService.add("Q1", "A1");
        questionService.add("Q2", "A2");
        questionService.add("Q3", "A3");
        questionService.add("Q4", "A4");
        questionService.add("Q5", "A5");

        Collection<Question> result = examinerService.getQuestions(5);
        assertEquals(5, result.size());
    }

    @Test
    void getQuestions_shouldThrowWhenAmountExceedsAvailable() {
        questionService.add("Q1", "A1");

        assertThrows(ResponseStatusException.class, () ->
                examinerService.getQuestions(5));
    }

    @Test
    void getQuestions_shouldReturnAllWhenAmountEqualsSize() {
        questionService.add("Q1", "A1");
        questionService.add("Q2", "A2");

        Collection<Question> result = examinerService.getQuestions(2);
        assertEquals(2, result.size());
    }
}