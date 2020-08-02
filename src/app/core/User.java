package app.core;

public class User {
    private String name;
    private int score = 0;

    public User(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public int getScore(){
        return this.score;
    }

    public  void incrementScore() {
        this.score++;
    }
}
