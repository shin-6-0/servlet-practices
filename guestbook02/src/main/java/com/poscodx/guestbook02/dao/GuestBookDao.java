package com.poscodx.guestbook02.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

import com.poscodx.guestbook02.vo.GuestBookVo;

public class GuestBookDao {
	private void syso() {
		
	}
	public List<GuestBookVo> findAll() {
		List<GuestBookVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();

			//3. SQL 준비
			String sql = "select * from guestbook "
					+ "order by no desc";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			//6. 결과 처리
			while(rs.next()) {
				long no = rs.getLong(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				String contents = rs.getString(4);
				Timestamp regDate = rs.getTimestamp(5);
				LocalDateTime localDateTime = null;
				if (regDate != null) {
				    localDateTime = regDate.toLocalDateTime();
				}
				GuestBookVo vo = new GuestBookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setPassword(password);
				vo.setContents(contents);
				vo.setRegDate(localDateTime);
				
				result.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("Guestbook findAll error:" + e);
		} finally {
			try {
				// 7. 자원정리
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public void insert(GuestBookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into guestbook values(null, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContents());
			// LocalDateTime를 Timestamp로 변환
			LocalDateTime localDateTime = vo.getRegDate();
			Timestamp timestamp = null;
			if (localDateTime != null) {
			    timestamp = Timestamp.valueOf(localDateTime);
			}
			pstmt.setTimestamp(4, timestamp);
			
			pstmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("Guestbook Insert error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String findPwByNo(int no) {
		String result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql =
					"select password "
					+ "from guestbook "
					+ "where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String password = rs.getString(1);
				
				result = password;
			}
			
		} catch (SQLException e) {
			System.out.println("Guestbook findPwByNo error: " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public void deleteByNo(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql =
					"delete from guestbook "
					+ "where no = ?";
			pstmt = conn.prepareStatement(sql);
				
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("Guestbook delete error: " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.0.13:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		
		return conn;
	}
	
}