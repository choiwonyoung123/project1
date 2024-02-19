//도서관프로그램 기능 클래스
package library_program;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LibraryDAO {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;

	// 기능 닫기
	void disconn() {
		try {
			if (conn != null) {
				conn.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ==== 로그인 기능 ==== 완성
	public boolean login(String u_id, String u_pw) {
		conn = DAO.getConn();
		sql = "select member_id, member_pw from members where member_id = ? and member_pw = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, u_id);
			psmt.setString(2, u_pw);
			rs = psmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("member_id").equals(u_id) && rs.getString("member_pw").equals(u_pw)) {
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
			if (r > 0) {
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
	public boolean addBook(String b_name, String g_no, String b_writer, String publishDate, String publication,
			String mediaType, String useObject, String checkState) {
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
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return false;
	}

	// ==== 도서수정 기능 ==== 완성
	public boolean updateBook(Books updateBook, String bookName) {
		conn = DAO.getConn();
		if (!bookName.equals("")) {
			sql = "update books set book_name = nvl(?, book_name) ";	
			if (!updateBook.getGroupNo().equals("")) {
				sql += ", group_no = nvl(?, group_no) ";
			}
			if (!updateBook.getWriter().equals("")) {
				sql += ", writer = nvl(?, writer) ";
			}
			if (!updateBook.getPublishDate().equals("")) {
				sql += ", publish_date = TO_DATE(nvl(?, publish_date), 'yy/mm/dd') ";
			}
			if (!updateBook.getPublication().equals("")) {
				sql += ", publication = nvl(?, publication) ";
			}
			if (!updateBook.getMediaType().equals("")) {
				sql += ", media_type = nvl(?, media_type) ";
			}
			if (!updateBook.getUseObject().equals("")) {
				sql += ", use_object = nvl(?, use_object) ";
			}
			if (!updateBook.getCheckState().equals("")) {
				sql += ", check_state = nvl(?, check_state) ";
			}
			sql += " where book_name = ? ";
			try {
				psmt = conn.prepareStatement(sql);
				int cnt = 1;
				psmt.setString(cnt++, updateBook.getBookName());
				if (!updateBook.getGroupNo().equals("")) {
					psmt.setString(cnt++, updateBook.getGroupNo());
				}
				if (!updateBook.getWriter().equals("")) {
					psmt.setString(cnt++, updateBook.getWriter());
				}
				if (!updateBook.getPublishDate().equals("")) {
					psmt.setString(cnt++, updateBook.getPublishDate());
				}
				if (!updateBook.getPublication().equals("")) {
					psmt.setString(cnt++, updateBook.getPublication());
				}
				if (!updateBook.getMediaType().equals("")) {
					psmt.setString(cnt++, updateBook.getMediaType());
				}
				if (!updateBook.getUseObject().equals("")) {
					psmt.setString(cnt++, updateBook.getUseObject());
				}
				if (!updateBook.getCheckState().equals("")) {
					psmt.setString(cnt++, updateBook.getCheckState());
				}
				psmt.setString(cnt++, bookName);
				int r = psmt.executeUpdate();
				if (r > 0) {
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
			if (r > 0) {
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
			if (r > 0) {
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
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return false;
	}
	// 종료
	

	// ========= 사용자 전용 기능 =========
	
	
	// ==== 도서목록 ==== 완성
	public List<Books> bookLists(int page){
		conn = DAO.getConn();
		List<Books> bookList = new ArrayList<>();
		sql = "select * from books order by book_no";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Books books = new Books();
				books.setBookNo(rs.getInt("book_no"));
				books.setBookName(rs.getString("book_name"));
				books.setGroupNo(rs.getString("group_no"));
				books.setWriter(rs.getString("writer"));
				books.setPublishDate(rs.getString("publish_date"));
				books.setPublication(rs.getString("publication"));
				books.setMediaType(rs.getString("media_type"));
				books.setUseObject(rs.getString("use_object"));
				books.setCheckState(rs.getString("check_state"));
				bookList.add(books);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return bookList;
	}
	
	// ==== 도서검색 ==== 완성
	public List<Books> searchBook(int searchOption1, String searchData, int searchOption2) {
		conn = DAO.getConn();
		String searchBookList;
		List<Books> bookList = new ArrayList<>();
		switch (searchOption1) {
		// 도서명
		case 1:
			if (searchOption2 == 1) { // 오름차순
				sql = "select book_no, book_name, group_no, group_name, writer, publish_date, publication, media_type, use_object, check_state"
						+ " from books where instr(book_name, ?) > 0 "
						+ " order by book_no asc, book_name asc";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while (rs.next()) {
						Books books = new Books();
						books.setBookNo(rs.getInt("book_no"));
						books.setBookName(rs.getString("book_name"));
						books.setGroupNo(rs.getString("group_no"));
						books.setWriter(rs.getString("writer"));
						books.setPublishDate(rs.getString("publish_date"));
						books.setPublication(rs.getString("publication"));
						books.setMediaType(rs.getString("media_type"));
						books.setUseObject(rs.getString("use_object"));
						books.setCheckState(rs.getString("check_state"));
						bookList.add(books);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else { // 내림차순
				sql = "select book_no, book_name, group_no, group_name, writer, publish_date, publication, media_type, use_object, check_state"
						+ " from books where instr(book_name, ?) > 0 "
						+ " order by book_no asc, book_name desc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while (rs.next()) {
						Books books = new Books();
						books.setBookNo(rs.getInt("book_no"));
						books.setBookName(rs.getString("book_name"));
						books.setGroupNo(rs.getString("group_no"));
						books.setWriter(rs.getString("writer"));
						books.setPublishDate(rs.getString("publish_date"));
						books.setPublication(rs.getString("publication"));
						books.setMediaType(rs.getString("media_type"));
						books.setUseObject(rs.getString("use_object"));
						books.setCheckState(rs.getString("check_state"));
						bookList.add(books);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			break;
		// 분류번호
		case 2:
			if (searchOption2 == 1) { // 오름차순
				sql = "select book_no, book_name, group_no, writer, publish_date, publication, media_type, use_object, check_state"
						+ " from books where instr(group_no, ?) > 0 "
						+ " order by book_no asc, group_no asc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while (rs.next()) {
						Books books = new Books();
						books.setBookNo(rs.getInt("book_no"));
						books.setBookName(rs.getString("book_name"));
						books.setGroupNo(rs.getString("group_no"));
						books.setWriter(rs.getString("writer"));
						books.setPublishDate(rs.getString("publish_date"));
						books.setPublication(rs.getString("publication"));
						books.setMediaType(rs.getString("media_type"));
						books.setUseObject(rs.getString("use_object"));
						books.setCheckState(rs.getString("check_state"));
						bookList.add(books);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else { // 내림차순
				sql = "select book_no, book_name, group_no, writer, publish_date, publication, media_type, use_object, check_state"
						+ " from books where instr(group_no, ?) > 0 "
						+ " order by book_no asc, group_no desc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while (rs.next()) {
						Books books = new Books();
						books.setBookNo(rs.getInt("book_no"));
						books.setBookName(rs.getString("book_name"));
						books.setGroupNo(rs.getString("group_no"));
						books.setWriter(rs.getString("writer"));
						books.setPublishDate(rs.getString("publish_date"));
						books.setPublication(rs.getString("publication"));
						books.setMediaType(rs.getString("media_type"));
						books.setUseObject(rs.getString("use_object"));
						books.setCheckState(rs.getString("check_state"));
						bookList.add(books);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			break;
		// 저자
		case 3:
			if (searchOption2 == 1) { // 오름차순
				sql = "select book_no, book_name, group_no, writer, publish_date, publication, media_type, use_object, check_state"
						+ " from books where instr(writer, ?) > 0 "
						+ " order by book_no asc, writer asc";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while (rs.next()) {
						Books books = new Books();
						books.setBookNo(rs.getInt("book_no"));
						books.setBookName(rs.getString("book_name"));
						books.setGroupNo(rs.getString("group_no"));
						books.setWriter(rs.getString("writer"));
						books.setPublishDate(rs.getString("publish_date"));
						books.setPublication(rs.getString("publication"));
						books.setMediaType(rs.getString("media_type"));
						books.setUseObject(rs.getString("use_object"));
						books.setCheckState(rs.getString("check_state"));
						bookList.add(books);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else { // 내림차순
				sql = "select book_no, book_name, group_no, writer, publish_date, publication, media_type, use_object, check_state"
						+ " from books where instr(writer, ?) > 0 "
						+ " order by book_no asc, writer desc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while (rs.next()) {
						Books books = new Books();
						books.setBookNo(rs.getInt("book_no"));
						books.setBookName(rs.getString("book_name"));
						books.setGroupNo(rs.getString("group_no"));
						books.setWriter(rs.getString("writer"));
						books.setPublishDate(rs.getString("publish_date"));
						books.setPublication(rs.getString("publication"));
						books.setMediaType(rs.getString("media_type"));
						books.setUseObject(rs.getString("use_object"));
						books.setCheckState(rs.getString("check_state"));
						bookList.add(books);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			break;
		// 출판년도
		case 4:
			if (searchOption2 == 1) { // 이후날짜
				sql = "select book_no, book_name, group_no, writer, publish_date, publication, media_type, use_object, check_state"
						+ " from books where publish_date > TO_DATE(?, 'yy/mm/dd') "
						+ " order by book_no asc, writer asc";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while (rs.next()) {
						Books books = new Books();
						books.setBookNo(rs.getInt("book_no"));
						books.setBookName(rs.getString("book_name"));
						books.setGroupNo(rs.getString("group_no"));
						books.setWriter(rs.getString("writer"));
						books.setPublishDate(rs.getString("publish_date"));
						books.setPublication(rs.getString("publication"));
						books.setMediaType(rs.getString("media_type"));
						books.setUseObject(rs.getString("use_object"));
						books.setCheckState(rs.getString("check_state"));
						bookList.add(books);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else { // 이전날짜
				sql = "select book_no, book_name, group_no, writer, publish_date, publication, media_type, use_object, check_state"
						+ " from books where publish_date < TO_DATE(?, 'yy/mm/dd') "
						+ " order by book_no asc, writer desc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while (rs.next()) {
						Books books = new Books();
						books.setBookNo(rs.getInt("book_no"));
						books.setBookName(rs.getString("book_name"));
						books.setGroupNo(rs.getString("group_no"));
						books.setWriter(rs.getString("writer"));
						books.setPublishDate(rs.getString("publish_date"));
						books.setPublication(rs.getString("publication"));
						books.setMediaType(rs.getString("media_type"));
						books.setUseObject(rs.getString("use_object"));
						books.setCheckState(rs.getString("check_state"));
						bookList.add(books);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			break;
		// 출판사
		case 5:
			if (searchOption2 == 1) { // 오름차순
				sql = "select book_no, book_name, group_no, writer, publish_date, publication, media_type, use_object, check_state"
						+ " from books where instr(publication, ?) > 0 "
						+ " order by book_no asc, publication asc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while (rs.next()) {
						Books books = new Books();
						books.setBookNo(rs.getInt("book_no"));
						books.setBookName(rs.getString("book_name"));
						books.setGroupNo(rs.getString("group_no"));
						books.setWriter(rs.getString("writer"));
						books.setPublishDate(rs.getString("publish_date"));
						books.setPublication(rs.getString("publication"));
						books.setMediaType(rs.getString("media_type"));
						books.setUseObject(rs.getString("use_object"));
						books.setCheckState(rs.getString("check_state"));
						bookList.add(books);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else { // 내림차순
				sql = "select book_no, book_name, group_no, writer, publish_date, publication, media_type, use_object, check_state"
						+ " from books where instr(publication, ?) > 0 "
						+ " order by book_no asc, publication desc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while (rs.next()) {
						Books books = new Books();
						books.setBookNo(rs.getInt("book_no"));
						books.setBookName(rs.getString("book_name"));
						books.setGroupNo(rs.getString("group_no"));
						books.setWriter(rs.getString("writer"));
						books.setPublishDate(rs.getString("publish_date"));
						books.setPublication(rs.getString("publication"));
						books.setMediaType(rs.getString("media_type"));
						books.setUseObject(rs.getString("use_object"));
						books.setCheckState(rs.getString("check_state"));
						bookList.add(books);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			break;
		// 매체형태
		case 6:
			if (searchOption2 == 1) { // 오름차순
				sql = "select book_no, book_name, group_no, writer, publish_date, publication, media_type, use_object, check_state"
						+ " from books where instr(media_type, ?) > 0 "
						+ " order by book_no asc, media_type asc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while (rs.next()) {
						Books books = new Books();
						books.setBookNo(rs.getInt("book_no"));
						books.setBookName(rs.getString("book_name"));
						books.setGroupNo(rs.getString("group_no"));
						books.setWriter(rs.getString("writer"));
						books.setPublishDate(rs.getString("publish_date"));
						books.setPublication(rs.getString("publication"));
						books.setMediaType(rs.getString("media_type"));
						books.setUseObject(rs.getString("use_object"));
						books.setCheckState(rs.getString("check_state"));
						bookList.add(books);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else { // 내림차순
				sql = "select book_no, book_name, group_no, writer, publish_date, publication, media_type, use_object, check_state"
						+ " from books where instr(media_type, ?) > 0 "
						+ " order by book_no asc, media_type desc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while (rs.next()) {
						Books books = new Books();
						books.setBookNo(rs.getInt("book_no"));
						books.setBookName(rs.getString("book_name"));
						books.setGroupNo(rs.getString("group_no"));
						books.setWriter(rs.getString("writer"));
						books.setPublishDate(rs.getString("publish_date"));
						books.setPublication(rs.getString("publication"));
						books.setMediaType(rs.getString("media_type"));
						books.setUseObject(rs.getString("use_object"));
						books.setCheckState(rs.getString("check_state"));
						bookList.add(books);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			break;
		// 이용대상
		case 7:
			if (searchOption2 == 1) { // 오름차순
				sql = "select book_no, book_name, group_no, writer, publish_date, publication, media_type, use_object, check_state"
						+ " from books where instr(use_object, ?) > 0 "
						+ " order by book_no asc, use_object asc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while (rs.next()) {
						Books books = new Books();
						books.setBookNo(rs.getInt("book_no"));
						books.setBookName(rs.getString("book_name"));
						books.setGroupNo(rs.getString("group_no"));
						books.setWriter(rs.getString("writer"));
						books.setPublishDate(rs.getString("publish_date"));
						books.setPublication(rs.getString("publication"));
						books.setMediaType(rs.getString("media_type"));
						books.setUseObject(rs.getString("use_object"));
						books.setCheckState(rs.getString("check_state"));
						bookList.add(books);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else { // 내림차순
				sql = "select book_no, book_name, group_no, writer, publish_date, publication, media_type, use_object, check_state"
						+ " from books where instr(use_object, ?) > 0 "
						+ " order by book_no asc, use_object desc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while (rs.next()) {
						Books books = new Books();
						books.setBookNo(rs.getInt("book_no"));
						books.setBookName(rs.getString("book_name"));
						books.setGroupNo(rs.getString("group_no"));
						books.setWriter(rs.getString("writer"));
						books.setPublishDate(rs.getString("publish_date"));
						books.setPublication(rs.getString("publication"));
						books.setMediaType(rs.getString("media_type"));
						books.setUseObject(rs.getString("use_object"));
						books.setCheckState(rs.getString("check_state"));
						bookList.add(books);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			break;
		// 대출유무
		case 8:
			if (searchOption2 == 1) { // 오름차순
				sql = "select book_no, book_name, group_no, writer, publish_date, publication, media_type, use_object, check_state"
						+ " from books where instr(check_state, ?) > 0 "
						+ " order by book_no asc, check_state asc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while (rs.next()) {
						Books books = new Books();
						books.setBookNo(rs.getInt("book_no"));
						books.setBookName(rs.getString("book_name"));
						books.setGroupNo(rs.getString("group_no"));
						books.setWriter(rs.getString("writer"));
						books.setPublishDate(rs.getString("publish_date"));
						books.setPublication(rs.getString("publication"));
						books.setMediaType(rs.getString("media_type"));
						books.setUseObject(rs.getString("use_object"));
						books.setCheckState(rs.getString("check_state"));
						bookList.add(books);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else { // 내림차순
				sql = "select book_no, book_name, group_no, writer, publish_date, publication, media_type, use_object, check_state"
						+ " from books where instr(check_state, ?) > 0 "
						+ " order by book_no asc, check_state desc ";
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, searchData);
					rs = psmt.executeQuery();
					while (rs.next()) {
						Books books = new Books();
						books.setBookNo(rs.getInt("book_no"));
						books.setBookName(rs.getString("book_name"));
						books.setGroupNo(rs.getString("group_no"));
						books.setWriter(rs.getString("writer"));
						books.setPublishDate(rs.getString("publish_date"));
						books.setPublication(rs.getString("publication"));
						books.setMediaType(rs.getString("media_type"));
						books.setUseObject(rs.getString("use_object"));
						books.setCheckState(rs.getString("check_state"));
						bookList.add(books);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			break;
		}
		return bookList;
	}

	// ==== 회원탈퇴 기능 ==== 완성
	public boolean unjoin(String unjoinMemberId, String unjoinMemberPw) {
		conn = DAO.getConn();
		sql = "delete members where member_id = ? and member_pw = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, unjoinMemberId);
			psmt.setString(2, unjoinMemberPw);
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return false;
	}
	
	
	// ==== 내정보조회 ==== 완성
	public String searchMyInfo(String myId, String myPw) {
		conn = DAO.getConn();
		sql = "select name, member_id, member_pw, email, birth_date, join_date " + "from members where member_id = ? and member_pw = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, myId);
			psmt.setString(2, myPw);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String myInfo = "이름:" + rs.getString("name") + "\n" + "아이디:" + rs.getString("member_id") + "\n"
						+ "비밀번호:" + rs.getString("member_pw") + "\n" + "이메일:" + rs.getString("email") + "\n" + "생년월일:"
						+ rs.getDate("birth_date") + "\n" + "가입일자:" + rs.getDate("join_date");
				return myInfo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return null;
	}

	// ==== 내정보수정 ==== 완성
	public boolean updateInfo(String u_id, Members updateMember) {
		conn = DAO.getConn();
		sql = "update members set name = nvl(?, name) ";
		if (!updateMember.getMemberId().equals("")) {
			sql += ", member_id = nvl(?, member_id) ";
		}
		if (!updateMember.getMemberPw().equals("")) {
			sql += ", member_pw = nvl(?, member_pw) ";
		}
		if (!updateMember.getEmail().equals("")) {
			sql += ", email = nvl(?, email) ";
		}
		if (!updateMember.getBirthDate().equals("")) {
			sql += ", birth_date = nvl(TO_DATE(?, 'yy/mm/dd'), birth_date) ";
		}
		sql += "where member_id = ?";
		try {
			int cnt = 1;
			psmt = conn.prepareStatement(sql);
			psmt.setString(cnt++, updateMember.getName());			
			if (!updateMember.getMemberId().equals("")) {
				psmt.setString(cnt++, updateMember.getMemberId());
			}
			if (!updateMember.getMemberPw().equals("")) {
				psmt.setString(cnt++, updateMember.getMemberPw());
			}
			if (!updateMember.getEmail().equals("")) {
				psmt.setString(cnt++, updateMember.getEmail());
			}
			if (!updateMember.getBirthDate().equals("")) {
				psmt.setString(cnt++, updateMember.getBirthDate());
			}
			psmt.setString(cnt++, u_id);
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return false;
	}

	// ==== 대출목록조회 기능 ==== 완성
	public List<CheckLists> checkLists(String myId) {
		conn = DAO.getConn();
		List<CheckLists> bookList = new ArrayList<>();
		sql = "select book_name, check_date, return_date from checklists where member_id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, myId);
			rs = psmt.executeQuery();
			while(rs.next()) {
				CheckLists myCheckLists = new CheckLists();
				myCheckLists.setBookName(rs.getString("book_name"));
				myCheckLists.setCheckDate(rs.getString("check_date"));
				myCheckLists.setReturnDate(rs.getString("return_date"));
				bookList.add(myCheckLists);
				//return bookList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return bookList;
	}

	
	// ==== 도서대출 기능 ==== 완성
	public boolean check(String bookName, String myId) {
		conn = DAO.getConn();
		String sql1, sql2, sql3;
		sql1 = "select member_id from members where member_id = ?";
		sql2 = "update books set check_state = '대출중' where book_name = ? and check_state = '대출가능' ";
		sql3 = "insert into checklists (check_no, member_id, book_name, check_date) values (check_no_seq.nextval, ?, ?, sysdate) ";
		try {
			psmt = conn.prepareStatement(sql1);
			psmt.setString(1, myId);
			rs = psmt.executeQuery();
			if(rs.next()) {
				psmt = conn.prepareStatement(sql2);
				psmt.setString(1, bookName);
				int r1 = psmt.executeUpdate();
				if(r1 > 0) {
					psmt = conn.prepareStatement(sql3);
					psmt.setString(1, myId);
					psmt.setString(2, bookName);
					int r2 = psmt.executeUpdate();
					if(r2 > 0) {
						return true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return false;
	}

	// ==== 도서반납 기능 ==== 완성
	public boolean uncheck(String bookName, String myId2) {
		conn = DAO.getConn();
		String sql1, sql2, sql3;
		sql1 = "select member_id from members where member_id = ?";
		sql2 = "update books set check_state = '대출가능' where book_name = ? and check_state = '대출중' ";
		sql3 = "update checklists set return_date = sysdate where book_name = ?";
		try {
			psmt = conn.prepareStatement(sql1);
			psmt.setString(1, myId2);
			rs = psmt.executeQuery();
			if(rs.next()) {
				psmt = conn.prepareStatement(sql2);
				psmt.setString(1, bookName);
				int r1 = psmt.executeUpdate();
				if(r1 > 0) {
					psmt = conn.prepareStatement(sql3);
					psmt.setString(1, bookName);
					int r2 = psmt.executeUpdate();
					if(r2 > 0) {
						return true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return false;
	}

	// 종료
}
