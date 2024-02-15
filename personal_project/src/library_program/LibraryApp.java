//도서관프로그램 실행 클래스
package library_program;

import java.util.Scanner;

public class LibraryApp {
	public static void main(String[] args) {
		//기능클래스 객체 생성
		LibraryDAO ldao = new LibraryDAO();
		//스캐너
		Scanner scn = new Scanner(System.in);
		//
		boolean run = true;
	
		
		while(run) { //관리자 또는 사용자 입장
			// ==== 로그인 ====
			System.out.println("========================");
			System.out.println("=         로그인        =");
			System.out.println("========================");
			System.out.print("아이디를 입력하세요:");
			String u_id = scn.nextLine();
			System.out.print("비밀번호를 입력하세요:");
			String u_pw = scn.nextLine();
			//로그인기능 호출
			if(ldao.login(u_id, u_pw)) {
				System.out.println("로그인 성공");
				run = false;
			}
			else {
				System.out.println("로그인 실패");
			}
			
			
		}
	}
}
