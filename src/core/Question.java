package core;

public class Question {
    private String prompt;
    private String answer;
    private String theme;

    public Question(String prompt, String answer, String theme) {
        this.prompt = prompt;
        this.answer = answer;
        this.theme = theme;
    }

    public String getPrompt() {
        return this.prompt;
    }

    public String getAnswer() {
        return this.answer;
    }
}
