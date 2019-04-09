package MM_DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import MM_Service.MM_Service;
import MM_Util.DBUtill;
import MM_View.MM_CityDialog;
import MM_View.MM_SpecialDialog;
import MM_View.MM_StartView;
import MM_View.MM_MainView;

public interface MM_DAO {

	/*
	 * DB에서 전체 도시 정보 받아오는 Method  
	 */
	public Object[] getCityDB(int cityNo) throws Exception;
	
	/*
	 * 로그인 Method
	 */
	public boolean isLogin(String...strs) throws Exception;
		
	/*
	 * 회원정보 받아오는 Method
	 */
	public Object[] getPlayerInfo(String...strs) throws Exception;
	
	/*
	 * 황금열쇠칸 도착 시 이벤트 Method
	 */
	public void goldkeyEvent() throws Exception;
	
	/*
	 * 플레이어 턴 ON 설정 Method 
	 */
	public void setPlayerTurnOn(String currentPlayer) throws Exception;
	
	/*
	 * 플레이어 턴 OFF 설정 Method 
	 */
	public void setPlayerTurnOff(String currentPlayer) throws Exception;

	/*
	 * 플레이어가 위치한 도시 업데이트 Method
	 */
	public boolean updatePlayerPoint(int cityNo) throws Exception;
	
	/*
	 * 현재 플레이어가 위치한 도시번호 받아오는 Method
	 */
	public int getPlayerLocation(String currentPlayer) throws Exception;
	
	/*
	 * 황금열쇠 내용 수행 이벤트 method
	 */
	public void goldkeyPlay(int goldkeyNo) throws Exception;
	
	/*
	 * 플레이어 돈 Update Method1
	 */
	public void updatePlayerMoney(int money) throws Exception;
	
	/*
	 * 플레이어 돈 Update Method2
	 */
	public void updatePlayerMoney(int money, String player) throws Exception;
	
	/*
	 * 소유주 업데이트  
	 */
	public void updateCityOwner(int cityNo, String currentPlayer, int bl) throws Exception;
	
	/*
	 * 소유주 있는 도시만 받아오기. 
	 */
	public void getOwnerCity(String currentPlayer) throws Exception;
	
	/*
	 * 프로그램 종료 시 회원 정보 설정
	 */
	public void setPlayerInit() throws Exception;
}
