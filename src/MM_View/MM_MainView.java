package MM_View;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import MM_Controller.MM_Controller;
import MM_Service.MM_Service;

public class MM_MainView extends Canvas implements ActionListener, MouseListener {

	File file;
	FileReader fr;
	BufferedReader br;
	String[] clientInfo, clientsInfo;

	Object[] resultDice;

	JFrame frame;
	String title[] = { "도시", "땅", "호텔", "랜드마크" };
	public static DefaultTableModel cli1_Model, cli2_Model;
	JTable cli1_Table, cli2_Table;
	Image imgBackground;
	Image imgPlayer1, imgPlayer2, imgHotel, imgLandmark;
	Toolkit toolkit;
	JPanel panel_Client1, panel_Client2, panel_Back, panel_Seperate;
	public static JLabel cli1_ID, cli1_Win, cli1_Percent, cli1_Money;
	public static JLabel cli2_ID, cli2_Win, cli2_Percent, cli2_Money;
	int playerX = 338;
	int playerY = 681;
	int playerX2 = 408;
	int playerY2 = 681;
	int state = 0;
	int state2 = 0;

	Image img_buffer;
	Graphics buffer;

	MM_MainView() {
		clientInfo = new String[500];
		clientsInfo = new String[500];

		frame = new JFrame("Modu Marble");

		panel_Client1 = new JPanel();
		panel_Client2 = new JPanel();
		panel_Back = new JPanel();
		panel_Seperate = new JPanel();
		cli1_ID = new JLabel(" 님     ");
		cli2_ID = new JLabel(" 님     ");
		cli1_Win = new JLabel("승률 : ");
		cli2_Win = new JLabel("승률 : ");
		cli1_Percent = new JLabel("");
		cli2_Percent = new JLabel("");
		cli1_Money = new JLabel("남은 돈 : ");
		cli2_Money = new JLabel("남은 돈 : ");
		cli1_Model = new DefaultTableModel(title, 0);
		cli2_Model = new DefaultTableModel(title, 0);
		cli1_Table = new JTable(cli1_Model);
		cli2_Table = new JTable(cli2_Model);

		toolkit = Toolkit.getDefaultToolkit();
		imgBackground = toolkit.getImage("C:/work/ModuMarble/img/imgBackground.png");
		imgPlayer1 = toolkit.getImage("C:/work/ModuMarble/img/duke.jpg");
		imgPlayer2 = toolkit.getImage("C:/work/ModuMarble/img/duke2.jpg");
		imgHotel = toolkit.getImage("C:/work/ModuMarble/img/hotel.png");
		imgLandmark = toolkit.getImage("C:/work/ModuMarble/img/landmark.png");

		panel_Back.setBackground(Color.black);
		panel_Client1.setBackground(Color.pink);
		panel_Client2.setBackground(Color.orange);
		panel_Seperate.setBackground(Color.GRAY);

		this.setBounds(0, 0, 870, 900);
		panel_Client1.setBounds(870, 0, 430, 435);
		panel_Client2.setBounds(870, 450, 440, 450);
		panel_Seperate.setBounds(870, 430, 440, 5);
		cli1_ID.setBounds(900, 30, 10, 10);
		cli1_Win.setBounds(1000, 300, 10, 10);

		panel_Client1.add(cli1_ID);
		panel_Client1.add(cli1_Win);
		panel_Client1.add(cli1_Percent);
		panel_Client1.add(cli1_Money);

		panel_Client1.add("Center", new JScrollPane(cli1_Table));
		cli1_Table.disable();

		panel_Client2.add(cli2_ID);
		panel_Client2.add(cli2_Win);
		panel_Client2.add(cli2_Percent);
		panel_Client2.add(cli2_Money);
		panel_Client2.add("Center", new JScrollPane(cli2_Table));
		cli2_Table.disable();

		frame.add(this);
		frame.add(panel_Client1);
		frame.add(panel_Back);
		frame.add(panel_Client2);
		frame.add(panel_Seperate);
		frame.setSize(1300, 900);
		frame.setVisible(true);
		frame.setResizable(false);

		frame.setLocation(250, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(this);

	}

	public void go(int diceSum) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (state != diceSum) {
					state++;
					if (state == 40) {
						state = 0;
					}
					int[] coordinateXY = MM_Controller.getCityPoint(state);
					playerX = coordinateXY[0];
					playerY = coordinateXY[1];
					repaint();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}

	public void back(int diceSum) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (state != diceSum) {
					state--;
					if (state == -1) {
						state = 39;
					}
					int[] coordinateXY = MM_Controller.getCityPoint(state);
					playerX = coordinateXY[0];
					playerY = coordinateXY[1];
					repaint();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}

	public void go2(int diceSum) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (state2 != diceSum) {
					state2++;
					if (state2 == 40) {
						state2 = 0;
					}
					int[] coordinateXY = MM_Controller.getCityPoint(state2);
					playerX2 = coordinateXY[0];
					playerY2 = coordinateXY[1];
					repaint();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}

	public void back2(int diceSum) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (state2 != diceSum) {
					state2--;
					if (state2 == -1) {
						state2 = 39;
					}
					int[] coordinateXY = MM_Controller.getCityPoint(state2);
					playerX2 = coordinateXY[0];
					playerY2 = coordinateXY[1];
					repaint();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}

	public void paint(Graphics g) {
		img_buffer = createImage(this.getWidth(), this.getHeight());
		buffer = img_buffer.getGraphics();
		buffer.drawImage(imgPlayer1, playerX, playerY, this);
		buffer.drawImage(imgPlayer2, playerX2, playerY2, this);
		g.drawImage(imgBackground, 10, 10, this);
		g.drawImage(imgPlayer1, playerX, playerY, this);
		g.drawImage(imgPlayer2, playerX2, playerY2, this);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int moveSpacePoint = 0;
		if (MM_Service.currentPlayer.equals("player1")) { // 플레이어 1일 때
			if (MM_Service.checkMoveSpase) {
				if (e.getX() >= 765 && e.getX() <= 855 && e.getY() >= 765 && e.getY() <= 865) {
					moveSpacePoint = 0;
				} else if (e.getX() >= 685 && e.getX() <= 740 && e.getY() >= 780 && e.getY() <= 865) {
					moveSpacePoint = 1;
				} else if (e.getX() >= 620 && e.getX() <= 670 && e.getY() >= 765 && e.getY() <= 865) {
					moveSpacePoint = 2;
				} else if (e.getX() >= 545 && e.getX() <= 600 && e.getY() >= 780 && e.getY() <= 865) {
					moveSpacePoint = 3;
				} else if (e.getX() >= 480 && e.getX() <= 530 && e.getY() >= 780 && e.getY() <= 865) {
					moveSpacePoint = 4;
				} else if (e.getX() >= 405 && e.getX() <= 465 && e.getY() >= 780 && e.getY() <= 865) {
					moveSpacePoint = 5;
				} else if (e.getX() >= 335 && e.getX() <= 395 && e.getY() >= 780 && e.getY() <= 865) {
					moveSpacePoint = 6;
				} else if (e.getX() >= 275 && e.getX() <= 325 && e.getY() >= 765 && e.getY() <= 865) {
					moveSpacePoint = 7;
				} else if (e.getX() >= 200 && e.getX() <= 255 && e.getY() >= 780 && e.getY() <= 865) {
					moveSpacePoint = 8;
				} else if (e.getX() >= 135 && e.getX() <= 185 && e.getY() >= 780 && e.getY() <= 865) {
					moveSpacePoint= 9;
				} else if (e.getX() >= 20 && e.getX() <= 115 && e.getY() >= 755 && e.getY() <= 870) {
					moveSpacePoint = 10;
				} else if (e.getX() >= 15 && e.getX() <= 80 && e.getY() >= 680 && e.getY() <= 740) {
					moveSpacePoint = 11;
				} else if (e.getX() >= 15 && e.getX() <= 105 && e.getY() >= 625 && e.getY() <= 675) {
					moveSpacePoint = 12;
				} else if (e.getX() >= 15 && e.getX() <= 80 && e.getY() >= 545 && e.getY() <= 605) {
					moveSpacePoint = 13;
				} else if (e.getX() >= 15 && e.getX() <= 80 && e.getY() >= 475 && e.getY() <= 530) {
					moveSpacePoint = 14;
				} else if (e.getX() >= 15 && e.getX() <= 80 && e.getY() >= 405 && e.getY() <= 465) {
					moveSpacePoint = 15;
				} else if (e.getX() >= 15 && e.getX() <= 80 && e.getY() >= 340 && e.getY() <= 390) {
					moveSpacePoint = 16;
				} else if (e.getX() >= 15 && e.getX() <= 105 && e.getY() >= 270 && e.getY() <= 325) {
					moveSpacePoint = 17;
				} else if (e.getX() >= 15 && e.getX() <= 80 && e.getY() >= 205 && e.getY() <= 255) {
					moveSpacePoint = 18;
				} else if (e.getX() >= 15 && e.getX() <= 80 && e.getY() >= 135 && e.getY() <= 185) {
					moveSpacePoint = 19;
				} else if (e.getX() >= 15 && e.getX() <= 115 && e.getY() >= 15 && e.getY() <= 115) {
					moveSpacePoint = 20;
				} else if (e.getX() >= 135 && e.getX() <= 185 && e.getY() >= 20 && e.getY() <= 90) {
					moveSpacePoint = 21;
				} else if (e.getX() >= 200 && e.getX() <= 255 && e.getY() >= 15 && e.getY() <= 115) {
					moveSpacePoint = 22;
				} else if (e.getX() >= 280 && e.getX() <= 315 && e.getY() >= 20 && e.getY() <= 90) {
					moveSpacePoint = 23;
				} else if (e.getX() >= 335 && e.getX() <= 385 && e.getY() >= 20 && e.getY() <= 90) {
					moveSpacePoint = 24;
				} else if (e.getX() >= 405 && e.getX() <= 460 && e.getY() >= 20 && e.getY() <= 90) {
					moveSpacePoint = 25;
				} else if (e.getX() >= 480 && e.getX() <= 525 && e.getY() >= 20 && e.getY() <= 90) {
					moveSpacePoint = 26;
				} else if (e.getX() >= 545 && e.getX() <= 600 && e.getY() >= 20 && e.getY() <= 90) {
					moveSpacePoint = 27;
				} else if (e.getX() >= 615 && e.getX() <= 675 && e.getY() >= 20 && e.getY() <= 90) {
					moveSpacePoint = 28;
				} else if (e.getX() >= 685 && e.getX() <= 740 && e.getY() >= 20 && e.getY() <= 90) {
					moveSpacePoint = 29;
				} else if (e.getX() >= 755 && e.getX() <= 850 && e.getY() >= 20 && e.getY() <= 115) {
					moveSpacePoint = 30;
				} else if (e.getX() >= 785 && e.getX() <= 850 && e.getY() >= 135 && e.getY() <= 180) {
					moveSpacePoint = 31;
				} else if (e.getX() >= 785 && e.getX() <= 850 && e.getY() >= 205 && e.getY() <= 250) {
					moveSpacePoint = 32;
				} else if (e.getX() >= 785 && e.getX() <= 850 && e.getY() >= 270 && e.getY() <= 320) {
					moveSpacePoint = 33;
				} else if (e.getX() >= 785 && e.getX() <= 850 && e.getY() >= 340 && e.getY() <= 390) {
					moveSpacePoint = 34;
				} else if (e.getX() >= 760 && e.getX() <= 855 && e.getY() >= 410 && e.getY() <= 465) {
					moveSpacePoint = 35;
				} else if (e.getX() >= 785 && e.getX() <= 850 && e.getY() >= 475 && e.getY() <= 530) {
					moveSpacePoint = 36;
				} else if (e.getX() >= 785 && e.getX() <= 850 && e.getY() >= 545 && e.getY() <= 600) {
					moveSpacePoint = 37;
				} else if (e.getX() >= 785 && e.getX() <= 850 && e.getY() >= 615 && e.getY() <= 675) {
					moveSpacePoint = 38;
				} else if (e.getX() >= 785 && e.getX() <= 850 && e.getY() >= 680 && e.getY() <= 740) {
					moveSpacePoint = 39;
				}
				go2(moveSpacePoint);
				MM_Controller.getCityInfo(moveSpacePoint);
				MM_Controller.updatePlayerPoint(moveSpacePoint);
				MM_Service.checkMoveSpase = false;
			}else if (e.getX() >= 595 && e.getX() <= 713 && e.getY() >= 583 && e.getY() <= 703) {
				Object[] objResultDice = MM_Controller.diceEvent();
				if ((boolean) objResultDice[3]) {
					JOptionPane.showMessageDialog(this, "주사위 눈 : " + objResultDice[0] + ", " + objResultDice[1]
							+ "\n주사위 합 : " + objResultDice[2] + "\n더블 추카추카뿌!");
				} else {
					JOptionPane.showMessageDialog(this,
							"주사위 눈 : " + objResultDice[0] + ", " + objResultDice[1] + "\n주사위 합 : " + objResultDice[2]);
				}
				int dest = ((int) objResultDice[2]) + state;
				if (dest >= 40)
					dest -= 40;
				MM_Controller.updatePlayerPoint(dest);
				go(dest);
				MM_Controller.getCityInfo(dest);
				if (MM_Service.currentPlayer.equals("player1")) {
					MM_Service.currentPlayer = "zcomputer";
					MM_Controller.setPlayerTurnOn(MM_Service.currentPlayer);
					MM_Controller.setPlayerTurnOff("player1");
				} else if (MM_Service.currentPlayer.equals("zcomputer")) {
					MM_Service.currentPlayer = "player1";
					MM_Controller.setPlayerTurnOn(MM_Service.currentPlayer);
					MM_Controller.setPlayerTurnOff("zcomputer");
				}
			} else if (e.getX() >= 139 && e.getX() <= 202 && e.getY() >= 686 && e.getY() <= 700) {
				int select = JOptionPane.showConfirmDialog(null, "재경기 하시겠습니까?", "부우마블", 2);
				if (select == 0) {
					MM_Controller.setPlayerInit();
				}
			} else if (e.getX() >= 139 && e.getX() <= 202 && e.getY() >= 721 && e.getY() <= 738) {
				int select = JOptionPane.showConfirmDialog(null, "게임을 종료 하시겠습니까?", "부우마블", 2);
				if (select == 0) {
					MM_Controller.setPlayerInit();
					new MM_StartView();
					frame.dispose();
				}
			}
		} else { // computer 일 때
			if (MM_Service.checkMoveSpase) {
				
				if (e.getX() >= 765 && e.getX() <= 855 && e.getY() >= 765 && e.getY() <= 865) {
					moveSpacePoint = 0;
				} else if (e.getX() >= 685 && e.getX() <= 740 && e.getY() >= 780 && e.getY() <= 865) {
					moveSpacePoint = 1;
				} else if (e.getX() >= 620 && e.getX() <= 670 && e.getY() >= 765 && e.getY() <= 865) {
					moveSpacePoint = 2;
				} else if (e.getX() >= 545 && e.getX() <= 600 && e.getY() >= 780 && e.getY() <= 865) {
					moveSpacePoint = 3;
				} else if (e.getX() >= 480 && e.getX() <= 530 && e.getY() >= 780 && e.getY() <= 865) {
					moveSpacePoint = 4;
				} else if (e.getX() >= 405 && e.getX() <= 465 && e.getY() >= 780 && e.getY() <= 865) {
					moveSpacePoint = 5;
				} else if (e.getX() >= 335 && e.getX() <= 395 && e.getY() >= 780 && e.getY() <= 865) {
					moveSpacePoint = 6;
				} else if (e.getX() >= 275 && e.getX() <= 325 && e.getY() >= 765 && e.getY() <= 865) {
					moveSpacePoint = 7;
				} else if (e.getX() >= 200 && e.getX() <= 255 && e.getY() >= 780 && e.getY() <= 865) {
					moveSpacePoint = 8;
				} else if (e.getX() >= 135 && e.getX() <= 185 && e.getY() >= 780 && e.getY() <= 865) {
					moveSpacePoint = 9;
				} else if (e.getX() >= 20 && e.getX() <= 115 && e.getY() >= 755 && e.getY() <= 870) {
					moveSpacePoint = 10;
				} else if (e.getX() >= 15 && e.getX() <= 80 && e.getY() >= 680 && e.getY() <= 740) {
					moveSpacePoint = 11;
				} else if (e.getX() >= 15 && e.getX() <= 105 && e.getY() >= 625 && e.getY() <= 675) {
					moveSpacePoint = 12;
				} else if (e.getX() >= 15 && e.getX() <= 80 && e.getY() >= 545 && e.getY() <= 605) {
					moveSpacePoint = 13;
				} else if (e.getX() >= 15 && e.getX() <= 80 && e.getY() >= 475 && e.getY() <= 530) {
					moveSpacePoint = 14;
				} else if (e.getX() >= 15 && e.getX() <= 80 && e.getY() >= 405 && e.getY() <= 465) {
					moveSpacePoint = 15;
				} else if (e.getX() >= 15 && e.getX() <= 80 && e.getY() >= 340 && e.getY() <= 390) {
					moveSpacePoint = 16;
				} else if (e.getX() >= 15 && e.getX() <= 105 && e.getY() >= 270 && e.getY() <= 325) {
					moveSpacePoint = 17;
				} else if (e.getX() >= 15 && e.getX() <= 80 && e.getY() >= 205 && e.getY() <= 255) {
					moveSpacePoint = 18;
				} else if (e.getX() >= 15 && e.getX() <= 80 && e.getY() >= 135 && e.getY() <= 185) {
					moveSpacePoint = 19;
				} else if (e.getX() >= 15 && e.getX() <= 115 && e.getY() >= 15 && e.getY() <= 115) {
					moveSpacePoint = 20;
				} else if (e.getX() >= 135 && e.getX() <= 185 && e.getY() >= 20 && e.getY() <= 90) {
					moveSpacePoint = 21;
				} else if (e.getX() >= 200 && e.getX() <= 255 && e.getY() >= 15 && e.getY() <= 115) {
					moveSpacePoint = 22;
				} else if (e.getX() >= 280 && e.getX() <= 315 && e.getY() >= 20 && e.getY() <= 90) {
					moveSpacePoint = 23;
				} else if (e.getX() >= 335 && e.getX() <= 385 && e.getY() >= 20 && e.getY() <= 90) {
					moveSpacePoint = 24;
				} else if (e.getX() >= 405 && e.getX() <= 460 && e.getY() >= 20 && e.getY() <= 90) {
					moveSpacePoint = 25;
				} else if (e.getX() >= 480 && e.getX() <= 525 && e.getY() >= 20 && e.getY() <= 90) {
					moveSpacePoint = 26;
				} else if (e.getX() >= 545 && e.getX() <= 600 && e.getY() >= 20 && e.getY() <= 90) {
					moveSpacePoint = 27;
				} else if (e.getX() >= 615 && e.getX() <= 675 && e.getY() >= 20 && e.getY() <= 90) {
					moveSpacePoint = 28;
				} else if (e.getX() >= 685 && e.getX() <= 740 && e.getY() >= 20 && e.getY() <= 90) {
					moveSpacePoint = 29;
				} else if (e.getX() >= 755 && e.getX() <= 850 && e.getY() >= 20 && e.getY() <= 115) {
					moveSpacePoint = 30;
				} else if (e.getX() >= 785 && e.getX() <= 850 && e.getY() >= 135 && e.getY() <= 180) {
					moveSpacePoint = 31;
				} else if (e.getX() >= 785 && e.getX() <= 850 && e.getY() >= 205 && e.getY() <= 250) {
					moveSpacePoint = 32;
				} else if (e.getX() >= 785 && e.getX() <= 850 && e.getY() >= 270 && e.getY() <= 320) {
					moveSpacePoint = 33;
				} else if (e.getX() >= 785 && e.getX() <= 850 && e.getY() >= 340 && e.getY() <= 390) {
					moveSpacePoint = 34;
				} else if (e.getX() >= 760 && e.getX() <= 855 && e.getY() >= 410 && e.getY() <= 465) {
					moveSpacePoint = 35;
				} else if (e.getX() >= 785 && e.getX() <= 850 && e.getY() >= 475 && e.getY() <= 530) {
					moveSpacePoint = 36;
				} else if (e.getX() >= 785 && e.getX() <= 850 && e.getY() >= 545 && e.getY() <= 600) {
					moveSpacePoint = 37;
				} else if (e.getX() >= 785 && e.getX() <= 850 && e.getY() >= 615 && e.getY() <= 675) {
					moveSpacePoint = 38;
				} else if (e.getX() >= 785 && e.getX() <= 850 && e.getY() >= 680 && e.getY() <= 740) {
					moveSpacePoint = 39;
				}
				go(moveSpacePoint);
				MM_Controller.getCityInfo(moveSpacePoint);
				MM_Controller.updatePlayerPoint(moveSpacePoint);
				MM_Service.checkMoveSpase = false;
			}else if (e.getX() >= 595 && e.getX() <= 713 && e.getY() >= 583 && e.getY() <= 703) {
				Object[] objResultDice = MM_Controller.diceEvent();
				if ((boolean) objResultDice[3]) {
					JOptionPane.showMessageDialog(this, "주사위 눈 : " + objResultDice[0] + ", " + objResultDice[1]
							+ "\n주사위 합 : " + objResultDice[2] + "\n더블 추카추카뿌!");
				} else {
					JOptionPane.showMessageDialog(this,
							"주사위 눈 : " + objResultDice[0] + ", " + objResultDice[1] + "\n주사위 합 : " + objResultDice[2]);
				}
				int dest = ((int) objResultDice[2]) + state2;
				if (dest >= 40)
					dest -= 40;
				MM_Controller.updatePlayerPoint(dest);
				go2(dest);
				MM_Controller.getCityInfo(dest);
				if (MM_Service.currentPlayer.equals("player1")) {
					MM_Service.currentPlayer = "zcomputer";
					MM_Controller.setPlayerTurnOn(MM_Service.currentPlayer);
					MM_Controller.setPlayerTurnOff("player1");
				} else if (MM_Service.currentPlayer.equals("zcomputer")) {
					MM_Service.currentPlayer = "player1";
					MM_Controller.setPlayerTurnOn(MM_Service.currentPlayer);
					MM_Controller.setPlayerTurnOff("zcomputer");
				}
			} else if (e.getX() >= 139 && e.getX() <= 202 && e.getY() >= 686 && e.getY() <= 700) {
				int select = JOptionPane.showConfirmDialog(null, "재경기 하시겠습니까?", "부우마블", 2);
				if (select == 0) {
					MM_Controller.setPlayerInit();
				}
			} else if (e.getX() >= 139 && e.getX() <= 202 && e.getY() >= 721 && e.getY() <= 738) {
				int select = JOptionPane.showConfirmDialog(null, "게임을 종료 하시겠습니까?", "부우마블", 2);
				if (select == 0) {
					MM_Controller.setPlayerInit();
					new MM_StartView();
					frame.dispose();
				}
			}
		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}