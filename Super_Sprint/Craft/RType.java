package Craft;


import javax.swing.JFrame;

import Vue.JBoard;
 
public class RType extends JFrame {

    public RType() {

        add(new JBoard());

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