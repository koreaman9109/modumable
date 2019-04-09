package MM_Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

import MM_Controller.MM_Controller;
import MM_DAO.MM_DAO;
import MM_DAO.MM_DAOImpl;
import MM_Util.DBUtill;
import MM_View.MM_CityDialog;
import MM_View.MM_DonationBig;
import MM_View.MM_DonationSmall;
import MM_View.MM_SpecialDialog;
import MM_View.MM_StartView;
import MM_View.MM_MainView;

public class MM_Service {
	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;
	public static String currentPlayer = "zcomputer";
	public static MM_CityDialog cityDialogInstance;
	public static MM_SpecialDialog specialDialogInstance;
	private static MM_DAO dao = new MM_DAOImpl();
	public static boolean checkMoveSpase = false;

	/*
	 * DB에서 전체 도시 정보 받아오는 Method
	 */
	public static Object[] getCityDB(int cityNo) throws Exception {
		Object[] cityDBInfo = dao.getCityDB(cityNo);
		return cityDBInfo;
	}

	/*
	 * 로그인 Method
	 */
	public static boolean isLogin(String... strs) throws Exception {
		return dao.isLogin(strs);
	}

	/*
	 * 회원정보 받아오는 Method
	 */
	public static Object[] getPlayerInfo(String... strs) throws Exception {
		Object[] playerInfo = dao.getPlayerInfo(strs);
		return playerInfo;
	}

	/*
	 * 황금열쇠칸 도착 시 이벤트 Method
	 */
	public static void goldkeyEvent() throws Exception {
		dao.goldkeyEvent();
	}

	/*
	 * 주사위 클릭 시 이벤트 Method
	 */
	public static Object[] diceEvent() throws Exception {
		int currentLocation = getPlayerLocation(currentPlayer);
		Object[] resultDice = new Object[4];
		resultDice[0] = 0;
		resultDice[1] = 0;
		resultDice[2] = 0;
		resultDice[3] = false;

		int sum = 0; // 두 주사위 합
		while (true) {
			int no1 = (int) (Math.random() * 6) + 1;
			int no2 = (int) (Math.random() * 6) + 1;
			if(sum!=10){
				sum = (no1 + no2);				
			}else{
				no1 = (int) (Math.random() * 6) + 1;
				no2 = (int) (Math.random() * 6) + 1;
				sum = (no1 + no2);	
			}
			resultDice[0] = no1;
			resultDice[1] = no2;
			resultDice[2] = sum;
			if (no1 == no2) {
				resultDice[3] = true;
			} else {
				resultDice[3] = false;
			}

			return resultDice;
		}
	}

	/*
	 * 플레이어 턴 ON 설정 Method
	 */
	public static void setPlayerTurnOn(String currentPlayer) throws Exception {
		dao.setPlayerTurnOn(currentPlayer);
	}

	/*
	 * 플레이어 턴 OFF 설정 Method
	 */
	public static void setPlayerTurnOff(String currentPlayer) throws Exception {
		dao.setPlayerTurnOff(currentPlayer);
	}

	/*
	 * 도시 번호에 따른 좌표 값 받아오는 Method
	 */
	public static int[] getCityPoint(int cityNo) throws Exception {
		int[] cityPoint = new int[2];
		Object[] obj = getCityDB(cityNo);
		cityPoint[0] = (int) obj[5];
		cityPoint[1] = (int) obj[6];
		return cityPoint;
	}

	/*
	 * 도시 번호에 따른 도시 정보 받아오는 Method
	 */
	public static Object[] getCityInfo(int cityNo) throws Exception {
		Object[] cityDialog = new Object[7];
		cityDialog[0] = "null";
		cityDialog[1] = 0;
		cityDialog[2] = 0;
		cityDialog[3] = 0;
		cityDialog[4] = "null";
		cityDialog[5] = 0;
		cityDialog[6] = 0;

		// 황금열쇠
		if (cityNo == 2 || cityNo == 7 || cityNo == 12 || cityNo == 17 || cityNo == 22 || cityNo == 35) {
			MM_Service.goldkeyEvent();

			// 특수도시
		} else if (cityNo == 5 || cityNo == 15 || cityNo == 25 || cityNo == 28 || cityNo == 32 || cityNo == 39) {
			Object[] obj = getCityDB(cityNo);
			cityDialog[0] = obj[1];
			cityDialog[1] = obj[2];
			cityDialog[2] = obj[14];
			cityDialog[3] = (int) ((int) obj[2] * 0.3);
			cityDialog[4] = obj[15];

			int[] check = checkBuild(cityNo);
			if (check[0] == 0) {
				specialDialogInstance = new MM_SpecialDialog((String) cityDialog[0], (int) cityDialog[1],
						(String) cityDialog[2], (int) cityDialog[3], (String) cityDialog[4]);
				specialDialogInstance.btnBuy.setText("땅매입");
				specialDialogInstance.btnSale.setText("판매");
				specialDialogInstance.btnBuy.setEnabled(true);
				specialDialogInstance.btnSale.setEnabled(false);
				specialDialogInstance.btnToll.setEnabled(false);
				specialDialogInstance.btnOk.setEnabled(true);
			} else if (check[0] == 1) {
				specialDialogInstance = new MM_SpecialDialog((String) cityDialog[0], (int) cityDialog[1],
						(String) cityDialog[2], (int) cityDialog[3], (String) cityDialog[4]);
				if (cityDialog[4].equals(MM_Service.setCurrentPlayerTurn())) {
					specialDialogInstance.btnBuy.setText("구매");
					specialDialogInstance.btnSale.setText("땅판매");
					specialDialogInstance.btnSale.setEnabled(true);
					specialDialogInstance.btnBuy.setEnabled(false);
					specialDialogInstance.btnToll.setEnabled(false);
					specialDialogInstance.btnOk.setEnabled(true);
				} else {
					specialDialogInstance.btnSale.setEnabled(false);
					specialDialogInstance.btnBuy.setEnabled(false);
					specialDialogInstance.btnToll.setEnabled(true);
					specialDialogInstance.btnOk.setEnabled(false);

				}
			}

			// 출발점
		} else if (cityNo == 0) {
			JOptionPane.showMessageDialog(null, "세계일주 성공 : 10만원 지급");
			Object[] result = dao.getPlayerInfo(currentPlayer);
			MM_Controller.updatePlayerMoney((int)result[1]+100000);

			// 무인도
		} else if (cityNo == 10) {
			JOptionPane.showMessageDialog(null, "무인도 ㅠㅠㅠㅠㅠㅠㅠ");

			// 사회복지금 받기
		} else if (cityNo == 20) {
			new MM_DonationBig();

			// 우주정거장
		} else if (cityNo == 30) {
			System.out.println("우주정거장");
			MM_Service.checkMoveSpase = true;
			JOptionPane.showMessageDialog(null, "우주여행 : 이동 할 나라를 클릭하세요.");
			// 사회복지금내기
		} else if (cityNo == 38) {
			new MM_DonationSmall();

			// 일반도시
		} else {
			Object[] obj = getCityDB(cityNo);
			cityDialog[0] = obj[1];
			cityDialog[1] = obj[2];
			cityDialog[2] = obj[3];
			cityDialog[3] = obj[4];
			cityDialog[4] = obj[14];
			cityDialog[5] = ((int) obj[2] * 0.3);
			cityDialog[6] = obj[15];

			if ((int) obj[11] == 1) {
				cityDialog[5] = (int) ((int) cityDialog[1] * 0.3);
			} else if ((int) obj[12] == 1) {
				cityDialog[5] = (int) ((int) cityDialog[2] * 0.3);
			} else if ((int) obj[13] == 0) {
				cityDialog[5] = (int) ((int) cityDialog[3] * 0.3);
			} else {
				cityDialog[5] = 0;
			}

			int[] check = checkBuild(cityNo);

			if (check[0] == 0) {
				cityDialogInstance = new MM_CityDialog((String) cityDialog[0], (int) cityDialog[1], (int) cityDialog[2],
						(int) cityDialog[3], (String) cityDialog[4], (int) cityDialog[5], (String) cityDialog[6]);
				cityDialogInstance.btnBuy.setText("땅매입");
				cityDialogInstance.btnSale.setText("판매");
				cityDialogInstance.btnBuy.setEnabled(true);
				cityDialogInstance.btnSale.setEnabled(false);
				cityDialogInstance.btnToll.setEnabled(false);
				cityDialogInstance.btnOk.setEnabled(true);
			} else if (check[0] == 1) {
				cityDialogInstance = new MM_CityDialog((String) cityDialog[0], (int) cityDialog[1], (int) cityDialog[2],
						(int) cityDialog[3], (String) cityDialog[4], (int) cityDialog[5], (String) cityDialog[6]);
				if (cityDialog[6].equals(MM_Service.setCurrentPlayerTurn())) {
					cityDialogInstance.btnBuy.setText("호텔건설");
					cityDialogInstance.btnSale.setText("땅판매");
					cityDialogInstance.btnBuy.setEnabled(true);
					cityDialogInstance.btnSale.setEnabled(true);
					cityDialogInstance.btnToll.setEnabled(false);
					cityDialogInstance.btnOk.setEnabled(true);

				} else {
					cityDialogInstance.btnBuy.setEnabled(false);
					cityDialogInstance.btnSale.setEnabled(false);
					cityDialogInstance.btnToll.setEnabled(true);
					cityDialogInstance.btnOk.setEnabled(false);

				}

			} else if (check[1] == 1) {
				cityDialogInstance = new MM_CityDialog((String) cityDialog[0], (int) cityDialog[1], (int) cityDialog[2],
						(int) cityDialog[3], (String) cityDialog[4], (int) cityDialog[5], (String) cityDialog[6]);
				if (cityDialog[6].equals(MM_Service.setCurrentPlayerTurn())) {
					cityDialogInstance.btnBuy.setText("랜드마크건설");
					cityDialogInstance.btnSale.setText("호텔판매");
					cityDialogInstance.btnBuy.setEnabled(true);
					cityDialogInstance.btnSale.setEnabled(true);
					cityDialogInstance.btnToll.setEnabled(false);
					cityDialogInstance.btnOk.setEnabled(true);

				} else {
					cityDialogInstance.btnBuy.setEnabled(false);
					cityDialogInstance.btnSale.setEnabled(false);
					cityDialogInstance.btnToll.setEnabled(true);
					cityDialogInstance.btnOk.setEnabled(false);

				}
			} else {
				cityDialogInstance = new MM_CityDialog((String) cityDialog[0], (int) cityDialog[1], (int) cityDialog[2],
						(int) cityDialog[3], (String) cityDialog[4], (int) cityDialog[5], (String) cityDialog[6]);
				if (cityDialog[6].equals(MM_Service.setCurrentPlayerTurn())) {
					cityDialogInstance.btnBuy.setText("구매");
					cityDialogInstance.btnSale.setText("랜드마크판매");
					cityDialogInstance.btnBuy.setEnabled(false);
					cityDialogInstance.btnSale.setEnabled(true);
					cityDialogInstance.btnToll.setEnabled(false);
					cityDialogInstance.btnOk.setEnabled(true);

				} else {
					cityDialogInstance.btnBuy.setEnabled(false);
					cityDialogInstance.btnSale.setEnabled(false);
					cityDialogInstance.btnToll.setEnabled(true);
					cityDialogInstance.btnOk.setEnabled(false);

				}
			}
		}
		return cityDialog;
	}

	/*
	 * 플레이어가 위치한 도시 업데이트 Method
	 */
	public static boolean updatePlayerPoint(int cityNo) throws Exception {
		return dao.updatePlayerPoint(cityNo);
	}

	/*
	 * 현재 플레이어 재설정
	 */
	public static String setCurrentPlayerTurn() throws Exception {
		if (MM_Service.currentPlayer.equals("player1")) {
			return "player1";
		} else {
			return "zcomputer";
		}
	}

	/*
	 * 현재 플레이어가 위치한 도시번호 받아오는 Method
	 */
	public static int getPlayerLocation(String currentPlayer) throws Exception {
		return dao.getPlayerLocation(currentPlayer);
	}

	/*
	 * 황금열쇠 내용 수행 이벤트 method
	 */
	public static void goldkeyPlay(int goldkeyNo) throws Exception {
		dao.goldkeyPlay(goldkeyNo);
	}

	/*
	 * DB Player_Money Update Method
	 */
	public static void updatePlayerMoney(int money) throws Exception {
		dao.updatePlayerMoney(money);
	}
	
	/*
	 * DB Player_Money Update Method2
	 */
	public static void updatePlayerMoney(int money, String player) throws Exception {
		dao.updatePlayerMoney(money, player);
	}

	/*
	 * 땅, 호텔, 랜드마크 여부 Method
	 */
	public static int[] checkBuild(int cityNo) throws Exception {
		int[] check = new int[3];
		Object[] obj = getCityDB(cityNo);
		check[0] = (int) obj[11];
		check[1] = (int) obj[12];
		check[2] = (int) obj[13];
		return check;
	}

	/*
	 * 땅, 호텔, 랜드마크 구매 Method
	 */
	public static boolean buyBuild(String btnText) throws Exception {
		String tmpPlayer = MM_Service.setCurrentPlayerTurn();
		int cityNo = getPlayerLocation(tmpPlayer);
		int[] price = new int[3];
		Object[] tmp = getPlayerInfo("null");
		int playerMoney = (int) tmp[1];
		String cityName = null;
		Object[] obj = getCityDB(cityNo);
		price[0] = (int) obj[2];
		price[1] = (int) obj[3];
		price[2] = (int) obj[4];
		cityName = (String) obj[1];

		if (btnText.equals("땅매입")) {
			if (playerMoney >= price[0]) {
				JOptionPane.showMessageDialog(null, "구매했습니다");
				if (tmpPlayer.equals("player1")) {
					MM_MainView.cli1_Money.setText(Integer.toString((playerMoney - price[0])));
				} else {
					MM_MainView.cli2_Money.setText(Integer.toString((playerMoney - price[0])));
				}
				updatePlayerMoney((playerMoney - price[0]));

				if (tmpPlayer.equals("player1")) {
					for (int i = MM_StartView.mainView.cli1_Model.getRowCount() - 1; i >= 0; i--) {
						MM_StartView.mainView.cli1_Model.removeRow(i);
					}
				} else {
					for (int i = MM_StartView.mainView.cli2_Model.getRowCount() - 1; i >= 0; i--) {
						MM_StartView.mainView.cli2_Model.removeRow(i);
					}
				}

				obj[12] = 1;
				updateCityOwner(cityNo, tmpPlayer, 1);
				getOwnerCity(tmpPlayer);
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "돈 부족");
			}
		} else if (btnText.equals("호텔건설")) {
			if (playerMoney >= price[1]) {
				JOptionPane.showMessageDialog(null, "구매했습니다");
				if (tmpPlayer.equals("player1")) {
					MM_MainView.cli1_Money.setText(Integer.toString((playerMoney - price[1])));
				} else {
					MM_MainView.cli2_Money.setText(Integer.toString((playerMoney - price[1])));
				}
				updatePlayerMoney((playerMoney - price[1]));
				if (tmpPlayer.equals("player1")) {
					for (int i = MM_StartView.mainView.cli1_Model.getRowCount() - 1; i >= 0; i--) {
						MM_StartView.mainView.cli1_Model.removeRow(i);
					}
				} else {
					for (int i = MM_StartView.mainView.cli2_Model.getRowCount() - 1; i >= 0; i--) {
						MM_StartView.mainView.cli2_Model.removeRow(i);
					}
				}
				obj[13] = 1;
				updateCityOwner(cityNo, tmpPlayer, 2);
				getOwnerCity(tmpPlayer);
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "돈 부족");
			}
		} else if (btnText.equals("랜드마크건설")) {
			if (playerMoney >= price[2]) {
				JOptionPane.showMessageDialog(null, "구매했습니다");
				if (tmpPlayer.equals("player1")) {
					MM_MainView.cli1_Money.setText(Integer.toString((playerMoney - price[2])));
				} else {
					MM_MainView.cli2_Money.setText(Integer.toString((playerMoney - price[2])));
				}
				updatePlayerMoney((playerMoney - price[2]));
				if (tmpPlayer.equals("player1")) {
					for (int i = MM_StartView.mainView.cli1_Model.getRowCount() - 1; i >= 0; i--) {
						MM_StartView.mainView.cli1_Model.removeRow(i);
					}
				} else {
					for (int i = MM_StartView.mainView.cli2_Model.getRowCount() - 1; i >= 0; i--) {
						MM_StartView.mainView.cli2_Model.removeRow(i);
					}
				}

				obj[14] = 1;
				updateCityOwner(cityNo, tmpPlayer, 3);
				getOwnerCity(tmpPlayer);
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "돈 부족");
			}
		}
		return false;
	}

	/*
	 * 땅, 호텔, 랜드마크 판매 Method
	 */
	public static boolean saleBuild(String btnText) throws Exception {
		String tmpPlayer = MM_Service.setCurrentPlayerTurn();
		int cityNo = getPlayerLocation(tmpPlayer);
		int[] price = new int[3];
		Object[] tmp = getPlayerInfo("null");
		int playerMoney = (int) tmp[1];
		String cityName = null;
		Object[] obj = getCityDB(cityNo);
		price[0] = (int) obj[2];
		price[1] = (int) obj[3];
		price[2] = (int) obj[4];
		cityName = (String) obj[1];

		if (btnText.equals("땅판매")) {
			if (playerMoney >= price[0]) {
				JOptionPane.showMessageDialog(null, "판매했습니다");
				if (tmpPlayer.equals("player1")) {
					MM_MainView.cli1_Money.setText(Integer.toString((playerMoney + (int) (price[0] * 0.5))));
				} else {
					MM_MainView.cli2_Money.setText(Integer.toString((playerMoney + (int) (price[0] * 0.5))));
				}
				updatePlayerMoney((playerMoney + (int) (price[0] * 0.5)));
				if (tmpPlayer.equals("player1")) {
					for (int i = MM_StartView.mainView.cli1_Model.getRowCount() - 1; i >= 0; i--) {
						MM_StartView.mainView.cli1_Model.removeRow(i);
					}
				} else {
					for (int i = MM_StartView.mainView.cli2_Model.getRowCount() - 1; i >= 0; i--) {
						MM_StartView.mainView.cli2_Model.removeRow(i);
					}
				}

				obj[12] = 0;
				updateCityOwner(cityNo, "소유주없음", 4);
				getOwnerCity(tmpPlayer);
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "돈 부족");
			}
		} else if (btnText.equals("호텔판매")) {
			if (playerMoney >= price[1]) {
				JOptionPane.showMessageDialog(null, "구매했습니다");
				if (tmpPlayer.equals("player1")) {
					MM_MainView.cli1_Money.setText(Integer.toString((playerMoney + (int) (price[1] * 0.5))));
				} else {
					MM_MainView.cli2_Money.setText(Integer.toString((playerMoney + (int) (price[1] * 0.5))));
				}
				updatePlayerMoney((playerMoney + (int) (price[1] * 0.5)));
				if (tmpPlayer.equals("player1")) {
					for (int i = MM_StartView.mainView.cli1_Model.getRowCount() - 1; i >= 0; i--) {
						MM_StartView.mainView.cli1_Model.removeRow(i);
					}
				} else {

					for (int i = MM_StartView.mainView.cli2_Model.getRowCount() - 1; i >= 0; i--) {
						MM_StartView.mainView.cli2_Model.removeRow(i);
					}
				}

				obj[13] = 0;
				updateCityOwner(cityNo, "소유주없음", 5);
				getOwnerCity(tmpPlayer);
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "돈 부족");
			}
		} else if (btnText.equals("랜드마크판매")) {
			if (playerMoney >= price[2]) {
				JOptionPane.showMessageDialog(null, "구매했습니다");
				if (tmpPlayer.equals("player1")) {
					MM_MainView.cli1_Money.setText(Integer.toString((playerMoney + (int) (price[2] * 0.5))));
				} else {
					MM_MainView.cli2_Money.setText(Integer.toString((playerMoney + (int) (price[2] * 0.5))));
				}
				updatePlayerMoney((playerMoney + (int) (price[2] * 0.5)));
				if (tmpPlayer.equals("player1")) {
					for (int i = MM_StartView.mainView.cli1_Model.getRowCount() - 1; i >= 0; i--) {
						MM_StartView.mainView.cli1_Model.removeRow(i);
					}
				} else {
					for (int i = MM_StartView.mainView.cli2_Model.getRowCount() - 1; i >= 0; i--) {
						MM_StartView.mainView.cli2_Model.removeRow(i);
					}
				}

				obj[14] = 0;
				updateCityOwner(cityNo, "소유주없음", 6);
				getOwnerCity(tmpPlayer);
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "돈 부족");
			}
		}
		return false;
	}

	/*
	 * 통행료 지불 Method
	 */
	public static boolean payToll() throws Exception {
		int tmpCityNo = getPlayerLocation(setCurrentPlayerTurn());
		Object tmpobj[] = getCityDB(tmpCityNo);
		int tmpToll = (int) ((int) tmpobj[2] * 0.3);
		if (MM_Controller.setCurrentPlayerTurn().equals("player1")) {
			Object[] tmpobj2 = getPlayerInfo("player1");
			if (tmpToll <= (int) tmpobj2[1]) {
				MM_Controller.updatePlayerMoney(((int) tmpobj2[1] - tmpToll));
				Object[] tmpobj3 = getPlayerInfo("zcomputer");
				MM_Controller.updatePlayerMoney((int)tmpobj3[1]+tmpToll, "zcomputer");
				return true;
			}
		} else {
			Object[] tmpobj2 = getPlayerInfo("zcomputer");
			if (tmpToll <= (int) tmpobj2[1]) {
				MM_Controller.updatePlayerMoney(((int) tmpobj2[1] - tmpToll));
				Object[] tmpobj3 = getPlayerInfo("player1");
				MM_Controller.updatePlayerMoney((int)tmpobj3[1]+tmpToll, "player1");
				return true;
			}
		}
		return false;
	}

	/*
	 * 소유주 업데이트
	 */
	public static void updateCityOwner(int cityNo, String currentPlayer, int bl) throws Exception {
		dao.updateCityOwner(cityNo, currentPlayer, bl);
	}

	/*
	 * 소유주 있는 도시만 받아오기.
	 */
	public static void getOwnerCity(String currentPlayer) throws Exception {
		dao.getOwnerCity(currentPlayer);
	}
	
	/*
	 * 사회복지기금 입금 
	 */
	public static boolean deposit(String currentPlayer) throws Exception{
		Object[] result = dao.getPlayerInfo(currentPlayer);
		if((int)result[1] >= 500000){
			MM_DonationBig.donateMoney += 500000;
			MM_DonationBig.l9.setText("누적금액 : " + MM_DonationBig.donateMoney + " 원");
			return true;
		}
		return false;
	}
	
	/*
	 * 사회복지기금 출금
	 */
	public static void withdraw(String currentPlayer) throws Exception{
		Object[] result = dao.getPlayerInfo(currentPlayer);
		MM_Controller.updatePlayerMoney((int)result[1]+MM_DonationBig.donateMoney);
	}
	
	/*
	 * 프로그램 종료 시 회원 정보 설정
	 */
	public static void setPlayerInit() throws Exception {
		dao.setPlayerInit();
	}
}