package Jpanel;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

	public class MyChooser extends JFileChooser {
	MyChooser(String path) {
		super(path);
	}

	public void approveSelection() {
		File file = this.getSelectedFile();
		if (file.exists()) {
			int copy = JOptionPane.showConfirmDialog(null, "�Ƿ�Ҫ���ǵ�ǰ�ļ���", "����",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (copy == JOptionPane.YES_OPTION)
				super.approveSelection();
		} else
			super.approveSelection();
	}
}