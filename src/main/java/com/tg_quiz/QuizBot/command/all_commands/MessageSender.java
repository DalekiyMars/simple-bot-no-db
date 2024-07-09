package com.tg_quiz.QuizBot.command.all_commands;

import com.tg_quiz.QuizBot.command.DefaultCommand;
import com.tg_quiz.QuizBot.common.Context;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;

@Component
public class MessageSender implements DefaultCommand<SendMessage> {
    private String message;
    @Override
    public SendMessage executeCommand(Context context) throws IOException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(context.getUpdate().getMessage().getChatId()));
        sendMessage.setText(message);
        return sendMessage;
    }
}
