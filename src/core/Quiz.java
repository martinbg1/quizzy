package core;

import java.util.ArrayList;

public class Quiz {

    private ArrayList<Question> quizSet;

    public Quiz(ArrayList<Question> quizSet){
        this.quizSet = quizSet;
    }

    public ArrayList<Question> getQuizSet(){
        return this.quizSet;
    }

    public int getQuizLength() {
        return this.quizSet.size();
    }


}
