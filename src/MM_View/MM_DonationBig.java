package MM_View;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MM_Controller.MM_Controller;
import MM_Service.MM_Service;

public class MM_DonationBig extends JFrame implements ActionListener {

	public JPanel p1, p2, p3, p4, p5, p6;
	public JButton bt_ok;
	public Image img;
	public static JLabel l9;
	public static int donateMoney = 0;

	public MM_DonationBig() {
		setTitle("사회복지기금");
		setBackground(Color.white);
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();
		p6 = new JPanel();

		bt_ok = new JButton("받기");
		bt_ok.addActionListener(this);

		Label l1 = new Label("");
		Label l2 = new Label("");
		Label l3 = new Label("");
		Label l4 = new Label("");

		Label l5 = new Label("");
		Label l6 = new Label("");
		Label l7 = new Label("");
		Label l8 = new Label("");
		l9 = new JLabel("누적금액 : " + donateMoney + " 원");
		Label l10 = new Label("");
		JLabel l11 = new JLabel("사회복지기금을 받아가세요.");

		setLayout(new BorderLayout());
		p1.setLayout(new BorderLayout());
		p2.setLayout(new BorderLayout());
		p3.setLayout(new BorderLayout());
		p4.setLayout(new BorderLayout());
		p5.setLayout(new FlowLayout());
		p6.setLayout(new FlowLayout());

		Toolkit tool = Toolkit.getDefaultToolkit();
		img = tool.getImage("C:/work/ModuMarble/img/donation.jpg");

		p1.add(view, "Center");

		p2.add(bt_ok, "Center");
		p2.add(l1, "South");
		p2.add(p3, "North");
		p2.add(l3, "East");
		p2.add(l4, "West");

		p3.add(p4, "Center");
		p3.add(l5, "North");
		p3.add(l6, "South");
		p3.add(l7, "West");
		p3.add(l8, "East");

		p4.add(p5, "Center");
		p4.add(p6, "South");

		p5.add(l9);
		p5.add(l10);

		p6.add(l11);

		add(p1, "Center");
		add(p2, "South");
		setSize(300, 400);

		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}

	Canvas view = new Canvas() {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, this);
			g.drawString("", 200, 200);
		}
	};

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt_ok){
			MM_Controller.withdraw(MM_Service.currentPlayer);
			dispose();
		}
	}
}