package com.tg_quiz.QuizBot.db.repository;

import com.tg_quiz.QuizBot.common.UserState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserStateRepository extends JpaRepository<UserState, Long> {
    UserState findUserStateByTelegramTag(String tag);
}
