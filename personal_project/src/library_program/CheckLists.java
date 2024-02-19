//대출목록 클래스
package library_program;

import java.util.Date;

public class CheckLists {
	private int checkNo;
	private String memberId;
	private String book_name;
	private Date checkDate;
	private Date returnDate;
	
	
	public int getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(int checkNo) {
		this.checkNo = checkNo;
	}
	public String getMemberNo() {
		return memberId;
	}
	public void setMemberNo(String memberId) {
		this.memberId = memberId;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
}
