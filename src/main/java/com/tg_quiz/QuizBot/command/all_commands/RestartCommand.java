package com.tg_quiz.QuizBot.command.all_commands;

import com.tg_quiz.QuizBot.command.DefaultCommand;
import com.tg_quiz.QuizBot.common.Context;
import com.tg_quiz.QuizBot.common.UserState;
import com.tg_quiz.QuizBot.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@Slf4j
public class RestartCommand implements DefaultCommand<SendMessage> {
    @Override
    public SendMessage executeCommand(Context context, UserState userState) {
        return refreshAllDataUser(userState);
    }

    /**
    * <p>Сбрасывает счетчик вопросов пользователя до первого вопроса для перепрохождения с нуля</p>
    * */
    private SendMessage refreshAllDataUser (UserState userState){
        log.info("Пользователь {} хочет перепройти опрос", userState.getTelegramTag());
        userState.setCurrentQuestion((short) 0);

        return SendMessage.builder()
                .chatId(userState.getChatId())
                .text(Constants.Messages.QUIZ_RESTARTED_MESSAGE)
                .build();
    }
}
