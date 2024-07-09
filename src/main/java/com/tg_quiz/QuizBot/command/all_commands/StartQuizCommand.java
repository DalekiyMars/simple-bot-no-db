package com.tg_quiz.QuizBot.command.all_commands;

import com.tg_quiz.QuizBot.command.DefaultCommand;
import com.tg_quiz.QuizBot.common.Context;
import com.tg_quiz.QuizBot.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartQuizCommand implements DefaultCommand<SendMessage> {

    private final QuestionService questionService;

    @Override
    public SendMessage executeCommand(Context context){
        final var list = questionService.getQuestions();
        List<String> temp = new ArrayList<>();
        for (var elem:
             list.getStairs()) {
             log.info(elem.getQuestion());
             temp.add(elem.getQuestion());
        }
        return createMessage(context, temp);
    }

    private SendMessage createMessage(Context context, List<String> questionList) {

        final SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(context.getUpdate().getMessage().getChatId()));
        message.setText(String.join("\n", questionList));
        return message;
    }
}
