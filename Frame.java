import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Frame extends JFrame implements ActionListener{
    JTextField timeField;
    JTextField minuteField;
    JTextField secondField;
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
        minuteField = new JTextField();
        secondField = new JTextField();
        timeField.setPreferredSize(new Dimension(70,22));
        minuteField.setPreferredSize(new Dimension(70,22));
        secondField.setPreferredSize(new Dimension(70,22));
        booleanButton = true;
        button = new JButton();
        button.setText("Definir Alarme");
        button.setPreferredSize(new Dimension(200,25));
        button.addActionListener(this);
        
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
        this.setJMenuBar(menubar);
        this.add(timeLabel);
        this.add(dateLabel);
        this.add(dayLabel);
        this.add(timeField);
        this.add(minuteField);
        this.add(secondField);
        this.add(button);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setResizable(false);
        this.setSize(310,280);
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
            String comparation = timeField.getText()+":"+minuteField.getText()+":"+secondField.getText();
            if(comparation.equals(time)){
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==menuitem){
            new Stopwatch();
        }
        if(e.getSource()==button && booleanButton == true){
            timeField.setEditable(false);
            minuteField.setEditable(false);
            secondField.setEditable(false);
            button.setText("Redefinir Alarme");
            booleanButton = false; 
        } else if(e.getSource()==button && booleanButton==false){
            timeField.setEditable(true);
            minuteField.setEditable(true);
            secondField.setEditable(true);
            button.setText("Definir Alarme");
            booleanButton = true; 
        }      
    }
}