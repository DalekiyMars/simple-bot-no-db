package com.tg_quiz.QuizBot.util;

import com.tg_quiz.QuizBot.common.Context;
import com.tg_quiz.QuizBot.common.Question;
import com.tg_quiz.QuizBot.common.UserState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuestionFormat {
    /**
     * <p>Форматизирует вопрос - добавляет кнопки и тд</p>*/
    public SendMessage mapMessage(Context context, Question question, UserState user) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        SendMessage sendMessage = SendMessage.builder()
                                .chatId(user.getChatId())
                                .text(question.getQuestion())
                                .build();

        markupInline.setKeyboard(answersHandler(context, user, question));
        sendMessage.setReplyMarkup(markupInline);
        return sendMessage;
    }

    /**
     * <p>Выводит кнопочки ответов - каждая в отдельной строке</p>*/
    private List<InlineKeyboardButton> getInlineKeyboardButtons(String answer) {
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        rowInline1.add(InlineKeyboardButton.builder().text(answer).callbackData(answer).build());
        return rowInline1;
    }

    /**
     * <p>Проверяет, на каком вопросе пользователь, чтобы добавить "ленивые" ответы - кнопку с именем и тп</p>*/
    private List<List<InlineKeyboardButton>> answersHandler(Context context, UserState user, Question question){
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        if (question.getAnswers().isEmpty()){
            if (user.getCurrentQuestion() - 1 == 0) {
                log.info("Выведено имя пользователя в кнопках");
                rowsInline.add(getInlineKeyboardButtons(context.getUpdate().getMessage().getChat().getFirstName()));
            }
        } else {
            for (var elem :
                    question.getAnswers()) {
                rowsInline.add(getInlineKeyboardButtons(elem));
            }
        }
        return rowsInline;
    }
}
