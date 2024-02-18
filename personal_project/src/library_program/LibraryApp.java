//도서관프로그램 실행 클래스
package library_program;


import java.util.Scanner;

public class LibraryApp {
	public static void main(String[] args) {
		LibraryDAO ldao = new LibraryDAO();
		Scanner scn = new Scanner(System.in);
		int account = 1; // 1 - 관리자계정, 2 - 사용자계정
		
		boolean run1 = true;
		// 전체프로그램
		while(run1) {
			// 로그인 사용자 아이디, 비밀번호
			boolean run2 = true;
			// 로그인 or 회원가입
			while(run2) { 
				System.out.println("============== [대구도서관] ==============");
				System.out.println("     1.로그인     2.회원가입     3.종료      ");
				System.out.println("=======================================");
				System.out.print("메뉴(1~3) 선택:");
				int log_join_check = scn.nextInt();
				scn.nextLine();
				if(log_join_check == 1 || log_join_check == 2) {
					if(log_join_check == 1) { //로그인	
						System.out.println("[로그인]");
						System.out.print("1.아이디:");
						String u_id = scn.nextLine();
						System.out.print("2.비밀번호:");
						String u_pw = scn.nextLine();
						//로그인기능 호출
						if(ldao.login(u_id, u_pw)) {
							if(u_id.equals("MANAGER") && u_pw.equals("MANAGER")) {
								System.out.println("관리자 모드");
								System.out.println("");
								System.out.println("");
								run2 = false;
								//관리자 기능
							}
							else {						
								System.out.println("로그인 성공");
								System.out.println("");
								System.out.println("");
								account++;
								run2 = false;
								//사용자 기능
							}
						}
						else {
							System.out.println("로그인에 실패했습니다.");
							System.out.println("");
							System.out.println("");
						}
					}
					else if(log_join_check == 2) { //회원가입 > 로그인
						System.out.println("[회원가입]");
						System.out.print("1.이름:");
						String u_name = scn.nextLine();
						System.out.print("2.아이디(15자이내):");
						String j_id = scn.nextLine();
						System.out.print("3.비밀번호(15자이내):");
						String j_pw = scn.nextLine();
						System.out.print("4.이메일:");
						String u_email = scn.nextLine();
						System.out.print("5.생년월일(yy/mm/dd):");
						String u_birthDate = scn.nextLine();
						//회원가입기능 호출
						if(ldao.join(u_name, j_id, j_pw, u_email, u_birthDate)) {
							System.out.println("회원가입을 완료했습니다.");
							System.out.println("");
							System.out.println("");
						}
						else {
							System.out.println("회원가입에 실패했습니다.");
							System.out.println("");
							System.out.println("");
						}
					}
				}
				else if(log_join_check == 3) { // 종료
					System.out.println("프로그램을 종료합니다.");
					return;
				}
				else {
					System.out.println("번호가 올바르지 않습니다.");
					System.out.println("");
					System.out.println("");
				}
			}
			// 관리자계정 
			if(account == 1) {
				boolean run3 = true;
				while(run3) {
					System.out.println("========== [관리자 메뉴] ==========");
					System.out.println("   1.도서등록  2.도서수정  3.도서삭제   ");
					System.out.println("   4.회원등록  5.회원삭제  6.종료      ");
					System.out.println("================================");
					System.out.print("메뉴(1~6) 선택:");
					int manager_menu_check = scn.nextInt();
					scn.nextLine();
					switch(manager_menu_check) {
					// 도서등록
					case 1:
						System.out.println("[도서등록]");
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
						if(ldao.addBook(b_name, g_no, b_writer, publishDate, publication, mediaType, useObject, checkState)) {
							System.out.println("도서등록을 완료했습니다.");
							System.out.println("");
							System.out.println("");
						}
						else {
							System.out.println("도서등록에 실패했습니다.");
							System.out.println("");
							System.out.println("");
						}
						break;
					// 도서수정
					case 2:
						System.out.println("[도서수정]");
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
							System.out.println("도서수정을 완료했습니다.");
							System.out.println("");
							System.out.println("");
						}
						else {
							System.out.println("도서수정에 실패했습니다.");
							System.out.println("");
							System.out.println("");
						}
						break;
					// 도서삭제
					case 3:
						System.out.println("[도서삭제]");
						System.out.print("삭제할 도서명:");
						String deleteBookName = scn.nextLine();
						if(ldao.deleteBook(deleteBookName)){
							System.out.println("도서삭제를 완료했습니다.");
							System.out.println("");
							System.out.println("");
						}
						else {
							System.out.println("도서삭제에 실패했습니다.");
							System.out.println("");
							System.out.println("");
						}
						break;
					// 회원등록
					case 4:
						System.out.println("[회원등록]");
						System.out.print("1.이름:");
						String u_name = scn.nextLine();
						System.out.print("2.아이디(15자이내):");
						String j_id = scn.nextLine();
						System.out.print("3.비밀번호(15자이내):");
						String j_pw = scn.nextLine();
						System.out.print("4.이메일:");
						String u_email = scn.nextLine();
						System.out.print("5.생년월일(yy/mm/dd):");
						String u_birthDate = scn.nextLine();
						if(ldao.addMember(u_name, j_id, j_pw, u_email, u_birthDate)) {
							System.out.println("회원등록을 완료했습니다.");
							System.out.println("");
							System.out.println("");
						}
						else {
							System.out.println("회원등록에 실패했습니다.");
							System.out.println("");
							System.out.println("");
						}
						break;
					// 회원삭제
					case 5:
						System.out.println("[회원삭제]");
						System.out.print("삭제할 회원 아이디:");
						String deleteMemberName = scn.nextLine();
						if(ldao.deleteMember(deleteMemberName)) {
							System.out.println("회원삭제를 완료했습니다.");
							System.out.println("");
							System.out.println("");
						}
						else {
							System.out.println("회원삭제에 실패했습니다.");
							System.out.println("");
							System.out.println("");
						}
						break;
					// 종료
					case 6:
						System.out.println("프로그램을 종료합니다.");
						return;
					}		
				}
			}
			//사용자계정 기능들
			else if(account == 2) {
				boolean run4 = true;
				while(run4) {
					System.out.println("========== [사용자 메뉴] ==========");
					System.out.println("   1.도서검색  2.도서대출  3.도서반납   ");
					System.out.println("   4.대출목록  5.내정보조회 6.내정보수정 ");
					System.out.println("   7.회원탈퇴  8.종료               ");
					System.out.println("================================");
					System.out.print("메뉴(1~7) 선택:");
					int user_menu_check = scn.nextInt();
					scn.nextLine();
					switch(user_menu_check) {
					// 도서검색
					case 1:
						System.out.println("[도서검색]");
						System.out.println("---------- [검색조건] ----------");
						System.out.println("  1.도서명   2.분류번호  3.저자     ");
						System.out.println("  4.출판년도  5.출판사   6.매체형태  ");
						System.out.println("  7.이용대상  8.대출유무  9.도서위치  ");
						System.out.println("---------- [정렬조건] ----------");
						System.out.println("      1.오름차순   2.내림차순      ");
						System.out.println("------------------------------");
						System.out.print("검색조건(1~9) 선택:");
						int searchOption1 = scn.nextInt();
						scn.nextLine();
						System.out.print("검색할 값 입력:");
						String searchData = scn.nextLine(); 
						System.out.print("정렬조건(1~2) 선택:");
						int searchOption2 = scn.nextInt();
						scn.nextLine();
						System.out.println("[검색결과]");
						if(ldao.searchBook(searchOption1, searchData, searchOption2) != null) {							
							System.out.println(ldao.searchBook(searchOption1, searchData, searchOption2));
							System.out.println("");
							System.out.println("");
						}
						else {
							System.out.println("검색결과가 없습니다.");
							System.out.println("");
							System.out.println("");
						}
						break;
					// 도서대출
					case 2:
						System.out.println("[도서대출]");
						System.out.print("아이디 입력:");
						String myId = scn.nextLine();
						System.out.print("대출할 도서명 입력:");
						String checkBookName = scn.nextLine();
						if(ldao.check(checkBookName, myId)) {
							System.out.println("도서대출을 완료했습니다.");
							System.out.println("");
							System.out.println("");
						}
						else {
							System.out.println("도서대출에 실패했습니다.");
							System.out.println("");
							System.out.println("");
						}
						break;
					// 도서반납
					case 3:
						System.out.println("[도서반납]");
						System.out.print("아이디 입력:");
						String myId2 = scn.nextLine();
						System.out.print("반납할 도서명 입력:");
						String uncheckBookName = scn.nextLine();
						if(ldao.uncheck(uncheckBookName, myId2)) {
							System.out.println("도서반납을 완료했습니다.");
							System.out.println("");
							System.out.println("");
						}
						else {
							System.out.println("도서반납에 실패했습니다.");
							System.out.println("");
							System.out.println("");
						}
						break;
					// 대출목록조회
					case 4:
						System.out.println("[대출목록]");
						System.out.print("조회할 아이디 입력:");
						String myId3 = scn.nextLine();
						System.out.println(ldao.checkLists(myId3));
						System.out.println("");
						System.out.println("");
						break;
					// 내정보조회
					case 5:
						System.out.println("[내정보]");
						System.out.print("1.아이디 입력:");
						String myId4 = scn.nextLine();
						System.out.print("2.비밀번호 입력:");
						String myPw = scn.nextLine();
						System.out.println(ldao.searchMyInfo(myId4, myPw));
						System.out.println("");
						System.out.println("");
						break;
					// 내정보수정
					case 6:
						System.out.println("[내정보수정]");
						System.out.print("본인 아이디 입력:");
						String u_id2 = scn.nextLine();
						System.out.print("1.이름 수정:");
						String updateName = scn.nextLine();
						System.out.print("2.아이디(15자이내) 수정:");
						String updateMemberId = scn.nextLine();
						System.out.print("3.비밀번호(15자이내) 수정:");
						String updateMemberPw = scn.nextLine();
						System.out.print("4.이메일 수정:");
						String updateEmail = scn.nextLine();
						System.out.print("5.생년월일(yy/mm/dd) 수정:");
						String updateBirthDate = scn.nextLine();
						Members updateMember = new Members();
						updateMember.setName(updateName);
						updateMember.setMemberId(updateMemberId);
						updateMember.setMemberPw(updateMemberPw);
						updateMember.setEmail(updateEmail);
						updateMember.setBirthDate(updateBirthDate);
						if(ldao.updateInfo(u_id2, updateMember)) {
							System.out.println("내정보수정을 완료했습니다.");
							System.out.println("");
							System.out.println("");
						}
						else {
							System.out.println("내정보수정에 실패했습니다.");
							System.out.println("");
							System.out.println("");
						}
						break;
					// 회원탈퇴
					case 7:
						System.out.print("탈퇴할 아이디 입력:");
						String unjoinMemberId = scn.nextLine();
						System.out.print("탈퇴할 비밀번호 입력:");
						String unjoinMemberPw = scn.nextLine();
						if(ldao.unjoin(unjoinMemberId, unjoinMemberPw)) {
							System.out.println("회원탈퇴를 완료했습니다.");
							System.out.println("");
							System.out.println("");
						}
						else {
							System.out.println("회원탈퇴에 실패했습니다.");
							System.out.println("");
							System.out.println("");
						}
						break;
					// 종료
					case 8:
						System.out.println("프로그램을 종료합니다.");
						return;
					}	
				}
			} //사용자계정				
		} //while(run) end
	}
}
