package game;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StartGame extends JFrame {

    private Image backgroundImg;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StartGame frame = new StartGame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public StartGame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 950, 625);

        // Create a custom content pane with double buffering enabled
        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Load the background image from the "background" package
        ImageIcon backgroundImageIcon = new ImageIcon(getClass().getResource("/background/Start1.gif"));
        backgroundImg = backgroundImageIcon.getImage();

        JButton btnStart = new JButton("");
        btnStart.setBackground(new Color(240, 240, 240));
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game game = new Game();
                game.setVisible(true);
                dispose();
            }
        });
        btnStart.setBounds(360, 338, 218, 38);
        btnStart.setContentAreaFilled(false);	
        btnStart.setForeground(new Color(64, 0, 64));
        btnStart.setBorderPainted(false);
        btnStart.setBorder(BorderFactory.createBevelBorder(10));
        contentPane.add(btnStart);
    }
}
