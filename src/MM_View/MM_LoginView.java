package MM_View;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MM_Controller.MM_Controller;

public class MM_LoginView extends JFrame implements ActionListener, FocusListener {

	public JButton b1, b2;
	public JTextField player1, pw1;
	public Image img;
	public JPanel p1, p2, p3, p4;
	public Toolkit tool;
	public JLabel l1, l2;
	public JFrame jf;
	
	public MM_LoginView() {

		jf = new JFrame();
		p1 = new JPanel(); // ID,PW
		p2 = new JPanel(); // 버튼판넬
		p3 = new JPanel(); // ID,PW배경판넬
		p4 = new JPanel(); // 그림판넬

		player1 = new JTextField("Player1", 100);
		pw1 = new JTextField("password", 100);

		Toolkit tool = Toolkit.getDefaultToolkit();
		img = tool.getImage("C:/work/ModuMarble/img/login.png");

		l1 = new JLabel("                    ");//
		l2 = new JLabel("                    ");//
		b1 = new JButton("게 임 시 작");
		b2 = new JButton(" 취소 ");

		jf.setLayout(new BorderLayout());
		p1.setLayout(new GridLayout(3, 3));
		p2.setLayout(new BorderLayout());
		p3.setLayout(new BorderLayout());
		p4.setLayout(new BorderLayout());

		jf.add(p2, "South");
		jf.add(p3, "Center");
		jf.setSize(200, 200);

		ArrayList<JLabel> la_arr = new ArrayList<JLabel>();
		for (int i = 0; i < 15; i++) {
			la_arr.add(new JLabel(""));
		}

		p1.add(la_arr.get(0));
		p1.add(player1);
		p1.add(la_arr.get(1));
		p1.add(la_arr.get(2));
		p1.add(la_arr.get(3));
		p1.add(la_arr.get(4));
		p1.add(la_arr.get(5));
		p1.add(pw1);
		p1.add(la_arr.get(6));

		jf.add(p2, "South");
		jf.add(p3, "Center");
		jf.setSize(300, 400);

		p4.add(view, "Center");

		p2.add(b1, "West");
		p2.add(b2, "East");
		p2.add(la_arr.get(7), "North");
		p2.add(la_arr.get(8), "South");
		p3.add(p1, "South");

		p3.add(la_arr.get(9), "North");
		p3.add(la_arr.get(10), "West");
		p3.add(p4, "Center");
		p3.add(la_arr.get(11), "East");

		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		player1.addFocusListener(this);
		pw1.addFocusListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
	}

	Canvas view = new Canvas() {
		public void paint(Graphics g) {
			// g.drawRect(x, y, width, height);
			g.drawImage(img, 0, 0, this);
			g.drawString("", 220, 200);
		}
	};

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b1){
			if(MM_Controller.isLogin(player1.getText(), pw1.getText())){
				MM_StartView.mainView = new MM_MainView();
				MM_Controller.getPlayerInfo("player1", "zcomputer");
				MM_Controller.setPlayerInit();
				jf.dispose();
				
			}
		}else if(e.getSource() == b2){
			jf.dispose();
			new MM_StartView();
		}
	}

	public void focusGained(FocusEvent e) {
		if (e.getSource() == player1) {
			player1.setText(null);
		}
		if (e.getSource() == pw1) {
			pw1.setText(null);
		}
	}

	public void focusLost(FocusEvent e) {
		if (e.getSource() == player1) {
			if (player1.getText().isEmpty()) {
				player1.setText("player1");
			}
		}

		if (e.getSource() == pw1) {
			if (pw1.getText().isEmpty()) {
				pw1.setText("****");
			}
		}
	}
}