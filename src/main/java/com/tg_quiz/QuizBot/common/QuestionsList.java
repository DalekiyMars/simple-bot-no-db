package com.tg_quiz.QuizBot.common;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

import static com.tg_quiz.QuizBot.constants.Constants.Paths.QUESTIONS;

@Data
public class QuestionsList {

    @SerializedName(QUESTIONS)
    private List<Question> stairs;
}
