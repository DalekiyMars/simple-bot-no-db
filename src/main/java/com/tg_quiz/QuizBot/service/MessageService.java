package com.tg_quiz.QuizBot.service;

import com.tg_quiz.QuizBot.common.Context;
import com.tg_quiz.QuizBot.common.UserState;
import com.tg_quiz.QuizBot.util.QuestionFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
@RequiredArgsConstructor
public class MessageService { //TODO добавить логи
    private final QuestionFormat questionFormat;
    private final QuestionService questionService;
    public SendMessage createMessage(Context context, UserState user) {
        if (user.getCurrentQuestion() < questionService.getQuestions().getStairs().size() -1){
            user.setCurrentQuestion(user.getCurrentQuestion()+1);
            return questionFormat.mapMessage(context, questionService.getQuestions().getStairs().get(user.getCurrentQuestion()));
        }
        return SendMessage.builder()
                .chatId(user.getChatId())
                .text("Quiz ended").build();
    }
}
