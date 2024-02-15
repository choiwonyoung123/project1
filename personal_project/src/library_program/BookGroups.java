//도서분류 클래스
package library_program;

public class BookGroups {
	private String groupNo; //분류번호
	private String groupName; //분류명
	private String book_location; //소장위치
	private String book_own; //소장처
	
	
	public String getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getBook_location() {
		return book_location;
	}
	public void setBook_location(String book_location) {
		this.book_location = book_location;
	}
	public String getBook_own() {
		return book_own;
	}
	public void setBook_own(String book_own) {
		this.book_own = book_own;
	}
}
