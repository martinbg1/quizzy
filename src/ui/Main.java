package ui;

import core.Question;
import core.Quiz;
import core.User;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import core.db.dbUtil;


class Main extends JFrame {
    public static Main instance;
    public static Container namePage;
    public static Container questionPage;
    public static Container leaderBoardPage;
    public static Connection conn;
    public static User user;
    public static Quiz quiz;
    public static ArrayList<User> leaderBoard;


    static Container createNamePage() {
        JPanel c = new JPanel(null);
        c.setSize(600, 600);

        JLabel title = new JLabel("Quizzy");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(200, 30);
        c.add(title);

        JLabel name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(125, 100);
        c.add(name);

        JTextField tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(225, 100);
        c.add(tname);

        JButton sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(150, 40);
        sub.setLocation(225, 450);
        sub.addActionListener(e -> {
            user = new User(tname.getText());
            navigateTo(questionPage);
        });
        c.add(sub);

        return c;
    }

    static Container createQuestionPage(Quiz quiz) {
        JPanel c = new JPanel(null);
        c.setSize(1000, 1000);

        int i = 1;
        int Y = 150;
        ArrayList<JTextField> answers = new ArrayList<>();
        // Creates textfields for each question and puts them underneath each other
        for (Question q: quiz.getQuizSet()) {


            JLabel title = new JLabel("Question " + i + " of " + quiz.getQuizSet().size());
            title.setFont(new Font("Arial", Font.PLAIN, 30));
            title.setSize(300, 20);
            title.setLocation(100, Y);
            c.add(title);


            JTextArea prompt = new JTextArea(q.getPrompt());
            prompt.setFont(new Font("Arial", Font.PLAIN, 20));
            prompt.setEditable(false);
            prompt.setBackground(c.getBackground());
            prompt.setSize(500, 100);
            prompt.setLocation(100, Y + 40);
            c.add(prompt);


            JTextField answer = new JTextField();
            answer.setFont(new Font("Arial", Font.PLAIN, 20));
            answer.setSize(40, 30);
            answer.setLocation(800, Y + 40);
            c.add(answer);

            answers.add(answer);
            Y += 200;
            i++;
        }


        JButton sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 40);
        sub.setLocation(870, 900);
        sub.addActionListener(e -> {

            // loops through user's answers and compares to correct answers
            for (int j = 0; j < answers.size(); j++) {
                String aText = answers.get(j).getText();
                if (aText.toLowerCase().equals(quiz.getQuizSet().get(j).getAnswer())){
                    user.incrementScore();
                }
            }
            try {
                // Submits the result
                dbUtil.insertUser(conn, user);

                // create leaderboard
                leaderBoard = dbUtil.fetchLeaderBoard(conn, 5);
                leaderBoardPage = createLeaderBoardPage(leaderBoard);

                navigateTo(leaderBoardPage);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        c.add(sub);
        return c;
    }

    static Container createLeaderBoardPage(ArrayList<User> leaderBoard) throws SQLException {
        JPanel c = new JPanel(null);
        c.setSize(800, 800);


        JLabel title = new JLabel("Top " + leaderBoard.size() + " scores:");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        title.setSize(300, 30);
        title.setLocation(250, 30);
        c.add(title);

        int Y = 100;

        for (int i = 0; i < leaderBoard.size(); i++) {
            User u = leaderBoard.get(i);
            JLabel userName = new JLabel(i +1 + ". " + u.getName());
            userName.setFont(new Font("Arial", Font.PLAIN, 25));
            userName.setSize(450, 25);
            userName.setLocation(100, Y);
            c.add(userName);

            JLabel userScore = new JLabel(String.valueOf(u.getScore()));
            userScore.setFont(new Font("Arial", Font.PLAIN, 25));
            userScore.setSize(100, 25);
            userScore.setLocation(500, Y);
            c.add(userScore);

            Y += 100;
        }

        return c;
    }



    static void navigateTo(Container page) {
        instance.setContentPane(page);
        instance.setSize(page.getSize());

    }

    public static void main(String[] args) throws Exception {
        // Connects to db and fetches quiz
        conn = dbUtil.getConnection();
        quiz = dbUtil.fetchQuiz(conn, null);

        instance = new Main();
        instance.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        instance.setTitle("Quizzy!");
        instance.setVisible(true);

        // creates panels
        namePage = createNamePage();
        questionPage = createQuestionPage(quiz);



        navigateTo(namePage);
    }
}
