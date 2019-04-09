package MM_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import MM_Controller.MM_Controller;
import MM_Service.MM_Service;
import MM_Util.DBUtill;
import MM_View.MM_StartView;
import MM_View.MM_MainView;

public class MM_DAOImpl implements MM_DAO {

	public Connection con;
	public PreparedStatement ps;
	public ResultSet rs;
	
	@Override
	public Object[] getCityDB(int cityNo) throws Exception {
		Object[] cityDBInfo = new Object[16];
		con = DBUtill.getConnection();
		ps = null;
		rs = null;
		try{
			ps = con.prepareStatement("select * from city where city_No = ?");
			ps.setInt(1, cityNo);
			rs = ps.executeQuery();
			if(rs.next()){
				for(int i=0; i<cityDBInfo.length; i++){
					if(i==1 || i==14 ){
						cityDBInfo[i] = rs.getString(i+1);
					}else if(i==15){
						cityDBInfo[i] = rs.getString(i+1)+".jpg";
					}else{
						cityDBInfo[i] = rs.getInt(i+1);
					}
				}
			}
		}finally{
			DBUtill.dbClose(con, ps, rs);
		}
		return cityDBInfo;
	}

	@Override
	public boolean isLogin(String... strs) throws Exception {
		int playerSu = strs.length;
		String[] playerInfo = new String[playerSu]; 
		con = DBUtill.getConnection();
		ps = null;
		rs = null;
		for(int i=0; i<strs.length; i++){
			playerInfo[i] = strs[i];
		}
		try{
			if(playerSu == 2){
				ps = con.prepareStatement("select * from player where player_id = ?");
				ps.setString(1, "player1");
				rs = ps.executeQuery();
				if(rs.next()){			
					if(playerInfo[0].equals(rs.getString(1)) && playerInfo[1].equals(rs.getString(2)) ){
						return true;
					}
				}
			}else{
				ps = con.prepareStatement("select player_id, player_pw from player");
				rs = ps.executeQuery();
				while(rs.next()){
					if(playerInfo[0].equals(rs.getString(1)) && playerInfo[1].equals(rs.getString(2))){
						rs.last();
						if(playerInfo[2].equals(rs.getString(1)) && playerInfo[3].equals(rs.getString(2))){
							return true;
						}	
					}
				}						
			}
		}finally{
			DBUtill.dbClose(con, ps, rs);
		}
		return false;
	}

	@Override
	public Object[] getPlayerInfo(String... strs) throws Exception {
		Object[] playerInfo = new Object[8];
		int playerSu = strs.length;
		String[] ids = new String[playerSu];
		for(int i = 0; i<strs.length; i++){
			ids[i] = strs[i];
		}
		con = DBUtill.getConnection();
		ps = null;
		rs = null;
		if(playerSu==1){
			if(ids[0].equals("null")){				
				try{
					ps = con.prepareStatement("select player_id, player_money, player_win, player_lose from player where player_turn = ?");
					ps.setInt(1, 1);
					rs = ps.executeQuery();
					while(rs.next()){
						playerInfo[0] = rs.getString(1);
						playerInfo[1] = rs.getInt(2);
						playerInfo[2] = rs.getInt(3);
						playerInfo[3] = rs.getInt(4);
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally {
					DBUtill.dbClose(con, ps, rs);
				}
			}else{
				try{
					ps = con.prepareStatement("select player_id, player_money, player_win, player_lose from player where player_id = ?");
					ps.setString(1, ids[0]);
					rs = ps.executeQuery();
					while(rs.next()){
						playerInfo[0] = rs.getString(1);
						playerInfo[1] = rs.getInt(2);
						playerInfo[2] = rs.getInt(3);
						playerInfo[3] = rs.getInt(4);
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally {
					DBUtill.dbClose(con, ps, rs);
				}
			}
		}else{
			try{
				ps = con.prepareStatement("select player_id, player_money, player_win, player_lose from player where player_id = ? or player_id = ?");
				ps.setString(1, ids[0]);
				ps.setString(2, ids[1]);
				rs = ps.executeQuery();
				while(rs.next()){
					playerInfo[0] = rs.getString(1);
					playerInfo[1] = rs.getInt(2);
					playerInfo[2] = rs.getInt(3);
					playerInfo[3] = rs.getInt(4);
					rs.last();
					playerInfo[4] = rs.getString(1);
					playerInfo[5] = rs.getInt(2);
					playerInfo[6] = rs.getInt(3);
					playerInfo[7] = rs.getInt(4);
				}
				
				MM_MainView.cli1_ID.setText((String)playerInfo[0]+" 님       ");
				MM_MainView.cli1_Percent.setText(Integer.toString(((int)playerInfo[2] / ((int)playerInfo[2] + (int)playerInfo[3])))+"."+(int)playerInfo[2] % ((int)playerInfo[2] + (int)playerInfo[3])+"%     ");
				MM_MainView.cli1_Money.setText(Integer.toString((int)(playerInfo[1]))+"원       ");
				MM_MainView.cli2_ID.setText((String)playerInfo[4]+" 님       ");
				MM_MainView.cli2_Percent.setText(Integer.toString(((int)playerInfo[6] / ((int)playerInfo[6] + (int)playerInfo[7])))+"."+(int)playerInfo[6] % ((int)playerInfo[6] + (int)playerInfo[7])+"%     ");
				MM_MainView.cli2_Money.setText(Integer.toString((int)(playerInfo[5]))+"       ");
				
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				DBUtill.dbClose(con, ps, rs);
			}
		}
		return playerInfo;
	}

	@Override
	public void goldkeyEvent() throws Exception{
		String[] goldkeyInfo = new String[8];
		int cardNo = (int) (Math.random() * 20) + 1;
		con = DBUtill.getConnection();
		ps = null;
		rs = null;
		try {
			ps = con.prepareStatement("select * from goldkey where goldkey_no = ?");
			ps.setInt(1, cardNo);
			rs = ps.executeQuery();
			if(rs.next()){
				goldkeyInfo[0] = rs.getString(2); 
				goldkeyInfo[1] = rs.getString(3);
			}
			JOptionPane.showMessageDialog(null, "\t"+goldkeyInfo[0]+"\n"+goldkeyInfo[1]);
			goldkeyPlay(cardNo);
		}finally {
			DBUtill.dbClose(con, ps, rs);
		}
		
	}

	@Override
	public void setPlayerTurnOn(String currentPlayer) throws Exception{
		con = DBUtill.getConnection();
		ps = null;
		rs = null;
		try{
			ps = con.prepareStatement("update player set player_turn = ? where player_id = ?");
			ps.setInt(1, 1);
			ps.setString(2, currentPlayer);
			ps.executeUpdate();			
		}finally {
			DBUtill.dbClose(con, ps, rs);
		}
	}

	@Override
	public void setPlayerTurnOff(String currentPlayer) throws Exception{
		con = DBUtill.getConnection();
		ps = null;
		rs = null;
		try{
			ps = con.prepareStatement("update player set player_turn = ? where player_id = ?");
			ps.setInt(1, 0);
			ps.setString(2, currentPlayer);
			ps.executeUpdate();			
		}finally {
			DBUtill.dbClose(con, ps, rs);
		}		
	}


	@Override
	public boolean updatePlayerPoint(int cityNo) throws Exception{
		String tmpPlayer = MM_Controller.setCurrentPlayerTurn();
		con = DBUtill.getConnection();
		ps = null;
		rs = null;
		try {
			ps = con.prepareStatement("update player set player_location = ? where player_id = ?");
			ps.setInt(1, cityNo);
			ps.setString(2, tmpPlayer);
			if (ps.executeUpdate() >= 0) {
				return true;
			}
		}finally {
			DBUtill.dbClose(con, ps, rs);
		}
		return false;
	}

	@Override
	public int getPlayerLocation(String currentPlayer) throws Exception{
		con = DBUtill.getConnection();
		ps = null;
		rs = null;
		try {
			ps = con.prepareStatement("select player_location from player where player_id = ?");
			ps.setString(1, currentPlayer);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} finally {
			DBUtill.dbClose(con, ps, rs);
		}
		return 0;
	}

	@Override
	public void goldkeyPlay(int goldkeyNo) throws Exception{
		int currentMoney = 0;
		int currentLocation = getPlayerLocation(MM_Service.currentPlayer);
		
		con = DBUtill.getConnection();
		ps = null;
		rs = null;
		try{
			ps = con.prepareStatement("select player_money from player where player_turn = ?");
			ps.setInt(1, 1);
			rs = ps.executeQuery();
			if (rs.next()) {
				currentMoney = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		switch (goldkeyNo) {
		case 1:
			try{
				ps = con.prepareStatement("update player set player_money = ? where player_turn = ?");
				ps.setInt(1, (currentMoney+100000));
				ps.setInt(2, 1);
				ps.executeUpdate();		
				if(MM_Service.currentPlayer.equals("player1")){					
					MM_MainView.cli1_Money.setText(Integer.toString((currentMoney+100000)));
				}else{
					MM_MainView.cli2_Money.setText(Integer.toString((currentMoney+100000)));
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUtill.dbClose(con, ps, rs);
			}
			break;
		case 2:
			try{
				ps = con.prepareStatement("update player set player_money = ? where player_turn = ?");
				ps.setInt(1, (currentMoney+100000));
				ps.setInt(2, 1);
				ps.executeUpdate();		
				if(MM_Service.currentPlayer.equals("player1")){					
					MM_MainView.cli1_Money.setText(Integer.toString((currentMoney+100000)));
				}else{
					MM_MainView.cli2_Money.setText(Integer.toString((currentMoney+100000)));
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUtill.dbClose(con, ps, rs);
			}			
			break;
		case 3:
			try{
				ps = con.prepareStatement("update player set player_money = ? where player_turn = ?");
				ps.setInt(1, (currentMoney+300000));
				ps.setInt(2, 1);
				ps.executeUpdate();	
				if(MM_Service.currentPlayer.equals("player1")){					
					MM_MainView.cli1_Money.setText(Integer.toString((currentMoney+300000)));
				}else{
					MM_MainView.cli2_Money.setText(Integer.toString((currentMoney+300000)));
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUtill.dbClose(con, ps, rs);
			}
			break;
		case 4:
			try{
				ps = con.prepareStatement("update player set player_money = ? where player_turn = ?");
				ps.setInt(1, (currentMoney+1000000));
				ps.setInt(2, 1);
				ps.executeUpdate();		
				if(MM_Service.currentPlayer.equals("player1")){					
					MM_MainView.cli1_Money.setText(Integer.toString((currentMoney+1000000)));
				}else{
					MM_MainView.cli2_Money.setText(Integer.toString((currentMoney+1000000)));
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUtill.dbClose(con, ps, rs);
			}
			break;
		case 5:
			try{
				ps = con.prepareStatement("update player set player_money = ? where player_turn = ?");
				ps.setInt(1, (currentMoney+1000000));
				ps.setInt(2, 1);
				ps.executeUpdate();	
				if(MM_Service.currentPlayer.equals("player1")){					
					MM_MainView.cli1_Money.setText(Integer.toString((currentMoney+1000000)));
				}else{
					MM_MainView.cli2_Money.setText(Integer.toString((currentMoney+1000000)));
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUtill.dbClose(con, ps, rs);
			}
			break;
		case 6:
			try{
				ps = con.prepareStatement("update player set player_money = ? where player_turn = ?");
				ps.setInt(1, (currentMoney-500000));
				ps.setInt(2, 1);
				ps.executeUpdate();		
				if(MM_Service.currentPlayer.equals("player1")){					
					MM_MainView.cli1_Money.setText(Integer.toString((currentMoney-500000)));
				}else{
					MM_MainView.cli2_Money.setText(Integer.toString((currentMoney-500000)));
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUtill.dbClose(con, ps, rs);
			}
			break;
		case 7:
			try{
				ps = con.prepareStatement("update player set player_money = ? where player_turn = ?");
				ps.setInt(1, (currentMoney+5000000));
				ps.setInt(2, 1);
				ps.executeUpdate();			
				if(MM_Service.currentPlayer.equals("player1")){					
					MM_MainView.cli1_Money.setText(Integer.toString((currentMoney+5000000)));
				}else{
					MM_MainView.cli2_Money.setText(Integer.toString((currentMoney+5000000)));
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUtill.dbClose(con, ps, rs);
			}
			break;
		case 8:
			try{
				ps = con.prepareStatement("update player set player_money = ? where player_turn = ?");
				ps.setInt(1, (currentMoney-500000));
				ps.setInt(2, 1);
				ps.executeUpdate();			
				if(MM_Service.currentPlayer.equals("player1")){					
					MM_MainView.cli1_Money.setText(Integer.toString((currentMoney-500000)));
				}else{
					MM_MainView.cli2_Money.setText(Integer.toString((currentMoney-500000)));
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUtill.dbClose(con, ps, rs);
			}
			break;
		case 9:
			try{
				ps = con.prepareStatement("update player set player_money = ? where player_turn = ?");
				ps.setInt(1, (currentMoney-1000000));
				ps.setInt(2, 1);
				ps.executeUpdate();		
				if(MM_Service.currentPlayer.equals("player1")){					
					MM_MainView.cli1_Money.setText(Integer.toString((currentMoney-1000000)));
				}else{
					MM_MainView.cli2_Money.setText(Integer.toString((currentMoney-1000000)));
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUtill.dbClose(con, ps, rs);
			}
			break;
		case 10:
			try{
				ps = con.prepareStatement("update player set player_money = ? where player_turn = ?");
				ps.setInt(1, (currentMoney-100000));
				ps.setInt(2, 1);
				ps.executeUpdate();			
				if(MM_Service.currentPlayer.equals("player1")){					
					MM_MainView.cli1_Money.setText(Integer.toString((currentMoney-100000)));
				}else{
					MM_MainView.cli2_Money.setText(Integer.toString((currentMoney-100000)));
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUtill.dbClose(con, ps, rs);
			}
			break;
		case 11:
			try{
				ps = con.prepareStatement("update player set player_money = ? where player_turn = ?");
				ps.setInt(1, (currentMoney-300000));
				ps.setInt(2, 1);
				ps.executeUpdate();				
				if(MM_Service.currentPlayer.equals("player1")){					
					MM_MainView.cli1_Money.setText(Integer.toString((currentMoney-300000)));
				}else{
					MM_MainView.cli2_Money.setText(Integer.toString((currentMoney-300000)));
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUtill.dbClose(con, ps, rs);
			}
			break;
		case 12:
			try{
				ps = con.prepareStatement("update player set player_money = ? where player_turn = ?");
				ps.setInt(1, (currentMoney-1000000));
				ps.setInt(2, 1);
				ps.executeUpdate();	
				if(MM_Service.currentPlayer.equals("player1")){					
					MM_MainView.cli1_Money.setText(Integer.toString((currentMoney-1000000)));
				}else{
					MM_MainView.cli2_Money.setText(Integer.toString((currentMoney-1000000)));
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUtill.dbClose(con, ps, rs);
			}
			break;
		case 13:
			try{
				ps = con.prepareStatement("update player set player_location = ? where player_turn = ?");
				ps.setInt(1, 39);
				ps.setInt(2, 1);
				ps.executeUpdate();	
				if(MM_Service.currentPlayer.equals("player1")){					
					MM_StartView.mainView.go(39);
				}else{
					MM_StartView.mainView.go2(39);
					
				}
				MM_Service.getCityInfo(39);
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUtill.dbClose(con, ps, rs);
			}
			break;
		case 14:
			try{
				ps = con.prepareStatement("update player set player_location = ? where player_turn = ?");
				ps.setInt(1, 30);
				ps.setInt(2, 1);
				ps.executeUpdate();	
				if(MM_Service.currentPlayer.equals("player1")){
					
					MM_StartView.mainView.go(30);
				}else{
					MM_StartView.mainView.go2(30);
				}
				MM_Service.getCityInfo(30);
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUtill.dbClose(con, ps, rs);
			}
			break;
		case 15:
			try{
				ps = con.prepareStatement("update player set player_location = ? where player_turn = ?");
				ps.setInt(1, 10);
				ps.setInt(2, 1);
				ps.executeUpdate();	
				if(MM_Service.currentPlayer.equals("player1")){
					
					MM_StartView.mainView.go(10);
				}else{
					MM_StartView.mainView.go2(10);
					
				}
				MM_Service.getCityInfo(10);
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUtill.dbClose(con, ps, rs);
			}
			break;
		case 16:
			try{
				ps = con.prepareStatement("update player set player_location = ? where player_turn = ?");
				ps.setInt(1, (currentLocation+3));
				ps.setInt(2, 1);
				ps.executeUpdate();	
				if(MM_Service.currentPlayer.equals("player1")){
					
					MM_StartView.mainView.go((currentLocation+3));
				}else{
					MM_StartView.mainView.go2((currentLocation+3));
					
				}

				MM_Service.getCityInfo((currentLocation+3));
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUtill.dbClose(con, ps, rs);
			}
			break;
		case 17:
			try{
				ps = con.prepareStatement("update player set player_location = ? where player_turn = ?");
				ps.setInt(1, (currentLocation+2));
				ps.setInt(2, 1);
				ps.executeUpdate();	
				if(MM_Service.currentPlayer.equals("player1")){
					MM_StartView.mainView.go((currentLocation+2));
				}else{
					MM_StartView.mainView.go2((currentLocation+2));					
				}
				MM_Service.getCityInfo((currentLocation+2));
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUtill.dbClose(con, ps, rs);
			}
			break;
		case 18:
			try{
				ps = con.prepareStatement("update player set player_money = ? where player_turn = ?");
				ps.setInt(1, (currentMoney-500000));
				ps.setInt(2, 1);
				ps.executeUpdate();	
				if(MM_Service.currentPlayer.equals("player1")){					
					MM_MainView.cli1_Money.setText(Integer.toString((currentMoney-500000)));
				}else{
					MM_MainView.cli2_Money.setText(Integer.toString((currentMoney-500000)));
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUtill.dbClose(con, ps, rs);
			}
			break;
		case 19:
			try{
				ps = con.prepareStatement("update player set player_location = ? where player_turn = ?");
				ps.setInt(1, (currentLocation-2));
				ps.setInt(2, 1);
				ps.executeUpdate();	
				if(MM_Service.currentPlayer.equals("player1")){
					MM_StartView.mainView.back((currentLocation-2));					
				}else{
					MM_StartView.mainView.back2((currentLocation-2));
				}
				MM_Service.getCityInfo((currentLocation-2));
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUtill.dbClose(con, ps, rs);
			}
			break;
		default:
			try{
				ps = con.prepareStatement("update player set player_location = ? where player_turn = ?");
				ps.setInt(1, (currentLocation-3));
				ps.setInt(2, 1);
				ps.executeUpdate();	
				if(MM_Service.currentPlayer.equals("player1")){
					MM_StartView.mainView.back((currentLocation-3));					
				}else{
					MM_StartView.mainView.back2((currentLocation-3));
				}
				MM_Service.getCityInfo((currentLocation-3));
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUtill.dbClose(con, ps, rs);
			}		
			break;
		}
	}

	@Override
	public void updatePlayerMoney(int money) throws Exception{
		con = DBUtill.getConnection();
		ps = null;
		rs = null;
		try{
			ps = con.prepareStatement("update player set player_money = ? where player_turn = ?");
			ps.setInt(1, money);
			ps.setInt(2, 1);
			ps.executeUpdate();	
		}finally {
			DBUtill.dbClose(con, ps, rs);
		}
	}
	
	@Override
	public void updatePlayerMoney(int money, String player) throws Exception{
		con = DBUtill.getConnection();
		ps = null;
		rs = null;
		try{
			ps = con.prepareStatement("update player set player_money = ? where player_turn = ? AND player_id = ?");
			ps.setInt(1, money);
			ps.setInt(2, 1);
			ps.setString(3, player);
			ps.executeUpdate();	
		}finally {
			DBUtill.dbClose(con, ps, rs);
		}
	}

	@Override
	public void updateCityOwner(int cityNo, String currentPlayer, int bl) throws Exception{
		con = DBUtill.getConnection();
		ps = null;
		rs = null;
		try{
			if(bl==1){
				ps = con.prepareStatement("update city set city_Owner = ?, city_GB = ? where city_No = ?");
				ps.setString(1, currentPlayer);
				ps.setInt(2, 1);
				ps.setInt(3, cityNo);
				ps.executeUpdate();
			}else if(bl==2){
				ps = con.prepareStatement("update city set city_Owner = ?, city_HB = ? where city_No = ?");
				ps.setString(1, currentPlayer);
				ps.setInt(2, 1);
				ps.setInt(3, cityNo);
				ps.executeUpdate();
			}else if(bl==3){
				ps = con.prepareStatement("update city set city_Owner = ?, city_LB = ? where city_No = ?");
				ps.setString(1, currentPlayer);
				ps.setInt(2, 1);
				ps.setInt(3, cityNo);
				ps.executeUpdate();
			}else if(bl==4){
				ps = con.prepareStatement("update city set city_Owner = ?, city_GB = ? where city_No = ?");
				ps.setString(1, currentPlayer);
				ps.setInt(2, 0);
				ps.setInt(3, cityNo);
				ps.executeUpdate();
			}else if(bl==5){
				ps = con.prepareStatement("update city set city_Owner = ?, city_HB = ? where city_No = ?");
				ps.setString(1, currentPlayer);
				ps.setInt(2, 0);
				ps.setInt(3, cityNo);
				ps.executeUpdate();
			}else if(bl==6){
				ps = con.prepareStatement("update city set city_Owner = ?, city_LB = ? where city_No = ?");
				ps.setString(1, currentPlayer);
				ps.setInt(2, 0);
				ps.setInt(3, cityNo);
				ps.executeUpdate();
			}
		}finally {
			DBUtill.dbClose(con, ps, rs);
		}		
	}

	@Override
	public void getOwnerCity(String currentPlayer) throws Exception{
		con = DBUtill.getConnection();
		ps = null;
		rs = null;
		try{
			ps = con.prepareStatement("select city_Name, city_GB, city_HB, city_LB from city where city_Owner = ? ");
			ps.setString(1, currentPlayer);
			rs = ps.executeQuery();
			while(rs.next()){
				Object data[] = {"     "+rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)};
				if(currentPlayer.equals("player1")){
					MM_StartView.mainView.cli1_Model.addRow(data);
				}else{
					MM_StartView.mainView.cli2_Model.addRow(data);
					
				}
			}
		}finally {
			DBUtill.dbClose(con, ps, rs);
		}		
	}

	@Override
	public void setPlayerInit() throws Exception {
		con = DBUtill.getConnection();
		ps = null;
		rs = null;
		try{
			ps = con.prepareStatement("update player set player_money = ?, player_location = ?, player_turn = ? ");
			ps.setInt(1, 1000000);
			ps.setInt(2, 0);
			ps.setInt(3, 0);
			ps.executeUpdate();	
			ps = con.prepareStatement("update city set city_GB = ?, city_HB = ?, city_LB = ?, city_Owner = ?");
			ps.setInt(1, 0);
			ps.setInt(2, 0);
			ps.setInt(3, 0);
			ps.setString(4, "소유주없음" );
			ps.executeUpdate();	
			ps = con.prepareStatement("update player set player_turn = ? where player_id = ?");
			ps.setInt(1, 1);
			ps.setString(2, "player1");
			ps.executeUpdate();
		}finally {
			DBUtill.dbClose(con, ps, rs);
		}		
	}

}
