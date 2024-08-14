package com.tg_quiz.QuizBot.command.all_commands;

import com.tg_quiz.QuizBot.command.DefaultCommand;
import com.tg_quiz.QuizBot.common.Context;
import com.tg_quiz.QuizBot.common.UserState;
import com.tg_quiz.QuizBot.constants.Constants;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.util.Map;

@Component
public class SendUserStateToMyTgCommand implements DefaultCommand<SendMessage> {
    @Override
    public SendMessage executeCommand(Context context, UserState userState) throws IOException {
        return SendMessage.builder()
                .chatId(Constants.SpetialTelegramTags.PRODUCER_CHAT_ID)
                .text(String.format(Constants.Messages.USER_ALL_ANSWERS_MESSAGE,  userState.getAnswers().values()))
                .build();
    }
}
