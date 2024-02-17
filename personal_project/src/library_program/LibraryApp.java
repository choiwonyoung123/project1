//도서관프로그램 실행 클래스
package library_program;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
	public static void main(String[] args) {
		LibraryDAO ldao = new LibraryDAO();
		Scanner scn = new Scanner(System.in);
		int account = 1; //1=관리자, 2=사용자
		
		boolean run = true;
		while(run) { //while(run) start
			//==== 로그인 or 회원가입 ====
			System.out.println("========================");
			System.out.println("1.로그인  2.회원가입  3.종료 ");
			System.out.println("========================");
			System.out.print("선택:");
			int log_join_check = scn.nextInt();
			scn.nextLine();
			if(log_join_check == 1 || log_join_check == 2) {
				if(log_join_check == 1) { //로그인	
					System.out.println(" << 1.로그인 >> ");
					System.out.print("아이디:");
					String u_id = scn.nextLine();
					System.out.print("비밀번호:");
					String u_pw = scn.nextLine();
					//로그인기능 호출
					if(ldao.login(u_id, u_pw)) {
						if(u_id.equals("MANAGER") && u_pw.equals("MANAGER")) {
							System.out.println("관리자모드 입장");
							//관리자 기능
						}
						else {						
							System.out.println("로그인 성공");
							account++;
							//사용자 기능
						}
					}
					else {
						System.out.println("로그인 실패");
					}
				}
				else if(log_join_check == 2) { //회원가입 > 로그인
					System.out.println(" << 2.회원가입 >> ");
					System.out.print("1.이름:");
					String u_name = scn.nextLine();
					System.out.print("2.아이디(15자이내):");
					String u_id = scn.nextLine();
					System.out.print("3.비밀번호(15자이내):");
					String u_pw = scn.nextLine();
					System.out.print("4.이메일:");
					String u_email = scn.nextLine();
					System.out.print("5.생년월일(yy/mm/dd):");
					String u_birthDate = scn.nextLine();
					//회원가입기능 호출
					if(ldao.join(u_name, u_id, u_pw, u_email, u_birthDate)) {
						System.out.println("회원가입 완료");
					}
					else {
						System.out.println("회원가입 실패");
					}
				}
				//관리자계정 기능들
				if(account == 1) {
					boolean run3 = true;
					while(run3) {
						System.out.println("===============메뉴================");
						System.out.println("   1.도서등록  2.도서수정  3.도서삭제   ");
						System.out.println("   4.회원등록  5.회원삭제  6.종료      ");
						System.out.println("==================================");
						System.out.print("선택:");
						int manager_menu_check = scn.nextInt();
						scn.nextLine();
						switch(manager_menu_check) {
						//도서등록
						case 1:
							System.out.print("1.도서명:");
							String b_name = scn.nextLine();
							System.out.print("2.분류번호:");
							String g_no = scn.nextLine();
							System.out.print("3.저자:");
							String b_writer = scn.nextLine();
							System.out.print("4.출판년도:");
							String publishDate = scn.nextLine();
							System.out.print("5.출판사:");
							String publication = scn.nextLine();
							System.out.print("6.매체형태:");
							String mediaType = scn.nextLine();
							System.out.print("7.이용대상:");
							String useObject = scn.nextLine();
							System.out.print("8.대출상태:");
							String checkState = scn.nextLine();
							//도서등록기능 호출
							if(ldao.addBook(b_name, g_no, b_writer, publishDate, publication, mediaType, useObject, checkState)) {
								System.out.println("도서등록 완료");
							}
							else {
								System.out.println("도서등록 실패");
							}
							break;
						//도서수정
						case 2:
							System.out.print("수정할 도서명:");
							String updateBookName = scn.nextLine();
							System.out.print("1.도서명 수정:");
							String b_name2 = scn.nextLine();
							System.out.print("2.분류번호 수정:");
							String g_no2 = scn.nextLine();
							System.out.print("3.저자 수정:");
							String b_writer2 = scn.nextLine();
							System.out.print("4.출판년도 수정:");
							String publishDate2 = scn.nextLine();
							System.out.print("5.출판사 수정:");
							String publication2 = scn.nextLine();
							System.out.print("6.매체형태 수정:");
							String mediaType2 = scn.nextLine();
							System.out.print("7.이용대상 수정:");
							String useObject2 = scn.nextLine();
							System.out.print("8.대출상태 수정:");
							String checkState2 = scn.nextLine();
							Books updateBook = new Books();
							updateBook.setBookName(b_name2);
							updateBook.setGroupNo(g_no2);
							updateBook.setWriter(b_writer2);
							updateBook.setPublishDate(publishDate2);
							updateBook.setPublication(publication2);
							updateBook.setMediaType(mediaType2);
							updateBook.setUseObject(useObject2);
							updateBook.setCheckState(checkState2);
							if(ldao.updateBook(updateBook, updateBookName)) {
								System.out.println("수정 완료");
							}
							else {
								System.out.println("수정 실패");
							}
							break;
						//도서삭제
						case 3:
							System.out.print("삭제할 도서명:");
							String deleteBookName = scn.nextLine();
							if(ldao.deleteBook(deleteBookName)){
								System.out.println("삭제 완료");
							}
							else {
								System.out.println("삭제 실패");
							}
							break;
						//회원등록
						case 4:
							System.out.println(" << 회원 등록 >> ");
							System.out.print("1.이름:");
							String u_name = scn.nextLine();
							System.out.print("2.아이디(15자이내):");
							String u_id = scn.nextLine();
							System.out.print("3.비밀번호(15자이내):");
							String u_pw = scn.nextLine();
							System.out.print("4.이메일:");
							String u_email = scn.nextLine();
							System.out.print("5.생년월일(yy/mm/dd):");
							String u_birthDate = scn.nextLine();
							if(ldao.addMember(u_name, u_id, u_pw, u_email, u_birthDate)) {
								System.out.println("회원 등록 완료");
							}
							else {
								System.out.println("회원 등록 실패");
							}
							break;
						//회원삭제
						case 5:
							System.out.print("삭제할 회원 아이디:");
							String deleteMemberName = scn.nextLine();
							if(ldao.deleteMember(deleteMemberName)) {
								System.out.println("회원 삭제 완료");
							}
							else {
								System.out.println("회원 삭제 실패");
							}
							break;
						//종료
						case 6:
							System.out.println("종료");
							break;
						}		
					}
				}
				//사용자계정 기능들
				else if(account == 2) {
					boolean run4 = true;
					while(run4) {
						System.out.println("===============메뉴================");
						System.out.println("1. 도 서 검 색    2. 도 서 대 출      ");
						System.out.println("3. 도 서 반 납    4. 대 출 목 록 조 회 ");
						System.out.println("5. 내 정 보 조 회  6. 회 원 탈 퇴      ");
						System.out.println("7. 종 료                            ");
						System.out.println("==================================");
						System.out.print("선택:");
						int user_menu_check = scn.nextInt();
						scn.nextLine();
						switch(user_menu_check) {
						//도서검색
						case 1:
							List<Books> bookList = new ArrayList<>();
							System.out.println("===============검색 조건===============");
							System.out.println("    1.도서명    2.분류번호    3.저자      ");
							System.out.println("    4.출판년도   5.출판사     6.매체형태   ");
							System.out.println("    7.이용대상   8.대출유무    9.도서위치   ");
							System.out.println("=====================================");
							System.out.print("선택:");
							int searchOption1 = scn.nextInt();
							scn.nextLine();
							System.out.print("검색할 값 입력:");
							String searchData = scn.nextLine(); 
							System.out.println("===============정렬 조건===============");
							System.out.println("        1.오름차순      2.내림차순       ");
							System.out.println("=====================================");
							System.out.print("선택:");
							int searchOption2 = scn.nextInt();
							scn.nextLine();
							System.out.println("=======검======색======결======과=======");
							ldao.searchBook(searchOption1, searchData, searchOption2);
							//System.out.println(bookList.getGroupName());
							break;
						//도서대출
						case 2:
							System.out.println();
							break;
						//도서반납
						case 3:
							break;
						//대출목록조회
						case 4:
							System.out.print("조회할 회원아이디 입력:");
							String searchMemberId = scn.nextLine();
							break;
						//내정보조회 > 아이디수정, 비밀번호수정
						case 5:
							
							break;
						//회원탈퇴
						case 6:
							System.out.print("탈퇴할 회원아이디 입력:");
							String unjoinMemberId = scn.nextLine();
							if(ldao.unjoin(unjoinMemberId)) {
								System.out.println("탈퇴 완료");
							}
							else {
								System.out.println("탈퇴 실패");
							}
							break;
						//종료
						case 7:
							break;
						}	
					}
				}				
			}
			else if(log_join_check == 3) { 
		    	run = false;
		    }
			else {
				System.out.println("번호가 올바르지 않습니다.");
			}
		} //while(run) end
		System.out.println("end of prog...");
	}
}
