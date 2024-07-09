package com.tg_quiz.QuizBot.command.all_commands;

import com.tg_quiz.QuizBot.command.DefaultCommand;
import com.tg_quiz.QuizBot.common.Context;
import com.tg_quiz.QuizBot.common.Question;
import com.tg_quiz.QuizBot.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartQuizCommand implements DefaultCommand<SendMessage> {

    private final QuestionService questionService;

    @Override
    public SendMessage executeCommand(Context context) throws IOException {
        final var list = questionService.getQuestions();
        for (var elem:
             list.getStairs()) {
             log.info(elem.getQuestion() + " - " + elem.getAnswers());
        }
        return createMessage(context, list.getStairs());
    }

    private SendMessage createMessage(Context context, List<Question> questionList) {

        final SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(context.getUpdate().getMessage().getChatId())); //FIXME обсер с выводом пользователю
        message.setText(questionList.getFirst().toString());
        return message;
    }
}
