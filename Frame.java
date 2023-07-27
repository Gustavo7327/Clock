import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Frame extends JFrame {
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
    Frame() {
        
        timeField = new JTextField();
        timeField.setPreferredSize(new Dimension(110,22));
        booleanButton = true;
        button = new JButton();
        button.setText("Definir Alarme");
        button.setPreferredSize(new Dimension(200,25));
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==button && booleanButton == true){
                    timeField.setEditable(false);
                    button.setText("Redefinir Alarme");
                    booleanButton = false; 
                } else if(e.getSource()==button && booleanButton==false){
                    timeField.setEditable(true);
                    button.setText("Definir Alarme");
                    booleanButton = true; 
                }
                
            } 
        });
        
        timeFormat = new SimpleDateFormat("HH:mm:ss");
        dayFormat = new SimpleDateFormat("EEEEE");
        dateFormat = new SimpleDateFormat("dd,MM,yyyy");

        dayLabel = new JLabel();
        dayLabel.setFont(new Font("Verdanna",Font.PLAIN,40));
        dayLabel.setForeground(Color.green);

        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Ink Free",Font.PLAIN,28));
        dateLabel.setForeground(Color.green);

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Ink Free",Font.PLAIN,50));
        timeLabel.setForeground(Color.green);
        
        this.setTitle("Clock :)");
        this.getContentPane().setBackground(Color.black);
        this.add(timeLabel);
        this.add(dateLabel);
        this.add(dayLabel);
        this.add(timeField);
        this.add(button);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setResizable(false);
        this.setSize(350,260);
        this.setVisible(true);

        setTime();
    }

    public void setTime()  {
        while(true){

            time = timeFormat.format(Calendar.getInstance().getTime());
            timeLabel.setText(time);
            
            day = dayFormat.format(Calendar.getInstance().getTime());
            dayLabel.setText(day);

            date = dateFormat.format(Calendar.getInstance().getTime());
            dateLabel.setText(date);

            if(timeField.getText().equals(time)){
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
    public void tocarAlarme() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        File file = new File("alarm-clock.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
}