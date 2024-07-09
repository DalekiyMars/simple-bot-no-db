package com.tg_quiz.QuizBot.command;

import com.tg_quiz.QuizBot.common.Context;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;

import java.io.IOException;

public interface DefaultCommand<Message extends BotApiMethodMessage> {
    Message executeCommand(Context context) throws IOException;
}
