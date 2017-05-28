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
			//控制线程每隔一秒刷新一次时间，在Time_Jpanel上的taday_label,time_label上显示
			 Date date=new Date();		
			 SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy：MM:dd-EEEE");  
			 hour=new Date().getHours();
		     minute=new Date().getMinutes();
			 second=new Date().getSeconds();
			 moment="当前时间是："+hour+":"+minute+":"+second;
			 day="今天是"+bartDateFormat.format(date);
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