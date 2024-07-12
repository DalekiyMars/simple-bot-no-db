package com.tg_quiz.QuizBot.command.all_commands;

import com.tg_quiz.QuizBot.command.DefaultCommand;
import com.tg_quiz.QuizBot.common.Context;
import com.tg_quiz.QuizBot.common.UserState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;

@Component
@Slf4j
public class RestartCommand implements DefaultCommand<SendMessage> {
    @Override
    public SendMessage executeCommand(Context context, UserState userState) throws IOException {
        return refreshAllDataUser(userState);
    }

    private SendMessage refreshAllDataUser (UserState userState){
        //TODO удаление записи о пользователе
        log.info("Пользователь {} хочет перепройти опрос", userState.getTelegramTag());
        userState.setCurrentQuestion(-1);

        return SendMessage.builder()
                .chatId(userState.getChatId())
                .text("Your data refreshed!")
                .build();
    }
}
