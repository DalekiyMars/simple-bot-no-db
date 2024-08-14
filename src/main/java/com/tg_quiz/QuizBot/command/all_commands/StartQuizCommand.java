package com.tg_quiz.QuizBot.command.all_commands;

import com.tg_quiz.QuizBot.command.DefaultCommand;
import com.tg_quiz.QuizBot.common.Context;
import com.tg_quiz.QuizBot.common.UserState;
import com.tg_quiz.QuizBot.constants.Constants;
import com.tg_quiz.QuizBot.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartQuizCommand implements DefaultCommand<SendMessage> {
    private final MessageService messageService;
    @Override
    public SendMessage executeCommand(Context context, UserState user){
        user.setTelegramTag(context.getUpdate().getMessage().getChat().getUserName());
        if (user.getCurrentQuestion() < 1){
            return messageService.createMessage(context, user);
        } else {
            return SendMessage.builder()
                    .chatId(user.getChatId())
                    .text(Constants.Messages.NEEDED_ANSWER_FOR_PREVIOUS_QUESTION_ANSWER)
                    .build();
        }
    }


}
