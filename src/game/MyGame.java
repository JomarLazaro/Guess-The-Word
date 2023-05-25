package game;

// inheritance
public class MyGame extends AbstractGame {

    @Override
    protected void initializeQuestions() {
        questions = new String[]{"QUESTION1", "QUESTION2", "QUESTION3"};
    }

    @Override
    protected void initializeAnswers() {
        answers = new String[]{"ANSWER1", "ANSWER2", "ANSWER3"};
    }

    @Override
    protected void displayQuestion() {
        // Implementation specific to MyGame
        // ...
    }
    

}
