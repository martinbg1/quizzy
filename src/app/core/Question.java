package app.core;

public class Question {
    private String prompt;
    private Character answer;
    private String theme;

    public Question(String prompt, Character answer, String theme) {
        this.prompt = prompt;
        this.answer = answer;
        this.theme = theme;
    }

    public String getPrompt() {
        return this.prompt;
    }

    public Character getAnswer() {
        return this.answer;
    }
}
