package Jpanel;

import java.io.*;

import javax.swing.JFileChooser;

import jxl.*;
import jxl.write.*;

public class CreateXLS {
	public static void main(String args[]) {
		try {
			 int result;
			 JFileChooser chooser;
			    chooser = new JFileChooser();
			    chooser.setCurrentDirectory(new java.io.File("."));
			    chooser.setDialogTitle("a");
			    System.out.println("f");
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false);
			   
			   
			// ���ļ�
			WritableWorkbook book = Workbook
					.createWorkbook(new File("test.xls"));
			// ������Ϊ����һҳ���Ĺ���������0��ʾ���ǵ�һҳ
			WritableSheet sheet = book.createSheet("��һҳ", 0);
			// ��Label����Ĺ�������ָ����Ԫ��λ���ǵ�һ�е�һ��(0,0)
			// �Լ���Ԫ������Ϊtest
			Label label = new Label(0, 0, "test");
			// ������õĵ�Ԫ����ӵ���������
			sheet.addCell(label);
			/*
			 * ����һ���������ֵĵ�Ԫ�� ����ʹ��Number��������·�����������﷨���� ��Ԫ��λ���ǵڶ��У���һ�У�ֵΪ789.123
			 */
			jxl.write.Number number = new jxl.write.Number(1, 0, 789.123);
			sheet.addCell(number);
			// д�����ݲ��ر��ļ�
			book.write();
			book.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}