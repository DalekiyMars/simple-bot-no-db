package com.tg_quiz.QuizBot.common;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity(name = "users")
public class UserState {
    @Id
    @Column
    private long chatId;
    private int currentQuestion;

    @ElementCollection
    @MapKeyColumn(name = "question_id")
    @Column(name = "answer")
    private Map<Integer, String> answers;
    @Column(name = "user_tag")
    private String telegramTag;
    @Transient
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
