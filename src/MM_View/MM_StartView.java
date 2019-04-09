package MM_View;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MM_Controller.MM_Controller;
import MM_Service.MM_Service;

public class MM_StartView extends JFrame implements ActionListener {

	public JPanel p1, p2, p3;
	public JButton player1, player2, btnExit;
	public JLabel l1, l2, l3, l4, l5;
	public Image img;
	public Toolkit tool;
	public static MM_MainView mainView;
	
	public MM_StartView() {

		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel(); 

		l1 = new JLabel("");
		l2 = new JLabel("");
		l3 = new JLabel("");
		l4 = new JLabel("");
		l5 = new JLabel("");

		player1 = new JButton("1 Player");
		player2 = new JButton("2 Player");
		btnExit = new JButton("종료");
		tool = Toolkit.getDefaultToolkit();
		img = tool.getImage("C:/work/ModuMarble/img/login.png");

		setLayout(new BorderLayout());
		setTitle("부우마블");
		p1.setLayout(new BorderLayout());
		p2.setLayout(new BorderLayout());
		p3.setLayout(new GridLayout(1, 5));

		p1.add(view);

		p3.add(player1);
		p3.add(btnExit);
		p3.add(player2);

		add(p1, "Center");
		add(p2, "South");
		setSize(300, 200);

		p2.add(p3, "Center");
		p2.add(l2, "North");
		p2.add(l3, "South");
		p2.add(l4, "East");
		p2.add(l5, "West");

		add(p1, "Center");
		add(p2, "South");
		setSize(310, 350);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		player1.addActionListener(this);
		player2.addActionListener(this);
		btnExit.addActionListener(this);
	}

	Canvas view = new Canvas() {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, this);
			g.drawString("", 100, 200);
		}
	};

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == player1) {
			new MM_LoginView();
			dispose();
		} else if (obj == player2) {
			new MM_LoginView2();
			dispose();
		}else{
			MM_Controller.setPlayerInit();
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new MM_StartView();
	}
}