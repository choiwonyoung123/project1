//도서 클래스
package library_program;

import java.util.Date;

public class Books {
	private int bookNo; //등록번호
	private String bookName; //도서명
	private String groupNo; //분류번호
	private String writer; //저자
	private String publishDate; //출판년도
	private String publication; //발행처
	private String mediaType; //매체형태
	private String useObject; //이용대상
	private String checkState; //대출상태
	
	
	public Books(int bookNo, String bookName, String groupNo, String writer, String publishDate, String publication,
			String mediaType, String useObject, String checkState) {
		super();
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.groupNo = groupNo;
		this.writer = writer;
		this.publishDate = publishDate;
		this.publication = publication;
		this.mediaType = mediaType;
		this.useObject = useObject;
		this.checkState = checkState;
	}
	public Books(String bookName, String groupNo, String writer, String publishDate, String publication,
			String mediaType, String useObject, String checkState) {
		super();
		this.bookName = bookName;
		this.groupNo = groupNo;
		this.writer = writer;
		this.publishDate = publishDate;
		this.publication = publication;
		this.mediaType = mediaType;
		this.useObject = useObject;
		this.checkState = checkState;
	}
	public Books() {
		super();
	}
	
	
	
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getPublication() {
		return publication;
	}
	public void setPublication(String publication) {
		this.publication = publication;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getUseObject() {
		return useObject;
	}
	public void setUseObject(String useObject) {
		this.useObject = useObject;
	}
	public String getCheckState() {
		return checkState;
	}
	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}
}
