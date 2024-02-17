//도서관프로그램 기능 클래스
package library_program;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LibraryDAO {
	//필드
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	SimpleDateFormat sdf = new SimpleDateFormat("yy/mm/dd");
	
	// 기능 닫기
	void disconn() {	
		try {
			if(conn != null){
				conn.close();
			}
			if(psmt != null) {
				psmt.close();
			}
			if(rs != null) {
				rs.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} 
	}
		
		
	// ==== 로그인 기능 ==== 완성
	public boolean login(String u_id, String u_pw){
		conn = DAO.getConn();
		sql = "select member_id, member_pw from members where member_id = ? and member_pw = ?";
		try {
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
		} finally {
			disconn();
		}
		return false;
	}
		
	// ==== 회원가입 기능 ==== 완성
	public boolean join(String u_name, String u_id, String u_pw, String u_email, String u_birthDate) {
		conn = DAO.getConn();
		sql = "insert into members (member_no, name, member_id, member_pw, email, birth_date) values (member_no_seq.NEXTVAL, ?, ?, ?, ?, TO_DATE(?, 'yy/mm/dd'))";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, u_name);
			psmt.setString(2, u_id);
			psmt.setString(3, u_pw);
			psmt.setString(4, u_email);
			psmt.setString(5, u_birthDate);
			int r = psmt.executeUpdate();
				if(r > 0) {
					return true;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return false;
	}
		
	// ============= 관리자 전용 기능 ==================
	// ==== 도서등록 기능 ==== 완성
	public boolean addBook(String b_name, String g_no, String b_writer, String publishDate, String publication, String mediaType, String useObject, String checkState) {
		conn = DAO.getConn();
		sql = "insert into books (book_no, book_name, group_no, writer, publish_date, publication, media_type, use_object, check_state) values (book_no_seq.nextval, ?, ?, ?, TO_DATE(?, 'yy/mm/dd'), ?, ?, ?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, b_name);
			psmt.setString(2, g_no);
			psmt.setString(3, b_writer);
			psmt.setString(4, publishDate);
			psmt.setString(5, publication);
			psmt.setString(6, mediaType);
			psmt.setString(7, useObject);
			psmt.setString(8, checkState);
			int r = psmt.executeUpdate();
			if(r > 0) {
				return true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return false;
	}
	// ==== 도서수정 기능 ==== 완성
	public boolean updateBook(Books updateBook, String bookName) {
		conn = DAO.getConn();
		if(!bookName.equals("")) {
			sql = "update books set ";
			if(!updateBook.getBookName().equals("")) {
				sql += " book_name = nvl(?, book_name) ";
			}
			else if(!updateBook.getGroupNo().equals("")) {
				sql += ", group_no = nvl(?, group_no) ";
			}
			else if(!updateBook.getWriter().equals("")){
				sql += ", writer = nvl(?, writer) ";
			}
			else if(!updateBook.getPublishDate().equals("")){
				sql += ", publish_date = TO_DATE(nvl(?, publish_date), 'yy/mm/dd') ";
			}
			else if(!updateBook.getPublication().equals("")){
				sql += ", publication = nvl(?, publication) ";
			}
			else if(!updateBook.getMediaType().equals("")){
				sql += ", media_type = nvl(?, media_type) ";
			}
			else if(!updateBook.getUseObject().equals("")){
				sql += ", use_object = nvl(?, use_object) ";
			}
			else if(!updateBook.getCheckState().equals("")){
				sql += ", check_state = nvl(?, check_state) ";
			}
			sql += " where book_name = ? ";
			try {
				psmt = conn.prepareStatement(sql);
				int cnt = 1;
				if(!updateBook.getBookName().equals("")) {
					psmt.setString(cnt++, updateBook.getBookName());
				}
				else if(!updateBook.getGroupNo().equals("")) {
					psmt.setString(cnt++, updateBook.getGroupNo());
				}
				else if(!updateBook.getWriter().equals("")){
					psmt.setString(cnt++, updateBook.getWriter());
				}
				else if(!updateBook.getPublishDate().equals("")){
					psmt.setString(cnt++, updateBook.getPublishDate());
				}
				else if(!updateBook.getPublication().equals("")){
					psmt.setString(cnt++, updateBook.getPublication());
				}
				else if(!updateBook.getMediaType().equals("")){
					psmt.setString(cnt++, updateBook.getMediaType());
				}
				else if(!updateBook.getUseObject().equals("")){
					psmt.setString(cnt++, updateBook.getUseObject());
				}
				else if(!updateBook.getCheckState().equals("")){
					psmt.setString(cnt++, updateBook.getCheckState());
				}
				psmt.setString(cnt++, bookName);
				int r = psmt.executeUpdate();
				if(r > 0) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconn();
			}
		}			
		return false;
	}
	// ==== 도서삭제 기능 ==== 완성
	public boolean deleteBook(String b_name) {
		conn = DAO.getConn();
		sql = "delete books where book_name = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, b_name);
			int r = psmt.executeUpdate();
			if(r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return false;
	}
	// ==== 회원등록 기능 ==== 완성
	public boolean addMember(String u_name, String u_id, String u_pw, String u_email, String u_birthDate) {
		conn = DAO.getConn();
		sql = "insert into members (member_no, name, member_id, member_pw, email, birth_date) values (member_no_seq.NEXTVAL, ?, ?, ?, ?, TO_DATE(?, 'yy/mm/dd'))";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, u_name);
			psmt.setString(2, u_id);
			psmt.setString(3, u_pw);
			psmt.setString(4, u_email);
			psmt.setString(5, u_birthDate);
			int r = psmt.executeUpdate();
			if(r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return false;
	}
	// ==== 회원삭제 기능 ==== 완성
	public boolean deleteMember(String u_name) {
		conn = DAO.getConn();
		sql = "delete members where member_id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, u_name);
			int r = psmt.executeUpdate();
			if(r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return false;
	}
	//종료
	public boolean off(boolean offCheck) {
		offCheck = false;
		return offCheck;
	}
		
	// ==== 사용자 전용 기능 ====
	//도서검색
	public String searchBook(int searchOption1, String searchData, int searchOption2){
		conn = DAO.getConn();
		List<Books> bookList = new ArrayList<>();
		switch(searchOption1) {
		//도서명
		case 1:
			if(searchOption2 == 1) { //오름차순
				sql = "select b.book_name, g.group_no, g.group_name, b.writer, b.publish_date, b.publication, b.media_type, b.use_object, b.check_state, g.book_location, g.book_own "
						+ " from books b join book_groups g on (b.group_no = g.group_no) "
						+ " where b.book_name = ? "
						+ " order by b.book_name asc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while(rs.next()) {
						Books book = new Books();
						book.setBookName(rs.getString("book_name"));
						book.setGroupNo(rs.getString("group_no"));
						book.setGroupName(rs.getString("group_name"));
						book.setWriter(rs.getString("writer"));
						book.setPublishDate(rs.getString("publish_date"));
						book.setPublication(rs.getString("publication"));
						book.setMediaType(rs.getString("media_type"));
						book.setUseObject(rs.getString("use_object"));
						book.setCheckState(rs.getString("check_state"));
						book.setBookLocation(rs.getString("book_location"));
						book.setBookOwn(rs.getString("book_own"));
						bookList.add(book);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else { //내림차순
				sql = "select b.book_name, g.group_no, g.group_name, b.writer, b.publish_date, b.publication, b.media_type, b.use_object, b.check_state, g.book_location, g.book_own "
						+ " from books b join book_groups g on (b.group_no = g.group_no) "
						+ " where b.book_name = ? "
						+ " order by b.book_name desc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while(rs.next()) {
						Books book = new Books();
						book.setBookName(rs.getString("book_name"));
						book.setGroupNo(rs.getString("group_no"));
						book.setGroupName(rs.getString("group_name"));
						book.setWriter(rs.getString("writer"));
						book.setPublishDate(rs.getString("publish_date"));
						book.setPublication(rs.getString("publication"));
						book.setMediaType(rs.getString("media_type"));
						book.setUseObject(rs.getString("use_object"));
						book.setCheckState(rs.getString("check_state"));
						book.setBookLocation(rs.getString("book_location"));
						book.setBookOwn(rs.getString("book_own"));
						bookList.add(book);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			break;
		//분류번호
		case 2:
			if(searchOption2 == 1) { //오름차순
				sql = "select b.book_name, g.group_no, g.group_name, b.writer, b.publish_date, b.publication, b.media_type, b.use_object, b.check_state, g.book_location, g.book_own "
						+ " from books b join book_groups g on (b.group_no = g.group_no) "
						+ " where g.group_no = ? "
						+ " order by g.group_no asc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while(rs.next()) {
						Books book = new Books();
						book.setBookName(rs.getString("book_name"));
						book.setGroupNo(rs.getString("group_no"));
						book.setGroupName(rs.getString("group_name"));
						book.setWriter(rs.getString("writer"));
						book.setPublishDate(rs.getString("publish_date"));
						book.setPublication(rs.getString("publication"));
						book.setMediaType(rs.getString("media_type"));
						book.setUseObject(rs.getString("use_object"));
						book.setCheckState(rs.getString("check_state"));
						book.setBookLocation(rs.getString("book_location"));
						book.setBookOwn(rs.getString("book_own"));
						bookList.add(book);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else { //내림차순
				sql = "select b.book_name, g.group_no, g.group_name, b.writer, b.publish_date, b.publication, b.media_type, b.use_object, b.check_state, g.book_location, g.book_own "
						+ " from books b join book_groups g on (b.group_no = g.group_no) "
						+ " where g.group_no = ? "
						+ " order by g.group_no desc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while(rs.next()) {
						Books book = new Books();
						book.setBookName(rs.getString("book_name"));
						book.setGroupNo(rs.getString("group_no"));
						book.setGroupName(rs.getString("group_name"));
						book.setWriter(rs.getString("writer"));
						book.setPublishDate(rs.getString("publish_date"));
						book.setPublication(rs.getString("publication"));
						book.setMediaType(rs.getString("media_type"));
						book.setUseObject(rs.getString("use_object"));
						book.setCheckState(rs.getString("check_state"));
						book.setBookLocation(rs.getString("book_location"));
						book.setBookOwn(rs.getString("book_own"));
						bookList.add(book);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			break;
		//저자
		case 3:
			if(searchOption2 == 1) { //오름차순
				sql = "select b.book_name, g.group_no, g.group_name, b.writer, b.publish_date, b.publication, b.media_type, b.use_object, b.check_state, g.book_location, g.book_own "
						+ " from books b join book_groups g on (b.group_no = g.group_no) "
						+ " where b.writer = ? "
						+ " order by b.writer asc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while(rs.next()) {
						Books book = new Books();
						book.setBookName(rs.getString("book_name"));
						book.setGroupNo(rs.getString("group_no"));
						book.setGroupName(rs.getString("group_name"));
						book.setWriter(rs.getString("writer"));
						book.setPublishDate(rs.getString("publish_date"));
						book.setPublication(rs.getString("publication"));
						book.setMediaType(rs.getString("media_type"));
						book.setUseObject(rs.getString("use_object"));
						book.setCheckState(rs.getString("check_state"));
						book.setBookLocation(rs.getString("book_location"));
						book.setBookOwn(rs.getString("book_own"));
						bookList.add(book);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else { //내림차순
				sql = "select b.book_name, g.group_no, g.group_name, b.writer, b.publish_date, b.publication, b.media_type, b.use_object, b.check_state, g.book_location, g.book_own "
						+ " from books b join book_groups g on (b.group_no = g.group_no) "
						+ " where b.writer = ? "
						+ " order by b.writer desc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while(rs.next()) {
						Books book = new Books();
						book.setBookName(rs.getString("book_name"));
						book.setGroupNo(rs.getString("group_no"));
						book.setGroupName(rs.getString("group_name"));
						book.setWriter(rs.getString("writer"));
						book.setPublishDate(rs.getString("publish_date"));
						book.setPublication(rs.getString("publication"));
						book.setMediaType(rs.getString("media_type"));
						book.setUseObject(rs.getString("use_object"));
						book.setCheckState(rs.getString("check_state"));
						book.setBookLocation(rs.getString("book_location"));
						book.setBookOwn(rs.getString("book_own"));
						bookList.add(book);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			break;
		//출판년도
		case 4:
			if(searchOption2 == 1) { //오름차순
				sql = "select b.book_name, g.group_no, g.group_name, b.writer, b.publish_date, b.publication, b.media_type, b.use_object, b.check_state, g.book_location, g.book_own "
						+ " from books b join book_groups g on (b.group_no = g.group_no) "
						+ " where b.publish_date = TO_DATE(?, 'yy/mm/dd') "
						+ " order by b.publish_date asc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while(rs.next()) {
						Books book = new Books();
						book.setBookName(rs.getString("book_name"));
						book.setGroupNo(rs.getString("group_no"));
						book.setGroupName(rs.getString("group_name"));
						book.setWriter(rs.getString("writer"));
						book.setPublishDate(rs.getString("publish_date"));
						book.setPublication(rs.getString("publication"));
						book.setMediaType(rs.getString("media_type"));
						book.setUseObject(rs.getString("use_object"));
						book.setCheckState(rs.getString("check_state"));
						book.setBookLocation(rs.getString("book_location"));
						book.setBookOwn(rs.getString("book_own"));
						bookList.add(book);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else { //내림차순
				sql = "select b.book_name, g.group_no, g.group_name, b.writer, b.publish_date, b.publication, b.media_type, b.use_object, b.check_state, g.book_location, g.book_own "
						+ " from books b join book_groups g on (b.group_no = g.group_no) "
						+ " where b.publish_date = TO_DATE(?, 'yy/mm/dd') "
						+ " order by b.publish_date desc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while(rs.next()) {
						Books book = new Books();
						book.setBookName(rs.getString("book_name"));
						book.setGroupNo(rs.getString("group_no"));
						book.setGroupName(rs.getString("group_name"));
						book.setWriter(rs.getString("writer"));
						book.setPublishDate(rs.getString("publish_date"));
						book.setPublication(rs.getString("publication"));
						book.setMediaType(rs.getString("media_type"));
						book.setUseObject(rs.getString("use_object"));
						book.setCheckState(rs.getString("check_state"));
						book.setBookLocation(rs.getString("book_location"));
						book.setBookOwn(rs.getString("book_own"));
						bookList.add(book);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			break;
		//출판사
		case 5:
			if(searchOption2 == 1) { //오름차순
				sql = "select b.book_name, g.group_no, g.group_name, b.writer, b.publish_date, b.publication, b.media_type, b.use_object, b.check_state, g.book_location, g.book_own "
						+ " from books b join book_groups g on (b.group_no = g.group_no) "
						+ " where b.publication = ? "
						+ " order by b.publication asc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while(rs.next()) {
						Books book = new Books();
						book.setBookName(rs.getString("book_name"));
						book.setGroupNo(rs.getString("group_no"));
						book.setGroupName(rs.getString("group_name"));
						book.setWriter(rs.getString("writer"));
						book.setPublishDate(rs.getString("publish_date"));
						book.setPublication(rs.getString("publication"));
						book.setMediaType(rs.getString("media_type"));
						book.setUseObject(rs.getString("use_object"));
						book.setCheckState(rs.getString("check_state"));
						book.setBookLocation(rs.getString("book_location"));
						book.setBookOwn(rs.getString("book_own"));
						bookList.add(book);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else { //내림차순
				sql = "select b.book_name, g.group_no, g.group_name, b.writer, b.publish_date, b.publication, b.media_type, b.use_object, b.check_state, g.book_location, g.book_own "
						+ " from books b join book_groups g on (b.group_no = g.group_no) "
						+ " where b.publication = ? "
						+ " order by b.publication desc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while(rs.next()) {
						Books book = new Books();
						book.setBookName(rs.getString("book_name"));
						book.setGroupNo(rs.getString("group_no"));
						book.setGroupName(rs.getString("group_name"));
						book.setWriter(rs.getString("writer"));
						book.setPublishDate(rs.getString("publish_date"));
						book.setPublication(rs.getString("publication"));
						book.setMediaType(rs.getString("media_type"));
						book.setUseObject(rs.getString("use_object"));
						book.setCheckState(rs.getString("check_state"));
						book.setBookLocation(rs.getString("book_location"));
						book.setBookOwn(rs.getString("book_own"));
						bookList.add(book);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			break;
		//매체형태
		case 6:
			if(searchOption2 == 1) { //오름차순
				sql = "select b.book_name, g.group_no, g.group_name, b.writer, b.publish_date, b.publication, b.media_type, b.use_object, b.check_state, g.book_location, g.book_own "
						+ " from books b join book_groups g on (b.group_no = g.group_no) "
						+ " where b.media_type = ? "
						+ " order by b.media_type asc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while(rs.next()) {
						Books book = new Books();
						book.setBookName(rs.getString("book_name"));
						book.setGroupNo(rs.getString("group_no"));
						book.setGroupName(rs.getString("group_name"));
						book.setWriter(rs.getString("writer"));
						book.setPublishDate(rs.getString("publish_date"));
						book.setPublication(rs.getString("publication"));
						book.setMediaType(rs.getString("media_type"));
						book.setUseObject(rs.getString("use_object"));
						book.setCheckState(rs.getString("check_state"));
						book.setBookLocation(rs.getString("book_location"));
						book.setBookOwn(rs.getString("book_own"));
						bookList.add(book);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else { //내림차순
				sql = "select b.book_name, g.group_no, g.group_name, b.writer, b.publish_date, b.publication, b.media_type, b.use_object, b.check_state, g.book_location, g.book_own "
						+ " from books b join book_groups g on (b.group_no = g.group_no) "
						+ " where b.media_type = ? "
						+ " order by b.media_type desc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while(rs.next()) {
						Books book = new Books();
						book.setBookName(rs.getString("book_name"));
						book.setGroupNo(rs.getString("group_no"));
						book.setGroupName(rs.getString("group_name"));
						book.setWriter(rs.getString("writer"));
						book.setPublishDate(rs.getString("publish_date"));
						book.setPublication(rs.getString("publication"));
						book.setMediaType(rs.getString("media_type"));
						book.setUseObject(rs.getString("use_object"));
						book.setCheckState(rs.getString("check_state"));
						book.setBookLocation(rs.getString("book_location"));
						book.setBookOwn(rs.getString("book_own"));
						bookList.add(book);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			break;
		//이용대상
		case 7:
			if(searchOption2 == 1) { //오름차순
				sql = "select b.book_name, g.group_no, g.group_name, b.writer, b.publish_date, b.publication, b.media_type, b.use_object, b.check_state, g.book_location, g.book_own "
						+ " from books b join book_groups g on (b.group_no = g.group_no) "
						+ " where b.use_object = ? "
						+ " order by b.use_object asc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while(rs.next()) {
						Books book = new Books();
						book.setBookName(rs.getString("book_name"));
						book.setGroupNo(rs.getString("group_no"));
						book.setGroupName(rs.getString("group_name"));
						book.setWriter(rs.getString("writer"));
						book.setPublishDate(rs.getString("publish_date"));
						book.setPublication(rs.getString("publication"));
						book.setMediaType(rs.getString("media_type"));
						book.setUseObject(rs.getString("use_object"));
						book.setCheckState(rs.getString("check_state"));
						book.setBookLocation(rs.getString("book_location"));
						book.setBookOwn(rs.getString("book_own"));
						bookList.add(book);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else { //내림차순
				sql = "select b.book_name, g.group_no, g.group_name, b.writer, b.publish_date, b.publication, b.media_type, b.use_object, b.check_state, g.book_location, g.book_own "
						+ " from books b join book_groups g on (b.group_no = g.group_no) "
						+ " where b.use_object = ? "
						+ " order by b.use_object desc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while(rs.next()) {
						Books book = new Books();
						book.setBookName(rs.getString("book_name"));
						book.setGroupNo(rs.getString("group_no"));
						book.setGroupName(rs.getString("group_name"));
						book.setWriter(rs.getString("writer"));
						book.setPublishDate(rs.getString("publish_date"));
						book.setPublication(rs.getString("publication"));
						book.setMediaType(rs.getString("media_type"));
						book.setUseObject(rs.getString("use_object"));
						book.setCheckState(rs.getString("check_state"));
						book.setBookLocation(rs.getString("book_location"));
						book.setBookOwn(rs.getString("book_own"));
						bookList.add(book);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			break;
		//대출유무
		case 8:
			if(searchOption2 == 1) { //오름차순
				sql = "select b.book_name, g.group_no, g.group_name, b.writer, b.publish_date, b.publication, b.media_type, b.use_object, b.check_state, g.book_location, g.book_own "
						+ " from books b join book_groups g on (b.group_no = g.group_no) "
						+ " where b.check_state = ? "
						+ " order by b.check_state asc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while(rs.next()) {
						Books book = new Books();
						book.setBookName(rs.getString("book_name"));
						book.setGroupNo(rs.getString("group_no"));
						book.setGroupName(rs.getString("group_name"));
						book.setWriter(rs.getString("writer"));
						book.setPublishDate(rs.getString("publish_date"));
						book.setPublication(rs.getString("publication"));
						book.setMediaType(rs.getString("media_type"));
						book.setUseObject(rs.getString("use_object"));
						book.setCheckState(rs.getString("check_state"));
						book.setBookLocation(rs.getString("book_location"));
						book.setBookOwn(rs.getString("book_own"));
						bookList.add(book);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else { //내림차순
				sql = "select b.book_name, g.group_no, g.group_name, b.writer, b.publish_date, b.publication, b.media_type, b.use_object, b.check_state, g.book_location, g.book_own "
						+ " from books b join book_groups g on (b.group_no = g.group_no) "
						+ " where b.check_state = ? "
						+ " order by b.check_state desc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while(rs.next()) {
						Books book = new Books();
						book.setBookName(rs.getString("book_name"));
						book.setGroupNo(rs.getString("group_no"));
						book.setGroupName(rs.getString("group_name"));
						book.setWriter(rs.getString("writer"));
						book.setPublishDate(rs.getString("publish_date"));
						book.setPublication(rs.getString("publication"));
						book.setMediaType(rs.getString("media_type"));
						book.setUseObject(rs.getString("use_object"));
						book.setCheckState(rs.getString("check_state"));
						book.setBookLocation(rs.getString("book_location"));
						book.setBookOwn(rs.getString("book_own"));
						bookList.add(book);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			break;
		//소장위치
		case 9:
			if(searchOption2 == 1) { //오름차순
				sql = "select b.book_name, g.group_no, g.group_name, b.writer, b.publish_date, b.publication, b.media_type, b.use_object, b.check_state, g.book_location, g.book_own "
						+ " from books b join book_groups g on (b.group_no = g.group_no) "
						+ " where g.book_location = ? "
						+ " order by g.book_location asc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while(rs.next()) {
						Books book = new Books();
						book.setBookName(rs.getString("book_name"));
						book.setGroupNo(rs.getString("group_no"));
						book.setGroupName(rs.getString("group_name"));
						book.setWriter(rs.getString("writer"));
						book.setPublishDate(rs.getString("publish_date"));
						book.setPublication(rs.getString("publication"));
						book.setMediaType(rs.getString("media_type"));
						book.setUseObject(rs.getString("use_object"));
						book.setCheckState(rs.getString("check_state"));
						book.setBookLocation(rs.getString("book_location"));
						book.setBookOwn(rs.getString("book_own"));
						bookList.add(book);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else { //내림차순
				sql = "select b.book_name, g.group_no, g.group_name, b.writer, b.publish_date, b.publication, b.media_type, b.use_object, b.check_state, g.book_location, g.book_own "
						+ " from books b join book_groups g on (b.group_no = g.group_no) "
						+ " where g.book_location = ? "
						+ " order by g.book_location desc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while(rs.next()) {
						Books book = new Books();
						book.setBookName(rs.getString("book_name"));
						book.setGroupNo(rs.getString("group_no"));
						book.setGroupName(rs.getString("group_name"));
						book.setWriter(rs.getString("writer"));
						book.setPublishDate(rs.getString("publish_date"));
						book.setPublication(rs.getString("publication"));
						book.setMediaType(rs.getString("media_type"));
						book.setUseObject(rs.getString("use_object"));
						book.setCheckState(rs.getString("check_state"));
						book.setBookLocation(rs.getString("book_location"));
						book.setBookOwn(rs.getString("book_own"));
						bookList.add(book);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					disconn();
				}
			}
			break;
		}
		return bookList;
	}
		
	//회원탈퇴
	public boolean unjoin(String unjoinMemberId) {
		conn = DAO.getConn();
		sql = "delete members where member_id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, unjoinMemberId);
			int r = psmt.executeUpdate();
			if(r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return false;
	}
		
	//내정보조회 아이디수정/비밀번호수정
	//public searchMember
	
	
	//대출목록조회
	
		
	//대출
		
	//반납
		
	//종료
}
