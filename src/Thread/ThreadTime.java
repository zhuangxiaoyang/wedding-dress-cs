package Thread;

import java.text.SimpleDateFormat;
import java.util.Date;

import Jpanel.Time_JPanel;

public class ThreadTime extends Thread {
	Time_JPanel timepanel=new Time_JPanel();
	
	
	public int hour;
	public int minute;
	public int second;
	public String day;
	public String moment;
	public ThreadTime(Time_JPanel timepanel){
		this.timepanel=timepanel;

	}
	public void run() {
	while(true){
		try{
			//�����߳�ÿ��һ��ˢ��һ��ʱ�䣬��Time_Jpanel�ϵ�taday_label,time_label����ʾ
			 Date date=new Date();		
			 SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy��MM:dd-EEEE");  
			 hour=new Date().getHours();
		     minute=new Date().getMinutes();
			 second=new Date().getSeconds();
			 moment="��ǰʱ���ǣ�"+hour+":"+minute+":"+second;
			 day="������"+bartDateFormat.format(date);
			 timepanel.today_label.setText(day);
			 timepanel.time_label.setText(moment);
			 
			Thread.sleep(1000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	}
}
/*public class ThreadTime{
	public static void main(String[] args){
		
			ThreadTime t = new ThreadTime(); 
			
			 t.start(); 
			
		
	}
}*/