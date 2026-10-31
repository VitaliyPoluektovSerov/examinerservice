package pro.skyjava.course2.examinerservice.controller;

import org.springframework.web.bind.annotation.*;
import pro.skyjava.course2.examinerservice.domain.Question;
import pro.skyjava.course2.examinerservice.service.QuestionServices;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaController {

    private final QuestionServices service;

    public JavaController(QuestionServices service) {
        this.service = service;
    }

    @GetMapping("/add")
    public Question addQuestion(@RequestParam String question, @RequestParam String answer) {
        return service.add(question, answer);

    }

    @GetMapping
    public Collection<Question> getQuestions() {
        return service.getAll();
    }

    @DeleteMapping("/remove")
    public Question removeQuestion(@RequestParam String question, @RequestParam String answer) {
        return service.remove(new Question(question, answer));
    }
}