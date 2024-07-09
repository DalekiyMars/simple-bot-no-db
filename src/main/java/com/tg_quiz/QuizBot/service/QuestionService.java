package com.tg_quiz.QuizBot.service;

import com.google.gson.Gson;
import com.tg_quiz.QuizBot.common.QuestionsList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.tg_quiz.QuizBot.constants.Constants.Paths.QUESTIONS_PATH;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final Gson gson;
    private final FileHandler fileHandler;
    public QuestionsList getQuestions() {
        return gson.fromJson(fileHandler.readJsonFromFile(QUESTIONS_PATH), QuestionsList.class);
    }
}
