package com.tg_quiz.QuizBot.command.all_commands;

import com.tg_quiz.QuizBot.command.DefaultCommand;
import com.tg_quiz.QuizBot.common.Context;
import com.tg_quiz.QuizBot.common.UserState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


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
        final String answer = String.format("Hell0, %s, we present you our new bot to accept your request about creating telegram bot!\nAre you ready?", update.getMessage().getChat().getFirstName());
        final SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        message.setText(answer);
        return message;
    }
}
