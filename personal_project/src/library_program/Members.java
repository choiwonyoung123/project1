//회원 클래스
package library_program;

import java.util.Date;

public class Members {
	private int memberNo;
	private String name;
	private String memberId;
	private String memberPw;
	private String email;
	private Date birthDate;
	private Date joinDate;
	
	
	public Members(int memberNo, String name, String memberId, String memberPw, String email, Date birthDate,
			Date joinDate) {
		super();
		this.memberNo = memberNo;
		this.name = name;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.email = email;
		this.birthDate = birthDate;
		this.joinDate = joinDate;
	}
	public Members(String memberId, String memberPw) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
	}
	
	public Members() {
		super();
	}
	
	
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
}
