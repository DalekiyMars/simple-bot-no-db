package com.tg_quiz.QuizBot.common;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class Question {
    @SerializedName("question")
    private String question;

    @SerializedName("answers")
    private List<String> answers;
}
