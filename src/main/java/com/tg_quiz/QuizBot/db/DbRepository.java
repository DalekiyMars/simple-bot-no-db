package com.tg_quiz.QuizBot.db;

import com.tg_quiz.QuizBot.common.UserState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DbRepository extends JpaRepository<UserState, Long> {

}
