import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Frame extends JFrame {
    
    JLabel dayLabel;
    JLabel dateLabel;
    JLabel timeLabel;
    SimpleDateFormat dateFormat;
    SimpleDateFormat dayFormat;
    SimpleDateFormat timeFormat;
    String time;
    String day;
    String date;

    Frame(){

        timeFormat = new SimpleDateFormat("HH:mm:ss");
        dayFormat = new SimpleDateFormat("EEEEE");
        dateFormat = new SimpleDateFormat("dd,MM,yyyy");

        dayLabel = new JLabel();
        dayLabel.setFont(new Font("Verdanna",Font.PLAIN,35));
        dayLabel.setForeground(Color.green);

        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Ink Free",Font.PLAIN,25));
        dateLabel.setForeground(Color.green);

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Ink Free",Font.PLAIN,50));
        timeLabel.setForeground(Color.green);
        
        this.setTitle("Clock :)");
        this.getContentPane().setBackground(Color.black);
        this.add(timeLabel);
        this.add(dateLabel);
        this.add(dayLabel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setResizable(false);
        this.setSize(350,200);
        this.setVisible(true);

        setTime();
    }

    public void setTime(){
        while(true){

            time = timeFormat.format(Calendar.getInstance().getTime());
            timeLabel.setText(time);

            day = dayFormat.format(Calendar.getInstance().getTime());
            dayLabel.setText(day);

            date = dateFormat.format(Calendar.getInstance().getTime());
            dateLabel.setText(date);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}