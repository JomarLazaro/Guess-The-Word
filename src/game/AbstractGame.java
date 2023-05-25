package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.Random;

public abstract class AbstractGame extends JFrame {
    protected JLabel questionLabel;
    protected JTextField answerField;
    protected JLabel resultLabel;
    protected int livesRemaining;
    protected int currentQuestionIndex;
    protected String[] questions;
    protected String[] answers;

    public AbstractGame() {
        initializeQuestions();
        initializeAnswers();

        getContentPane().setLayout(null); // Use absolute layout

        // Set up background image
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/background/Game.gif"));

        // Set up GUI components with absolute positioning
        questionLabel = new JLabel();
        questionLabel.setForeground(new Color(255, 255, 255));
        questionLabel.setFont(new Font("Verdana", Font.BOLD, 65));
        questionLabel.setBounds(62, 180, 810, 85);
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        answerField = new JTextField(10);
        answerField.setBackground(new Color(255, 255, 255));
        answerField.setForeground(new Color(0, 0, 0));
        answerField.setFont(new Font("Verdana", Font.PLAIN, 35));
        answerField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        answerField.setHorizontalAlignment(SwingConstants.CENTER);
        answerField.setBounds(268, 309, 396, 73);
        resultLabel = new JLabel();
        resultLabel.setForeground(new Color(255, 255, 255));
        resultLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        resultLabel.setBounds(791, 21, 81, 39);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add components to the frame
        getContentPane().add(questionLabel);
        getContentPane().add(answerField);
        getContentPane().add(resultLabel);

        // Create a JLabel to hold the background image
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        getContentPane().add(backgroundLabel);

        livesRemaining = 3;
        currentQuestionIndex = 0;

        // Add key listener for the answer field
        answerField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    checkAnswer();
                }
            }
        });

        // Display the first question
        displayQuestion();
    }

    protected abstract void initializeQuestions();

    protected abstract void initializeAnswers();

    protected abstract void displayQuestion();

    protected String shuffleWord(String word) {
        char[] characters = word.toCharArray();
        Random random = new Random();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }

    protected void checkAnswer() {
        String userAnswer = answerField.getText();
        if (userAnswer.equalsIgnoreCase(answers[currentQuestionIndex])) {
            resultLabel.setText("Correct!");
            currentQuestionIndex++;
            if (currentQuestionIndex < questions.length) {
                displayQuestion();
            } else {
                dispose();
                Congrats congrats = new Congrats();
                congrats.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Wrong answer. Try again.");
            answerField.setText("");
            livesRemaining--;
            if (livesRemaining == 0) {
                dispose();
                Gameover gameover = new Gameover();
                gameover.setVisible(true);
            } else {
                resultLabel.setText("" + livesRemaining);
            }
        }
    }
}
