package com.tg_quiz.QuizBot.command.all_commands;

import com.tg_quiz.QuizBot.command.DefaultCommand;
import com.tg_quiz.QuizBot.common.Context;
import com.tg_quiz.QuizBot.common.UserState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.tg_quiz.QuizBot.constants.Constants.Messages.START_MESSAGE;


@Slf4j
@Component
public class StartCommand implements DefaultCommand<SendMessage> {

    @Override
    public SendMessage executeCommand(Context context, UserState userState) {
        log.info("Выполнение команды /start пользователю {}", context.getUpdate().getMessage().getChat().getUserName());
        return getAnswer(context.getUpdate());
    }

    /**
    *<p>Генерирует приветственное сообщение пользователю</p> */
    private SendMessage getAnswer(Update update) {
        final String answer = String.format(START_MESSAGE, update.getMessage().getChat().getFirstName());
        return SendMessage.builder()
                .text(answer)
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .build();
    }
}
