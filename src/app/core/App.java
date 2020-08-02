package app.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import app.core.db.dbUtil;

public class App {

    public static void main(String[] args) {
        try(Connection conn = dbUtil.getConnection()) {
            dbUtil.fetchQuiz(conn, null);
            Quiz quiz = dbUtil.fetchQuiz(conn, null);

            Scanner keyBoardInput = new Scanner(System.in);
            System.out.println("Hva heter du");
            String name = keyBoardInput.nextLine();
            User user = new User(name);

            for (Question q : quiz.getQuizSet()) {
                System.out.println(q.getPrompt());
                Character answer = keyBoardInput.nextLine().charAt(0);

                if (answer.equals(q.getAnswer())) {
                    user.incrementScore();
                }
            }

            System.out.println("You got " + user.getScore() + "/" + quiz.getQuizLength() + " correct answers.");
            dbUtil.insertUser(conn, user);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
