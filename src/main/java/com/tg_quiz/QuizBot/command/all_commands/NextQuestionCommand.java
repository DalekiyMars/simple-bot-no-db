package com.tg_quiz.QuizBot.command.all_commands;

import com.tg_quiz.QuizBot.command.DefaultCommand;
import com.tg_quiz.QuizBot.common.Context;
import com.tg_quiz.QuizBot.common.UserState;
import com.tg_quiz.QuizBot.service.MessageService;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;

@Data
@Component
public class NextQuestionCommand implements DefaultCommand<SendMessage> {
    private final MessageService messageService;

    @Override
    public SendMessage executeCommand(Context context, UserState user) throws IOException {
        return messageService.createMessage(context, user);
    }
}
