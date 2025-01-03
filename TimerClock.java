import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.*;

public class TimerClock extends JFrame implements ActionListener{

    JMenuBar menubar;
    JMenu menu;
    JMenuItem menuitem;
    JMenuItem menuitem2;

    JLabel timerLabel;
    JTextField hourField, minuteField, secondField;
    JButton startButton, pauseButton, resetButton;

    int hours, minutes, seconds = 0;
    boolean running = false;
    Timer timer;

    TimerClock(){

        menubar = new JMenuBar();
        menu = new JMenu("Options");
        menuitem = new JMenuItem("Clock");
        menuitem2 = new JMenuItem("Stopwatch");
        menuitem.addActionListener(this);
        menuitem2.addActionListener(this);
        menubar.add(menu);
        menu.add(menuitem);
        menu.add(menuitem2);
        menubar.setBackground(Color.black);
        menubar.setForeground(Color.green);
        menu.setBackground(Color.black);
        menu.setForeground(Color.green);
        menuitem.setBackground(Color.black);
        menuitem.setForeground(Color.green);
        menuitem2.setBackground(Color.black);
        menuitem2.setForeground(Color.green);

        this.setTitle("TimerClock");
        this.getContentPane().setBackground(Color.black);
        this.setJMenuBar(menubar);

        timerLabel = new JLabel();
        timerLabel.setFont(new Font("Ink Free", Font.PLAIN, 40));
        timerLabel.setForeground(Color.green);
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timerLabel.setText(formatTime(hours ,minutes, seconds));

        hourField = createInputField();
        minuteField = createInputField();
        secondField = createInputField();

        startButton = createButton("Start");
        pauseButton = createButton("Pause");
        resetButton = createButton("Reset");

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.setBackground(Color.black);
        inputPanel.add(new JLabel("Hours: "));
        inputPanel.add(hourField);
        inputPanel.add(new JLabel("Minutes: "));
        inputPanel.add(minuteField);
        inputPanel.add(new JLabel("Seconds: "));
        inputPanel.add(secondField);

        inputPanel.getComponent(0).setForeground(Color.green);
        inputPanel.getComponent(2).setForeground(Color.green);
        inputPanel.getComponent(4).setForeground(Color.green);

        this.add(timerLabel);
        this.add(inputPanel);
        this.add(startButton);
        this.add(pauseButton);
        this.add(resetButton);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        this.setResizable(false);
        this.setSize(380, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuitem) {
            new Frame();
            this.dispose();
        }

        if (e.getSource() == menuitem2) {
            new Stopwatch();
            this.dispose();
        }

        if(e.getSource() == startButton){
            if(!running){
                try{
                    hours = Integer.parseInt(hourField.getText());
                    minutes = Integer.parseInt(minuteField.getText());
                    seconds = Integer.parseInt(secondField.getText());

                    if(hours < 0 || minutes < 0 || minutes > 59 || seconds < 0 || seconds > 59){
                        JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
                    } 
                    running = true;
                    startTimer();
                    hourField.setEditable(false);
                    minuteField.setEditable(false);
                    secondField.setEditable(false);

                } catch(NumberFormatException n){
                    JOptionPane.showMessageDialog(this, "Please enter only numbers", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if(e.getSource() == pauseButton){
            running = false;
            if(timer != null){
                timer.cancel();
            }
        }

        if(e.getSource() == resetButton){
            running = false;
            if(timer != null){
                timer.cancel();
            }
            hours = 0;
            minutes = 0;
            seconds = 0;
            hourField.setEditable(true);
            minuteField.setEditable(true);
            secondField.setEditable(true);
            timerLabel.setText(formatTime(hours, minutes, seconds));
        }
    }

    private JTextField createInputField() {
        JTextField field = new JTextField(2);
        field.setFont(new Font("Ink Free", Font.PLAIN, 20));
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setBackground(Color.black);
        field.setForeground(Color.green);
        field.setBorder(BorderFactory.createLineBorder(Color.green, 2));
        return field;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.black);
        button.setForeground(Color.green);
        button.setFont(new Font("Ink Free", Font.PLAIN, 20));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.green, 2));
        button.setPreferredSize(new Dimension(200, 50));
        button.addActionListener(this);
        return button;
    }

    private String formatTime(int hours, int minutes, int seconds) {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (running) {
                    if (seconds == 0) {
                        if(minutes == 0){
                            if(hours == 0){
                                running = false;
                                timer.cancel();
                                JOptionPane.showMessageDialog(null, "Time's up!", "Timer", JOptionPane.INFORMATION_MESSAGE);
                            } else{
                                hours--;
                                minutes = 59;
                                seconds = 59;
                            }
                        } else{
                            minutes--;
                            seconds = 59;
                        }
                    } else{
                        seconds--;
                    }
                    timerLabel.setText(formatTime(hours, minutes, seconds));
                }
            }
        }, 0, 1000);
    }
    
}
