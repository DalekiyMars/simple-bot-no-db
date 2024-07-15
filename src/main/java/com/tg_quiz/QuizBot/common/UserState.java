package com.tg_quiz.QuizBot.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class UserState {
    private int currentQuestion;
    private Map<String, String> answers;
    private final long chatId;
    private String telegramTag;

    public UserState(long chatId) {
        this.currentQuestion = -1;
        this.answers = new HashMap<>();
        this.chatId = chatId;
    }
}
