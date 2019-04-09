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
import javax.swing.JPanel;

import MM_Controller.MM_Controller;
import MM_Service.MM_Service;

public class MM_DonationSmall extends JFrame implements ActionListener {

   public JPanel p1, p2, p3, p4, p5, p6;
   public JButton bt_ok;
   public Image img;

   public MM_DonationSmall() {
      setTitle("사회복지기금");
      setBackground(Color.white);
      p1 = new JPanel();
      p2 = new JPanel();
      p3 = new JPanel();
      p4 = new JPanel();
      p5 = new JPanel();
      p6 = new JPanel();

      bt_ok = new JButton("입금");

      Label l1 = new Label("");
      Label l2 = new Label("");
      Label l3 = new Label("         ");
      Label l4 = new Label("         ");

      Label l5 = new Label("");
      Label l6 = new Label("");
      Label l7 = new Label("");
      Label l8 = new Label("");
      Label l9 = new Label("");
      Label l10 = new Label("");
      Label l11 = new Label("     사회복지기금 50만원을 내세요.");
      Label l12 = new Label("                             ");
      Label l13 = new Label("                             ");

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

      p4.add(l11, "Center");
      p4.add(l12, "North");
      p4.add(l13, "South");

      add(p1, "Center");
      add(p2, "South");
      setSize(300, 350);

      bt_ok.addActionListener(this);
      
      setVisible(true);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   }

   Canvas view = new Canvas() {
      public void paint(Graphics g) {
         g.drawImage(img, 0, 0, this);
         g.drawString("", 200, 200);
      }
   };

   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource()==bt_ok) {
    	 MM_Controller.deposit(MM_Service.currentPlayer);
         dispose();      
      }
   }

}