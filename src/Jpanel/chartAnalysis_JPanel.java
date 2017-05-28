package Jpanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



public class chartAnalysis_JPanel extends JPanel implements ActionListener {
	JLabel year_label = new JLabel("������Ҫ��������");

	JPanel chartpanel = new JPanel();

	JTextField year_textfield = new JTextField();
	JButton check_button = new JButton("��ѯ");
	JButton histogram = new JButton("����Ӫҵ�����");
	JButton piechart = new JButton("Ӫҵ�����ͳ��");
	JButton linechart = new JButton("Ӫҵ�����Ʒ���");

	String driver = "com.mysql.jdbc.Driver";// ������ // @jve:decl-index=0:
	String username = "xiaoyang";// ���ݿ��û��� // @jve:decl-index=0:
	String password = "xiaoyang";// ���ݿ����� // @jve:decl-index=0:
	String url = "jdbc:mysql://127.0.0.1:3306/wedding dress";// �������ݿ�ĵ�ַ
	Connection conn;

	String year;
	float retail[] = new float[12];
	float wholesale[] = new float[12];
	float order[] = new float[12];
	float all[] = new float[12];
	int retailmonth;
	int retailyear;
	int retailmoney;
	int wholesaleyear;
	int wholesalemonth;
	int wholesalemoney;
	int ordermonth;
	int orderyear;
	int ordermoney;

	public chartAnalysis_JPanel() {
		year_label.setBounds(20, 20, 120, 30);
		year_textfield.setBounds(150, 20, 60, 30);
		check_button.setBounds(230, 20, 60, 30);
		histogram.setBounds(60, 60, 200, 20);
		piechart.setBounds(260, 60, 200, 20);
		linechart.setBounds(460, 60, 200, 20);
		chartpanel.setBounds(0, 80, 800, 400);
		this.setLayout(null);
		this.setVisible(true);
		this.add(year_label);
		this.add(year_textfield);
		this.add(check_button);
		this.add(histogram);
		this.add(piechart);
		this.add(linechart);
		this.add(chartpanel);

		check_button.addActionListener(this);
		histogram.addActionListener(this);
		piechart.addActionListener(this);
		linechart.addActionListener(this);

	}
	/*
	 * ��ʾֱ��ͼ
	 */
	public void showhistogram() {

		setRetail();
		setWholesale();
		setOrder();
		for (int i = 0; i <= 11; i++) {
			all[i] = retail[i] + wholesale[i] + order[i];
			System.out.println("allmoney" + all[i]);
		}

		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		// ���ñ�������
		standardChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20));
		// ����ͼ��������
		standardChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15));
		// �������������
		standardChartTheme.setLargeFont(new Font("����", Font.PLAIN, 15));
		// Ӧ��������ʽ
		 ChartFactory.setChartTheme(standardChartTheme);

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(all[0], "Ӫҵ��", "һ��");
		dataset.setValue(all[1], "Ӫҵ��", "����");
		dataset.setValue(all[2], "Ӫҵ��", "����");
		dataset.setValue(all[3], "Ӫҵ��", "����");
		dataset.setValue(all[4], "Ӫҵ��", "����");
		dataset.setValue(all[5], "Ӫҵ��", "����");
		dataset.setValue(all[6], "Ӫҵ��", "����");
		dataset.setValue(all[7], "Ӫҵ��", "����");
		dataset.setValue(all[8], "Ӫҵ��", "����");
		dataset.setValue(all[9], "Ӫҵ��", "ʮ��");
		dataset.setValue(all[10], "Ӫҵ��", "ʮһ");
		dataset.setValue(all[11], "Ӫҵ��", "ʮ��");
		JFreeChart chart = ChartFactory.createBarChart("����Ӫҵ����ֱ��ͼ", "�·�",
				"Ӫҵ��", dataset, PlotOrientation.VERTICAL, false, true, false);
		ChartPanel chartpanel2 = new ChartPanel(chart);
		chartpanel2.setBackground(null);
		chartpanel2.setBounds(0, 0, 700, 400);
		chartpanel.add(chartpanel2);
		chartpanel.setVisible(true);
		chartpanel.setBackground(null);
		chartpanel.repaint();
	}
	/*
	 * ��ʾ����ͼ
	 */
	public void showpiechart() {
		setRetail();
		setWholesale();
		setOrder();
		float allcount = 0;
		for (int i = 0; i <= 11; i++) {
			all[i] = retail[i] + wholesale[i] + order[i];
			System.out.println("allmoney" + all[i]);
			float a = all[i];
			allcount = allcount + a;
		}
		float percent[] = new float[12];
		for (int i = 0; i <= 11; i++) {

			percent[i] = (all[i] / allcount) * 100;
			System.out.println("all[]" + all[i] + "   " + "account" + allcount);
			System.out.println("percent" + percent[i]);
		}
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		// ���ñ�������
		standardChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20));
		// ����ͼ��������
		standardChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15));
		// �������������
		standardChartTheme.setLargeFont(new Font("����", Font.PLAIN, 15));
		// Ӧ��������ʽ
		ChartFactory.setChartTheme(standardChartTheme);

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("һ��" + percent[0] + "%", all[0]);
		dataset.setValue("����" + percent[1] + "%", all[1]);
		dataset.setValue("����" + percent[2] + "%", all[2]);
		dataset.setValue("����" + percent[3] + "%", all[3]);
		dataset.setValue("����" + percent[4] + "%", all[4]);
		dataset.setValue("����" + percent[5] + "%", all[5]);
		dataset.setValue("����" + percent[6] + "%", all[6]);
		dataset.setValue("����" + percent[7] + "%", all[7]);
		dataset.setValue("����" + percent[8] + "%", all[8]);
		dataset.setValue("ʮ��" + percent[9] + "%", all[9]);
		dataset.setValue("ʮһ" + percent[10] + "%", all[10]);
		dataset.setValue("ʮ��" + percent[11] + "%", all[11]);
		JFreeChart chart = ChartFactory.createPieChart("Ӫҵ�����ͳ��", dataset,
				true, true, false);

		ChartPanel chartpanel2 = new ChartPanel(chart);
		chartpanel2.setBackground(null);
		chartpanel2.setBounds(0, 0, 700, 400);
		chartpanel.add(chartpanel2);
		chartpanel.setVisible(true);
		chartpanel.setBackground(null);
		chartpanel.repaint();

	}
	/*
	 * ��ʾ����ͼ
	 */
	public void showlinechart(){
		setRetail();
		setWholesale();
		setOrder();
		float allcount = 0;
		for (int i = 0; i <= 11; i++) {
			all[i] = retail[i] + wholesale[i] + order[i];
			System.out.println("allmoney" + all[i]);
			float a = all[i];
			allcount = allcount + a;
		}
		float percent[] = new float[12];
		for (int i = 0; i <= 11; i++) {

			percent[i] = (all[i] / allcount) * 100;
			System.out.println("all[]" + all[i] + "   " + "account" + allcount);
			System.out.println("percent" + percent[i]);
		}
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		// ���ñ�������
		standardChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20));
		// ����ͼ��������
		standardChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15));
		// �������������
		standardChartTheme.setLargeFont(new Font("����", Font.PLAIN, 15));
		// Ӧ��������ʽ
		ChartFactory.setChartTheme(standardChartTheme);
		
		XYSeries series = new XYSeries("Ӫҵ�������Ʒ���");
		for(int i=0;i<=11;i++){
			series.add(i+1,all[i]);
		}

		// Add the series to your data set
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		// Generate the graph
		JFreeChart chart = ChartFactory.createXYLineChart("Ӫҵ�������Ʒ���", // Title
				"�·�", // x-axis Label
				"Ӫҵ��", // y-axis Label
				dataset, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				true, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);
		ChartPanel chartpanel2 = new ChartPanel(chart);
		chartpanel2.setBackground(null);
		chartpanel2.setBounds(0, 0, 700, 400);
		chartpanel.add(chartpanel2);
		chartpanel.setVisible(true);
		chartpanel.setBackground(null);
		chartpanel.repaint();
	}
	/*
	 * ���۸��·ݵ����۶�
	 */
	public void setRetail() {
		String sql = "select retaildate,allprice from retail";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, username, password);
			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			Statement pstmt = conn.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
						Locale.CHINA);
				Date retaildate;
				try {
					retaildate = (Date) sdf.parseObject(rs.getString(1));
					retailmonth = retaildate.getMonth() + 1;
					retailyear = retaildate.getYear() + 1900;
					String retailyear1 = retailyear + "";
					if (retailyear1.equals(year)) {
						retailmoney = Integer.parseInt(rs.getString(2));
						System.out.println("retailmoney" + retailmoney);
						switch (retailmonth) {
						case 1:
							retail[0] = retail[0] + retailmoney;
							break;
						case 2:
							retail[1] = retail[1] + retailmoney;
							break;
						case 3:
							retail[2] = retail[2] + retailmoney;
							break;
						case 4:
							retail[3] = retail[3] + retailmoney;
							break;
						case 5:
							retail[4] = retail[4] + retailmoney;
							break;
						case 6:
							retail[5] = retail[5] + retailmoney;
							break;
						case 7:
							retail[6] = retail[6] + retailmoney;
							break;
						case 8:
							retail[7] = retail[7] + retailmoney;
							break;
						case 9:
							retail[8] = retail[8] + retailmoney;
							break;
						case 10:
							retail[9] = retail[9] + retailmoney;
							break;
						case 11:
							retail[10] = retail[10] + retailmoney;
							break;
						case 12:
							retail[11] = retail[11] + retailmoney;
							break;
						}
					}

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i <= 11; i++) {
			System.out.println(retail[i]);
		}
	}

	/*
	 * �������·ݵ����۶�
	 */
	public void setWholesale() {
		String sql = "select wholesaledate,allprice from wholesale";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, username, password);
			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			Statement pstmt = conn.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);
			while (rs.next()) {

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
						Locale.CHINA);

				Date wholesaledate;
				try {
					wholesaledate = (Date) sdf.parseObject(rs.getString(1));
					wholesalemonth = wholesaledate.getMonth() + 1;
					wholesaleyear = wholesaledate.getYear() + 1900;
					String wholesaleyear1 = wholesaleyear + "";
					if (wholesaleyear1.equals(year)) {
						wholesalemoney = Integer.parseInt(rs.getString(2));
						System.out.println("wholedalemoney" + wholesalemoney);
						switch (wholesalemonth) {
						case 1:
							wholesale[0] = wholesale[0] + wholesalemoney;
							break;
						case 2:
							wholesale[1] = wholesale[1] + wholesalemoney;
							break;
						case 3:
							wholesale[2] = wholesale[2] + wholesalemoney;
							break;
						case 4:
							wholesale[3] = wholesale[3] + wholesalemoney;
							break;
						case 5:
							wholesale[4] = wholesale[4] + wholesalemoney;
							break;
						case 6:
							wholesale[5] = wholesale[5] + wholesalemoney;
							break;
						case 7:
							wholesale[6] = wholesale[6] + wholesalemoney;
							break;
						case 8:
							wholesale[7] = wholesale[7] + wholesalemoney;
							break;
						case 9:
							wholesale[8] = wholesale[8] + wholesalemoney;
							break;
						case 10:
							wholesale[9] = wholesale[9] + wholesalemoney;
							break;
						case 11:
							wholesale[10] = wholesale[10] + wholesalemoney;
							break;
						case 12:
							wholesale[11] = wholesale[11] + wholesalemoney;
							break;
						}

					}

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i <= 11; i++) {
			System.out.println(wholesale[i]);
		}
	}

	/*
	 * �������·ݵ����۶�
	 */

	public void setOrder() {
		String sql = "select pickdate,orderallprice from order_wedding";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, username, password);
			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			Statement pstmt = conn.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);
			while (rs.next()) {

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
						Locale.CHINA);

				Date orderdate;
				try {
					orderdate = (Date) sdf.parseObject(rs.getString(1));
					ordermonth = orderdate.getMonth() + 1;
					orderyear = orderdate.getYear() + 1900;
					String orderyear1 = orderyear + "";
					if (orderyear1.equals(year)) {
						ordermoney = Integer.parseInt(rs.getString(2));
						System.out.println("ordermoney" + ordermoney);
						switch (ordermonth) {
						case 1:
							order[0] = order[0] + ordermoney;
							break;
						case 2:
							order[1] = order[1] + ordermoney;
							break;
						case 3:
							order[2] = order[2] + ordermoney;
							break;
						case 4:
							order[3] = order[3] + ordermoney;
							break;
						case 5:
							order[4] = order[4] + ordermoney;
							break;
						case 6:
							order[5] = order[5] + ordermoney;
							break;
						case 7:
							order[6] = order[6] + ordermoney;
							break;
						case 8:
							order[7] = order[7] + ordermoney;
							break;
						case 9:
							order[8] = order[8] + ordermoney;
							break;
						case 10:
							order[9] = order[9] + ordermoney;
							break;
						case 11:
							order[10] = order[10] + ordermoney;
							break;
						case 12:
							order[11] = order[11] + ordermoney;
							break;
						}
					}

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i <= 11; i++) {
			System.out.println(order[i]);
		}
	}

	/*
	 * ����ť�ļ���
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(check_button)) {
			chartpanel.removeAll();
			year = year_textfield.getText();
			showhistogram();

		} else if (e.getSource().equals(histogram)) {
			chartpanel.removeAll();
			year = year_textfield.getText();
			showhistogram();
		}

		else if (e.getSource().equals(piechart)) {
			chartpanel.removeAll();
			year = year_textfield.getText();
			showpiechart();

		} else if (e.getSource().equals(linechart)) {
			chartpanel.removeAll();
			year = year_textfield.getText();
			showlinechart();

		}
	}
}
