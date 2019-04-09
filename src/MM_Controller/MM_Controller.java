package MM_Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

import MM_Service.MM_Service;
import MM_Util.DBUtill;
import MM_View.MM_CityDialog;
import MM_View.MM_DonationBig;
import MM_View.MM_SpecialDialog;
import MM_View.MM_StartView;
import MM_View.MM_MainView;

public class MM_Controller {

	public static Connection con;
	public static PreparedStatement ps;
	public static ResultSet rs;

	/*
	 * DB에서 전체 도시 정보 받아오는 Method
	 */
	public static Object[] getCityDB(int cityNo) {
		Object[] cityDBInfo = new Object[16];
		try {
			cityDBInfo = MM_Service.getCityDB(cityNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityDBInfo;
	}

	/*
	 * 로그인 Method
	 */
	public static boolean isLogin(String... strs) {
		boolean result = false;
		try {
			result = MM_Service.isLogin(strs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 회원정보 받아오는 Method
	 */
	public static Object[] getPlayerInfo(String... strs) {
		Object[] playerInfo = new Object[8];
		try {
			playerInfo = MM_Service.getPlayerInfo(strs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return playerInfo;
	}

	/*
	 * 황금열쇠칸 도착 시 이벤트 Method
	 */
	public static void goldkeyEvent() {
		String[] goldkeyInfo = new String[8];
		try {
			MM_Service.goldkeyEvent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 주사위 클릭 시 이벤트 Method
	 */
	public static Object[] diceEvent() {
		Object[] result = new Object[4];
		try {
			result = MM_Service.diceEvent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 플레이어 턴 ON 설정 Method
	 */
	public static void setPlayerTurnOn(String currentPlayer) {
		try {
			MM_Service.setPlayerTurnOn(currentPlayer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 플레이어 턴 OFF 설정 Method
	 */
	public static void setPlayerTurnOff(String currentPlayer) {
		try {
			MM_Service.setPlayerTurnOff(currentPlayer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 도시 번호에 따른 좌표 값 받아오는 Method
	 */
	public static int[] getCityPoint(int cityNo) {
		int[] result = new int[2];
		try {
			result = MM_Service.getCityPoint(cityNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 도시 번호에 따른 도시 정보 받아오는 Method
	 */
	public static Object[] getCityInfo(int cityNo) {
		Object[] result = new Object[7];
		try {
			result = MM_Service.getCityInfo(cityNo);
		} catch (Exception e1) {		
			e1.printStackTrace();
		}
		return result;
	}

	/*
	 * 플레이어가 위치한 도시 업데이트 Method
	 */
	public static boolean updatePlayerPoint(int cityNo) {
		boolean result = false;
		try {
			result = MM_Service.updatePlayerPoint(cityNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 현재 플레이어 재설정
	 */
	public static String setCurrentPlayerTurn() {
		if (MM_Service.currentPlayer.equals("player1")) {
			return "zcomputer";
		} else {
			return "player1";
		}
	}

	/*
	 * 현재 플레이어가 위치한 도시번호 받아오는 Method
	 */
	public static int getPlayerLocation(String currentPlayer) {
		int result = 0;
		try {
			result = MM_Service.getPlayerLocation(currentPlayer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 황금열쇠 내용 수행 이벤트 method
	 */
	public static void goldkeyPlay(int goldkeyNo) {
		try {
			MM_Service.goldkeyEvent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * DB Player_Money Update Method
	 */
	public static void updatePlayerMoney(int money) {
		try {
			MM_Service.updatePlayerMoney(money);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * DB Player_Money Update Method2
	 */
	public static void updatePlayerMoney(int money, String player) {
		try {
			MM_Service.updatePlayerMoney(money, player);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 땅, 호텔, 랜드마크 여부 Method
	 */
	public static int[] checkBuild(int cityNo) {
		int[] result = new int[3];
		try {
			result = MM_Service.checkBuild(cityNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 땅, 호텔, 랜드마크 구매 Method
	 */
	public static boolean buyBuild(String btnText) {
		boolean result = false;
		try {
			result = MM_Service.buyBuild(btnText);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return result;
	}

	/*
	 * 땅, 호텔, 랜드마크 판매 Method
	 */
	public static boolean saleBuild(String btnText) {
		boolean result = false;
		try {
			result = MM_Service.saleBuild(btnText);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return result;
	}

	/*
	 * 통행료 지불 Method
	 */
	public static boolean payToll() {
		boolean result = false;
		try {
			result = MM_Service.payToll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 소유주 업데이트
	 */
	public static void updateCityOwner(int cityNo, String currentPlayer, int bl) {
		try {
			MM_Service.updateCityOwner(cityNo, currentPlayer, bl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 소유주 있는 도시만 받아오기.
	 */
	public static void getOwnerCity(String currentPlayer) {
		try {
			MM_Service.getOwnerCity(currentPlayer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 사회복지기금 입금 
	 */
	public static boolean deposit(String currentPlayer) {
		boolean result = false;
		try {
			result = MM_Service.deposit(currentPlayer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/*
	 * 사회복지기금 출금
	 */
	public static void withdraw(String currentPlayer) {
		try {
			MM_Service.withdraw(currentPlayer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 프로그램 종료 시 회원 정보 설정
	 */
	public static void setPlayerInit() {
		try {
			MM_Service.setPlayerInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
