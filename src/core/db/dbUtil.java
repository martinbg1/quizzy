package core.db;

import core.User;
import core.Question;
import core.Quiz;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class dbUtil {

    private static final String CONN = "jdbc:sqlite:C://Users/47984/Documents/prog/intervju/quizzy/quizzy.db";
    private static final String[] QUIZ_THEMES = {"history", "geography"};

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(CONN);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    /**
     * Fetches the quiz from the db
     *
     * @param conn Connection to the db
     * @param theme Theme of the quiz. null for question from all themes
     * @throws SQLException
     */
    public static Quiz fetchQuiz(Connection conn, String theme) throws SQLException {
        ResultSet rs;
        ArrayList<Question> quizSet = new ArrayList<Question>();

        // exception handling
        if(theme == null) {
            Statement stmt = conn.createStatement();
            rs =  stmt.executeQuery("SELECT prompt, answer, theme from questions");
        }
        else if(Arrays.stream(QUIZ_THEMES).anyMatch(theme::equals)) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT prompt, answer, theme from questions WHERE theme = ?");
            pstmt.setString(1, theme);
            rs = pstmt.executeQuery();
        } else {
            return new Quiz(null);
        }


        while (rs.next()) {
            Question q = new Question(rs.getString("prompt"), rs.getString("answer"), rs.getString("theme"));
            quizSet.add(q);
        }

        return new Quiz(quizSet);
    }


    /**
     * Inserts the user and his/her score to the db
     *
     * @param conn Connection to the db
     * @param user User to insert
     * @throws SQLException
     */
    public static void insertUser(Connection conn, User user) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users VALUES (NULL, ?, ?)");
        pstmt.setString(1, user.getName());
        pstmt.setInt(2, user.getScore());
        pstmt.executeUpdate();
    }


    /**
     * Fetches the top k users
     *
     * @param conn Connection to the db
     * @param k Number of users to fetch
     * @throws SQLException
     */
    public static ArrayList<User> fetchLeaderBoard(Connection conn, int k) throws SQLException {
        ArrayList<User> leaderboard = new ArrayList<>();
        PreparedStatement pstmt = conn.prepareStatement("SELECT name, score FROM users ORDER BY score DESC LIMIT ?;");
        pstmt.setInt(1, k);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            User user = new User(rs.getString("name"));
            user.setScore(rs.getInt("score"));
            leaderboard.add(user);
        }
        return leaderboard;
    }
}
