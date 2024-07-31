package com.tg_quiz.QuizBot.common;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class UserState {

    private int currentQuestion;
    private Map<Integer, String> answers;
    //@Id
    private final long chatId;
    private String telegramTag;
    private LocalDateTime lastActivityTime;

    public UserState(long chatId) {
        this.currentQuestion = 0;
        this.answers = new HashMap<>();
        this.chatId = chatId;
        this.lastActivityTime = LocalDateTime.now();
    }

    public void setCurrentQuestion(short currentQuestion) {
        this.currentQuestion = currentQuestion;
        this.lastActivityTime = LocalDateTime.now();
    }


}
