import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Stopwatch extends JFrame implements ActionListener{
    JLabel cronometro;
    int seconds;
    int minutes;
    int time;
    JButton button;
    Stopwatch(){

        button = new JButton();

        cronometro = new JLabel();
        cronometro.setBounds(50,150,100,80);
        cronometro.setText(time+":"+minutes+":"+seconds);

        this.setTitle("Stopwatch");
        this.getContentPane().setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(310,260);
        this.add(cronometro);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
