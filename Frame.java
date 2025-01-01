import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Frame extends JFrame implements ActionListener {

    JTextField timeField;
    JButton button;
    JLabel dayLabel;
    JLabel dateLabel;
    JLabel timeLabel;
    SimpleDateFormat dateFormat;
    SimpleDateFormat dayFormat;
    SimpleDateFormat timeFormat;
    String time;
    String day;
    String date;
    boolean booleanButton;
    JMenuBar menubar;
    JMenu menu;
    JMenuItem menuitem;

    Frame() {

        menubar = new JMenuBar();
        menu = new JMenu("Options");
        menuitem = new JMenuItem("Stopwatch");
        menuitem.addActionListener(this);
        menubar.add(menu);
        menu.add(menuitem);
        menubar.setBackground(Color.black);
        menubar.setForeground(Color.green);
        menu.setBackground(Color.black);
        menu.setForeground(Color.green);
        menuitem.setBackground(Color.black);
        menuitem.setForeground(Color.green);

        timeField = new JTextField();
        timeField.setPreferredSize(new Dimension(150, 30));
        timeField.setBackground(Color.black);
        timeField.setForeground(Color.green);
        timeField.setFont(new Font("Ink Free", Font.PLAIN, 20));
        timeField.setHorizontalAlignment(JTextField.CENTER);
        timeField.setBorder(BorderFactory.createLineBorder(Color.green, 2));

        booleanButton = true;
        button = new JButton();
        button.setText("Definir Alarme");
        button.setPreferredSize(new Dimension(200, 30));
        button.setBackground(Color.black);
        button.setForeground(Color.green);
        button.setFont(new Font("Ink Free", Font.PLAIN, 18));
        button.setBorder(BorderFactory.createLineBorder(Color.green, 2));
        button.setFocusPainted(false);
        button.addActionListener(this);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.green);
                button.setForeground(Color.black);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.black);
                button.setForeground(Color.green);
            }
        });

        timeFormat = new SimpleDateFormat("HH:mm:ss");
        dayFormat = new SimpleDateFormat("EEEEE");
        dateFormat = new SimpleDateFormat("dd,MM,yyyy");

        dayLabel = new JLabel();
        dayLabel.setFont(new Font("Verdanna", Font.PLAIN, 40));
        dayLabel.setForeground(Color.green);

        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Ink Free", Font.PLAIN, 28));
        dateLabel.setForeground(Color.green);

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Ink Free", Font.PLAIN, 50));
        timeLabel.setForeground(Color.green);

        this.setTitle("Clock :)");
        this.getContentPane().setBackground(Color.black);
        this.setJMenuBar(menubar);

        JPanel alarmPanel = new JPanel();
        alarmPanel.setBackground(Color.black);
        alarmPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        alarmPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); 
        alarmPanel.add(timeField);
        alarmPanel.add(button);

        this.add(timeLabel);
        this.add(dateLabel);
        this.add(dayLabel);
        this.add(alarmPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        this.setResizable(false);
        this.setSize(380, 320);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        setTime();
    }

    public void setTime() {
        while (true) {

            time = timeFormat.format(Calendar.getInstance().getTime());
            timeLabel.setText(time);

            day = dayFormat.format(Calendar.getInstance().getTime());
            dayLabel.setText(day);

            date = dateFormat.format(Calendar.getInstance().getTime());
            dateLabel.setText(date);
            if (timeField.getText().equals(time) && !booleanButton) {
                try {
                    tocarAlarme();
                } catch (UnsupportedAudioFileException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (LineUnavailableException e1) {
                    e1.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void tocarAlarme() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("alarm-clock.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuitem) {
            new Stopwatch();
            this.dispose();
        }

        if (e.getSource() == button && booleanButton == true) {
            String userTime = timeField.getText();

            if (userTime.matches("^(?:[01]\\d|2[0-3]):(?:[0-5]\\d):(?:[0-5]\\d)$")) {
                timeField.setEditable(false);
                button.setText("Redefinir Alarme");
                booleanButton = false;

            } else {
                JOptionPane.showMessageDialog(null, "Invalid time format. Please enter in HH:mm:ss format.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == button && booleanButton == false) {
            timeField.setEditable(true);
            timeField.setText("");
            button.setText("Definir Alarme");
            booleanButton = true;
        }
    }
}
