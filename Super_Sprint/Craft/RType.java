package Craft;

import javax.swing.JFrame;
 
public class RType extends JFrame {

    public RType() {

        add(new Board());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 700);
        setLocationRelativeTo(null);
        setTitle("Le jeu qui tue");
        setResizable(false);
        setVisible(true);
        setAlwaysOnTop(true);
    }

    public static void main(String[] args) {
        new RType();        
    }
}