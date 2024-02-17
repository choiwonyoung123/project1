package library_program;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	//생성자 > Connection 타입의 conn 인스턴스
		public static Connection conn;
		//함수 > Connection 타입의 getConn 함수
		public static Connection getConn() {
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				//데이터베이스 URL, 아이디, 비밀번호 입력 
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##yedam", "1234");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return conn;
		}
}
