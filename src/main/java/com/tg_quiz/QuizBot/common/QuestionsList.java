package com.tg_quiz.QuizBot.common;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class QuestionsList {

    @SerializedName("Quests")
    private List<Question> stairs;
}
