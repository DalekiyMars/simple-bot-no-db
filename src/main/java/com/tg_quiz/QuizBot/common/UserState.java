package com.tg_quiz.QuizBot.common;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class UserState {

    private long chatId;
    private int currentQuestion;

    public void setAnswerById(int questionId, String userAnswer) {
        this.answers.put(questionId, userAnswer);
    }

    private Map<Integer, String> answers;
    private String telegramTag;
    private LocalDateTime lastActivityTime;

    public UserState(long chatId) {
        this.currentQuestion = 0;
        this.answers = new HashMap<>();
        this.chatId = chatId;
        this.lastActivityTime = LocalDateTime.now();
    }

    public UserState() {

    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
        this.lastActivityTime = LocalDateTime.now();
    }
}
