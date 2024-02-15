//도서관프로그램 기능 클래스
package library_program;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryDAO {
	//필드
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	
	// ==== 로그인 기능 ====
	public boolean login(String u_id, String u_pw){
		conn = DAO.getConn();
		sql = "select member_id, member_pw from members where member_id = ? and member_pw = ?";
		try {
			Members memb = new Members();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, u_id);
			psmt.setString(2, u_pw);
			rs = psmt.executeQuery();
			while(rs.next()) {				
				if(rs.getString("member_id").equals(u_id) && rs.getString("member_pw").equals(u_pw)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// ==== 회원가입 기능 ====
	
	
	// ==== 관리자 전용 기능 ====
	//등록
	
	//수정
	
	//삭제
	
	//회원등록
	
	//회원삭제
	
	//종료
	
	
	// ==== 사용자 전용 기능 ====
	//아이디수정
	
	//비밀번호수정
	
	//회원탈퇴
	
	//내정보조회
	
	//대출
	
	//반납
	
	//종료
}
