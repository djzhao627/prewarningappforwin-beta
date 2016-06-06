package com.prewarningapp.tools;

import java.sql.SQLException;
import java.util.ArrayList;

import com.prewarningapp.model.Board;
import com.prewarningapp.model.Warning;

public class Mysqlutil extends SuperOprMysql {

	public ArrayList<Board> getBoardList(String sql) {

		ArrayList<Board> boardList = new ArrayList<>();
		Board board ;
		cm = new ConmysqlManager();
		psmt =cm.getPreparedStatement(sql);
		try {
			rs = psmt.executeQuery();
			while(rs.next()){
				board  = new Board();
				board.setId(rs.getInt("id"));
				board.setBoardID(rs.getInt("boardID"));
				board.setPosX(rs.getInt("posX"));
				board.setPosY(rs.getInt("posY"));
				board.setPlace(rs.getString("place"));
				board.setState(rs.getInt("state"));
				board.setPosition(rs.getInt("position"));
				boardList.add(board);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			cm.close();
		}
		return boardList;
	}
	
	public ArrayList<Warning> getWarningList(String sql) {
		ArrayList<Warning> warningList = new ArrayList<>();
		Warning warning;
		cm = new ConmysqlManager();
		psmt = cm.getPreparedStatement(sql);
		try {
			rs = psmt.executeQuery();
			while (rs.next()) {
				warning = new Warning();
				warning.setWarningPlace(rs.getString("warningPlace"));
				warning.setWarningTime(rs.getString("warningTime"));
				warning.setWarningType(rs.getString("warningType"));
				warning.setBoardID(rs.getString("boardID"));
				warning.setWarningHandle(rs.getInt("warningHandle"));
				warning.setId(rs.getInt("id"));
				warning.setWarningHPeople(rs.getString("warningHPeople"));
				warning.setWarningHTime(rs.getString("warningHtime"));
				warningList.add(warning);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cm.close();
		}
		return warningList;
	}

	public boolean uodatePosition(String sql) {
		int row = 0;
		cm = new ConmysqlManager();
		psmt = cm.getPreparedStatement(sql);
		try {
			row = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cm.close();
		}
		return row > 0;
	}

	public ArrayList<Board> getBoardByBoardId(String sql) {
		cm = new ConmysqlManager();
		ArrayList<Board> list = new ArrayList<>();
		psmt = cm.getPreparedStatement(sql);
		try {
			rs = psmt.executeQuery();
			while (rs.next()) {
				Board b = new Board();
				b.setPosition(rs.getInt("position"));
				b.setPosX(rs.getInt("posX"));
				b.setPosY(rs.getInt("posY"));
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cm.close();
		}
		return list;
	}

	public int test(String sql) {
		psmt = cm.getPreparedStatement(sql);
		int row = 0;
		try {
			row = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cm.close();
		}
		return row;
	}

	public static void main(String[] args) {
		String sql = "insert into warningtb(warningPlace,warningTime,boardID,warningType,warningHPeople) values('二号厂区','2016-10-25','cbar','火警','李四')";
		Mysqlutil m = new Mysqlutil();
		System.out.println(m.test(sql));

	}
}
