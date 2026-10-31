package pro.skyjava.course2.examinerservice.service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.skyjava.course2.examinerservice.domain.Question;
import pro.skyjava.course2.examinerservice.service.JavaQuestionService;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private JavaQuestionService service;

    @BeforeEach
    void setUp() {
        service = new JavaQuestionService();
    }

    @Test
    void add_shouldAddQuestion() {
        Question q = service.add("What is Java?", "A programming language");
        assertEquals("What is Java?", q.getQuestion());
        assertEquals("A programming language", q.getAnswer());
        assertEquals(1, service.getAll().size());
    }

    @Test
    void add_shouldNotAddDuplicate() {
        service.add("Q1", "A1");
        service.add("Q1", "A1");
        assertEquals(1, service.getAll().size());
    }

    @Test
    void remove_shouldRemoveQuestion() {
        Question q = service.add("Q1", "A1");
        Question removed = service.remove(q);
        assertEquals(q, removed);
        assertTrue(service.getAll().isEmpty());
    }

    @Test
    void remove_shouldThrowWhenNotFound() {
        assertThrows(IllegalArgumentException.class, () ->
                service.remove(new Question("Nonexistent", "Answer")));
    }

    @Test
    void getAll_shouldReturnAllQuestions() {
        service.add("Q1", "A1");
        service.add("Q2", "A2");
        Collection<Question> all = service.getAll();
        assertEquals(2, all.size());
    }

    @Test
    void getRandomQuestion_shouldReturnQuestion() {
        service.add("Q1", "A1");
        Question random = service.getRandomQuestion();
        assertNotNull(random);
    }

    @Test
    void getRandomQuestion_shouldThrowWhenEmpty() {
        assertThrows(IllegalStateException.class, () -> service.getRandomQuestion());
    }
}