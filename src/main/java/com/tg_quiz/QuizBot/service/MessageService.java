package com.tg_quiz.QuizBot.service;

import com.tg_quiz.QuizBot.command.all_commands.SendUserStateToMyTgCommand;
import com.tg_quiz.QuizBot.common.Context;
import com.tg_quiz.QuizBot.common.UserState;
import com.tg_quiz.QuizBot.constants.Constants;
import com.tg_quiz.QuizBot.util.QuestionFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {
    private final QuestionFormat questionFormat;
    private final QuestionService questionService;

    /**
     * <p>Вызывает новый вопрос пользователю и добавляет к нему кнопки из json файла с вопросами</p>*/
    public SendMessage createMessage(Context context, UserState user) {
        if (user.getCurrentQuestion() <= questionService.getQuestions().getStairs().size() -1){
            user.setCurrentQuestion((user.getCurrentQuestion()+1));
            log.info("Пользователь {} получил вопрос {}", user.getTelegramTag(), questionService.getQuestions().getStairs().get(user.getCurrentQuestion()-1).getQuestion());
            return questionFormat.mapMessage(context, questionService.getQuestions().getStairs().get(user.getCurrentQuestion()-1), user);
        }

        log.info("Пользователь {} закончил опрос", user.getTelegramTag());
        return SendMessage.builder()
                .chatId(user.getChatId())
                .text(Constants.Messages.QUIZ_ENDED_MESSAGE).build();
    }
}
