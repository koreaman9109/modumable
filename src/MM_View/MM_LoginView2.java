package MM_View;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import MM_Controller.MM_Controller;
import MM_Service.MM_Service;

public class MM_LoginView2 extends JFrame implements ActionListener, FocusListener {

	public JPanel p1, p2, p3, p4;
	public JButton b1, b2;
	public JTextField player1, player2, pw1, pw2;
	public Image img;
	public JLabel l1, l2;
	public boolean result = false;
	public String conid = "";

	public MM_LoginView2() {

		p1 = new JPanel(); // ID,PW
		p2 = new JPanel(); // 버튼판넬
		p3 = new JPanel(); // ID,PW배경판넬
		p4 = new JPanel(); // 그림판넬

		player1 = new JTextField("Player1", 100);
		pw1 = new JTextField("password", 100);
		player2 = new JTextField("Player2", 100);
		pw2 = new JTextField("password", 100);

		Toolkit tool = Toolkit.getDefaultToolkit();
		img = tool.getImage("C:/work/ModuMarble/img/login.png");

		l1 = new JLabel("                    ");//
		l2 = new JLabel("                    ");//

		b1 = new JButton("게 임 시 작");
		b2 = new JButton(" 취소 ");

		setLayout(new BorderLayout());
		p1.setLayout(new GridLayout(3, 5));
		p2.setLayout(new BorderLayout());
		p3.setLayout(new BorderLayout());
		p4.setLayout(new BorderLayout());

		add(p2, "South");
		add(p3, "Center");
		setSize(200, 200);

		// 빈라벨 공간용으로 리스트화
		ArrayList<Label> la_arr = new ArrayList<Label>();
		for (int i = 0; i < 20; i++) {
			la_arr.add(new Label(""));
		}

		p1.add(la_arr.get(0));
		p1.add(player1);
		p1.add(la_arr.get(1));
		p1.add(player2);
		p1.add(la_arr.get(2));
		p1.add(la_arr.get(3));
		p1.add(la_arr.get(4));
		p1.add(la_arr.get(5));
		p1.add(la_arr.get(6));
		p1.add(la_arr.get(7));
		p1.add(la_arr.get(8));
		p1.add(pw1);
		p1.add(la_arr.get(9));
		p1.add(pw2);
		p1.add(la_arr.get(10));
		add(p2, "South");
		add(p3, "Center");
		add(la_arr.get(11), "West");
		add(la_arr.get(12), "East");
		setSize(350, 450);

		p4.add(view, "Center");
		// 아래 버튼 패널
		p2.add(b1, "West");
		p2.add(b2, "East");
		p2.add(la_arr.get(13), "North");
		p2.add(la_arr.get(14), "South");
		p3.add(p1, "South");
		p3.add(la_arr.get(15), "North");
		p3.add(la_arr.get(16), "West");
		p3.add(p4, "Center");
		p3.add(la_arr.get(17), "East");

		player1.addFocusListener(this);
		pw1.addFocusListener(this);
		player2.addFocusListener(this);
		pw2.addFocusListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	Canvas view = new Canvas() {
		public void paint(Graphics g) {
			// g.drawRect(x, y, width, height);
			g.drawImage(img, 0, 0, this);
			g.drawString("", 220, 200);
		}
	};

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b1){
			if(MM_Controller.isLogin(player1.getText(), pw1.getText(), player2.getText(), pw2.getText())){
				MM_StartView.mainView = new MM_MainView();
				MM_Controller.getPlayerInfo("player1", "player2");
				dispose();
			}
		}else{
			dispose();
			new MM_StartView();
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == player1) {
			player1.setText(null);
		}
		if (e.getSource() == player2) {
			player2.setText(null);
		}
		if (e.getSource() == pw1) {
			pw1.setText(null);
		}
		if (e.getSource() == pw2) {
			pw2.setText(null);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == player1) {
			if (player1.getText().isEmpty()) {
				player1.setText("player1");
			}
		}
		if (e.getSource() == player2) {
			if (player2.getText().isEmpty()) {
				player2.setText("player2");
			}
		}
		if (e.getSource() == pw1) {
			if (pw1.getText().isEmpty()) {
				pw1.setText("****");
			}
		}
		if (e.getSource() == pw2) {
			if (pw2.getText().isEmpty()) {
				pw2.setText("****");
			}
		}
	}
}