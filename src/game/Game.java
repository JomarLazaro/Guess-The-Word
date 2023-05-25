package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.Random;

// Game class use a inheritance
public class Game extends JFrame {
    private JLabel questionLabel;
    private JTextField answerField;
    private JLabel resultLabel;
    private int livesRemaining;
    private int currentQuestionIndex;
    private String[] questions;
    private String[] answers;

    public Game() {
        // Call initializeQuestions() and initializeAnswers() to set up questions and answers
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

    // Set up the questions array for inheritance
    private void initializeQuestions() {
        questions = new String[]{"APPLE", "BANANA", "ORANGE","SINGAPORE","NEWYORK","PHILIPPINES","COMMUNICATION", "ABSTRACTION", "INHERITANCE"};
    }

    // Set up the answers array for inheritance
    private void initializeAnswers() {
        answers = new String[]{"APPLE", "BANANA", "ORANGE","SINGAPORE","NEWYORK","PHILIPPINES","COMMUNICATION", "ABSTRACTION", "INHERITANCE"};
    }

    private void displayQuestion() {
        questionLabel.setText(shuffleWord(questions[currentQuestionIndex]));
        resultLabel.setText("" + livesRemaining);
        answerField.setText("");
        
        // Resize the frame to fit the question label
        Dimension labelSize = questionLabel.getPreferredSize();
        setSize(950, 625);
    }

    // Function for Shuffling letters in word
    private String shuffleWord(String word) {
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
    
    private void checkAnswer() {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Game game = new Game();
                game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                game.setSize(950, 625);
                game.setVisible(true);
                game.setResizable(false);
                game.setLocationRelativeTo(null); // Center the frame on the screen
            }
        });
    }
}
