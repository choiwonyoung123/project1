//대출목록 클래스
package library_program;

import java.util.Date;

public class CheckLists {
	private int checkNo;
	private int memberNo;
	private String book_name;
	private Date checkDate;
	private Date returnDate;
	private Date remainDate;
	
	
	public int getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(int checkNo) {
		this.checkNo = checkNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
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
	public Date getRemainDate() {
		return remainDate;
	}
	public void setRemainDate(Date remainDate) {
		this.remainDate = remainDate;
	}
}
