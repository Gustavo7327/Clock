import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Stopwatch extends JFrame implements ActionListener {

    JLabel cronometro;
    JButton startButton, pauseButton, resetButton;
    JMenuBar menubar;
    JMenu menu;
    JMenuItem menuitem;

    int seconds = 0;
    int minutes = 0;
    int hours = 0;

    boolean running = false;
    Timer timer;

    Stopwatch() {

        menubar = new JMenuBar();
        menu = new JMenu("Options");
        menuitem = new JMenuItem("Clock");
        menuitem.addActionListener(this);
        menubar.add(menu);
        menu.add(menuitem);
        menubar.setBackground(Color.black);
        menubar.setForeground(Color.green);
        menu.setBackground(Color.black);
        menu.setForeground(Color.green);
        menuitem.setBackground(Color.black);
        menuitem.setForeground(Color.green);

        this.setTitle("Stopwatch");
        this.getContentPane().setBackground(Color.black);
        this.setJMenuBar(menubar);

        cronometro = new JLabel();
        cronometro.setFont(new Font("Ink Free", Font.PLAIN, 40));
        cronometro.setForeground(Color.green);
        cronometro.setHorizontalAlignment(SwingConstants.CENTER);
        cronometro.setText(formatTime(hours, minutes, seconds));

        startButton = createButton("Start");
        pauseButton = createButton("Pause");
        resetButton = createButton("Reset");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        this.setResizable(false);
        this.setSize(280, 340);
        this.setLocationRelativeTo(null);

        this.add(cronometro);
        this.add(startButton);
        this.add(pauseButton);
        this.add(resetButton);
        this.setVisible(true);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuitem) {
            if (timer != null) {
                timer.cancel();
            }
            new Frame();
            this.dispose();
        }

        if (e.getSource() == startButton) {
            if (!running) {
                running = true;
                startTimer();
            }
        }

        if (e.getSource() == pauseButton) {
            running = false;
            if (timer != null) {
                timer.cancel();
            }
        }

        if (e.getSource() == resetButton) {
            running = false;
            if (timer != null) {
                timer.cancel();
            }
            seconds = 0;
            minutes = 0;
            hours = 0;
            cronometro.setText(formatTime(hours, minutes, seconds));
        }
    }

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (running) {
                    seconds++;
                    if (seconds == 60) {
                        seconds = 0;
                        minutes++;
                    }
                    if (minutes == 60) {
                        minutes = 0;
                        hours++;
                    }
                    cronometro.setText(formatTime(hours, minutes, seconds));
                }
            }
        }, 0, 1000);
    }
}
