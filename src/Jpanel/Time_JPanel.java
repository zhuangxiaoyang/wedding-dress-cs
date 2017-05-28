package Jpanel;

import java.awt.Label;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;


public class Time_JPanel extends JPanel {
	Date date=new Date();	
	public Label today_label=new Label();
	public Label time_label=new Label();
	public int hour;
	public int minute;
	public int second;
	public String day;
	public String moment;
	public Time_JPanel(){
		//��today��time�ı�ǩ�Ϸֱ���ʾ��ǰ���ڣ�ʱ��
		 SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy��MM:dd-EEEE"); 
		 day="������"+bartDateFormat.format(date);
		 hour=date.getHours();
	     minute=date.getMinutes();
		 second=date.getSeconds();
		 moment="��ǰʱ���ǣ�"+hour+":"+minute+":"+second;		
		today_label.setText(day);
		time_label.setText(moment);
		this.add(today_label);
		this.add(time_label);
	

	}
	

}

