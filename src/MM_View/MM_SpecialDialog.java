package MM_View;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MM_Controller.MM_Controller;
import MM_Service.MM_Service;

public class MM_SpecialDialog extends JFrame implements ActionListener {
	JPanel p1, p2, p3;
	public static JButton btnBuy, btnSale, btnOk, btnToll;
	JLabel lbCityName, lbCityGPrice, lbCityOwner, lbCityToll, lbImg, lb1, lb2, lb3, lb4, lb5, lb6, lb7;
	JLabel tfCityName, tfCityGPrice, tfCityOwner, tfCityToll;
	ImageIcon imageIcon;

	public MM_SpecialDialog(String cityName, int cityGPrice, String cityOwner, int cityToll, String imgPath) {
		setTitle("부우마블");

		p1 = new JPanel(new GridLayout(2, 1, 10, 5));
		p2 = new JPanel(new GridLayout(6, 2, 10, 5));
		p3 = new JPanel(new GridLayout(2, 2));
		btnBuy = new JButton("구입");
		btnSale = new JButton("팔기");
		btnOk = new JButton("확인");
		btnToll = new JButton("통행료지불");
		lbCityName = new JLabel(" 도시명 : ");
		lbCityGPrice = new JLabel(" 땅 가격 : ");
		lbCityOwner = new JLabel(" 소유주 : ");
		lbCityToll = new JLabel(" 통행료 : ");
		lb1 = new JLabel("            ");
		lb2 = new JLabel("            ");
		lb3 = new JLabel("            ");
		lb4 = new JLabel("   ");
		lb5 = new JLabel("   ");
		lb6 = new JLabel("   ");
		lb7 = new JLabel("   ");
		lbImg = new JLabel();
		imageIcon = new ImageIcon(imgPath);
		tfCityName = new JLabel(cityName);
		tfCityGPrice = new JLabel(Integer.toString(cityGPrice));
		tfCityOwner = new JLabel(cityOwner);
		tfCityToll = new JLabel(Integer.toString(cityToll));

		switch (cityName) {

		case "제주도":
			lbImg.setIcon(imageIcon);
			break;

		case "콩코드여객기":
			lbImg.setIcon(imageIcon);
			break;

		case "부산":
			lbImg.setIcon(imageIcon);
			break;

		case "퀸엘리자베스":
			lbImg.setIcon(imageIcon);
			break;

		case "아둔의창":
			lbImg.setIcon(imageIcon);
			break;

		case "서울":
			lbImg.setIcon(imageIcon);
			break;
		}

		p2.add(lbCityName);
		p2.add(tfCityName);
		p2.add(lbCityGPrice);
		p2.add(tfCityGPrice);
		p2.add(lbCityOwner);
		p2.add(tfCityOwner);
		p2.add(lbCityToll);
		p2.add(tfCityToll);
		p3.add(btnBuy);
		p3.add(btnSale);

		p3.add(btnToll);

		p3.add(btnOk);

		p1.add(lbImg);
		p1.add(p2);
		add(lb1, "North");
		add(lb2, "East");
		add(lb3, "West");
		add(p1, "Center");
		add(p3, "South");

		btnBuy.addActionListener(this);
		btnSale.addActionListener(this);
		btnOk.addActionListener(this);
		btnToll.addActionListener(this);
		setVisible(true);
		setSize(300, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuy) {
			MM_Controller.buyBuild(btnBuy.getText());
			dispose();
		} else if (e.getSource() == btnSale) {
			MM_Controller.saleBuild(btnSale.getText());
			dispose();
		} else if (e.getSource() == btnOk) {
			dispose();
		}else if(e.getSource()==btnToll){
			if(MM_Controller.payToll()){
				dispose();
			}else{
				JOptionPane.showMessageDialog(null, "돈이 부족합니다");
				JOptionPane.showMessageDialog(null, "파산 : 게임 종료");
				System.exit(0);
			}
		}

	}

}