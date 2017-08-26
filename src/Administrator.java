
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/*
 * @ myZhou, qzDong
 * This is the admin user.
 */
public class Administrator {

	LibConnection conn;
	String id;

	final static int BORROW_TIME = 30;
	final static int MAX_BORROW_NUMBER = 8;

	public Administrator(LibConnection conn) {
		this.conn = conn;
	}

	public Administrator(LibConnection conn, String id) {
		this.conn = conn;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LibConnection getConn() {
		return conn;
	}

	public void setConn(LibConnection conn) {
		this.conn = conn;
	}

	public boolean addUser(String card_id, String user_password, String user_id, String user_name, Date card_date,
			int card_time, String user_phone) {
		String sql = "insert into user(card_id,user_password,user_id,user_name,card_date,card_time,user_phone) values (?,?,?,?,?,?,?)";
		boolean re = false;
		int n = -1;
		try {

			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, card_id);
			pstatement.setString(2, user_password);
			pstatement.setString(3, user_id);
			pstatement.setString(4, user_name);
			pstatement.setDate(5, card_date);
			pstatement.setInt(6, card_time);
			pstatement.setString(7, user_phone);

			n = pstatement.executeUpdate();
			re = true;

		} catch (Exception ex) {
			re = false;
			ex.printStackTrace();
		}

		if (re && n >= 0) {
			System.out.println("successfully add user with info" + n);
		} else {
			System.out.println("fail to add user with info" + n);
		}
		return re;
	}

	public boolean removeUser(String card_id) {
		String sql = "delete from user where card_id=(?)";

		boolean re = false;
		int n = -1;
		try {

			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, card_id);

			n = pstatement.executeUpdate();
			re = true;

		} catch (Exception ex) {
			re = false;
			ex.printStackTrace();
		}

		if (re && n >= 0) {
			System.out.println("successfully delete with info" + n);
		} else {
			System.out.println("fail to delete with info" + n);
		}

		return re;
	}

	public boolean changeUserPhone(String card_id, String user_phone) {
		String sql = "update user set user_phone=? where card_id=(?)";
		boolean re = false;
		int n = -1;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, user_phone);
			pstatement.setString(2, card_id);

			n = pstatement.executeUpdate();
			re = true;
		} catch (Exception ex) {
			re = false;
			ex.printStackTrace();
		}

		if (re && n >= 0) {
			System.out.println("successfully update user phone with info" + n);
		} else {
			System.out.println("fail to update user phone with info" + n);
		}
		return re;
	}

	private boolean addToBorrow(String card_id, String book_id, Date borrow_date, int borrow_time, int borrow_times,
			String title_name) {
		String sql = "insert into borrow(card_id,book_id,borrow_date,borrow_time,borrow_times,title_name) values (?,?,?,?,?,?)";
		boolean re = false;
		int n = -1;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, card_id);
			pstatement.setString(2, book_id);
			pstatement.setDate(3, borrow_date);
			pstatement.setInt(4, borrow_time);
			pstatement.setInt(5, borrow_times);
			pstatement.setString(6, title_name);

			n = pstatement.executeUpdate();
			re = true;

		} catch (Exception ex) {
			re = false;
			ex.printStackTrace();
		}

		if (re && n >= 0) {
			System.out.println("successfully add new borrow with" + n);
		} else {
			System.out.println("fail to add new borrow with" + n);
		}
		return re;
	}

	private boolean addToBook(String book_id, String title_id, Date storage_date, String operator_id) {
		String sql = "insert into book(book_id,title_id,storage_date,operator_id,book_state) values (?,?,?,?,?)";
		boolean re = false;
		int n = -1;
		try {

			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, book_id);
			pstatement.setString(2, title_id);
			pstatement.setDate(3, storage_date);
			pstatement.setString(4, this.getId());
			pstatement.setString(5, "I");// 此时该书册存在于馆内

			n = pstatement.executeUpdate();
			re = true;

		} catch (Exception ex) {
			re = false;
			ex.printStackTrace();
		}

		if (re && n >= 0) {
			System.out.println("向book表中添加一行，涉及book表" + n + "行");
		} else {
			System.out.println("向book表中添加一行，涉及book表" + n + "行");
		}
		return re;
	}

	private boolean addToHistory(String card_id, String book_id, Date borrow_date, Date return_date) {
		String sql = "insert into history(card_id,book_id,borrow_date,return_date) values (?,?,?,?)";
		boolean re = false;
		int n = -1;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, card_id);
			pstatement.setString(2, book_id);
			pstatement.setDate(3, borrow_date);
			pstatement.setDate(4, return_date);

			n = pstatement.executeUpdate();
			re = true;

		} catch (Exception ex) {
			re = false;
			ex.printStackTrace();
		}

		if (re && n >= 0) {
			System.out.println("get borrow history" + n);
		} else {
			System.out.println("fail to get borrow history" + n);
		}
		return re;
	}

	private boolean addToTitle(String isbn, String title_name, String title_press, Date title_date, double title_price,
			int title_booknumber, int title_currentnumber) {
		boolean re = false;
		String sql = "insert into title(isbn,title_name,title_press,title_date,title_price,title_booknumber,title_currentnumber) values (?,?,?,?,?,?,?)";
		int n = -1;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, isbn);
			pstatement.setString(2, title_name);
			pstatement.setString(3, title_press);
			pstatement.setDate(4, title_date);
			pstatement.setString(5, Double.toString(title_price));
			pstatement.setInt(6, title_booknumber);
			pstatement.setInt(7, title_currentnumber);

			n = pstatement.executeUpdate();
			re = true;
		} catch (Exception ex) {
			re = false;
			ex.printStackTrace();
		}

		if (re && n >= 0) {
			System.out.println("modify book info" + n);
		} else {
			System.out.println("fail to modify info" + n);
		}
		re = re && addToMessageByRecommend(title_name);
		System.out.println("addtomessagebyrecommend" + re);
		re = re && removeFromRecommend(title_name);
		System.out.println("removefromrecommend" + re);
		return re;
	}

	public boolean addOneBook(String book_id, Date storage_date, String operator_id, String isbn, String title_name,
			String title_press, Date title_date, double title_price, int title_booknumber) {
		boolean re = false;
		try {
			re = addToBook(book_id, isbn, storage_date, this.getId());
			System.out.println("addtobook" + re);

			if (isTitleExist(isbn)) {
				re = re && addOneBookToTitle(isbn);
				System.out.println("addonebooktotitle" + re);
			} else {
				re = re && addToTitle(isbn, title_name, title_press, title_date, title_price, title_booknumber,
						title_booknumber);
				System.out.println("addtotitle" + re);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}

	private boolean isTitleExist(String isbn) {
		String sql = "select * from title where isbn = ?";
		boolean re = false;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, isbn);
			ResultSet rs = pstatement.executeQuery();

			if (rs.next()) {
				re = true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			re = false;
		}

		if (re) {
			System.out.println("get info");
		} else {
			System.out.println("fail finding info");
		}
		return re;
	}

	private boolean isAuthorExist(String isbn, String author) {
		String sql = "select * from author where title_id = ? and author_name=?";
		boolean re = false;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, isbn);
			pstatement.setString(2, author);
			ResultSet rs = pstatement.executeQuery();

			if (rs.next()) {
				re = true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			re = false;
		}

		if (re) {
			System.out.println("get author");
		} else {
			System.out.println("fail to get author");
		}
		return re;
	}

	private boolean isKeywordExist(String isbn, String keyword) {
		String sql = "select * from keyword where title_id = ? and key_word=?";
		boolean re = false;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, isbn);
			pstatement.setString(2, keyword);
			ResultSet rs = pstatement.executeQuery();

			if (rs.next()) {
				re = true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			re = false;
		}

		if (re) {
			System.out.println("get key");
		} else {
			System.out.println("didn't get key");
		}
		return re;
	}

	private boolean addOneBookToTitle(String isbn) {
		String sql = "update title set title_booknumber=title_booknumber+1,title_currentnumber=title_currentnumber+1 where isbn=(?)";
		boolean re = false;
		int n = -1;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, isbn);

			n = pstatement.executeUpdate();
			re = true;
		} catch (Exception ex) {
			re = false;
			ex.printStackTrace();
		}

		if (re && n >= 0) {
			System.out.println("new book number" + n);
		} else {
			System.out.println("fail with" + n);
		}
		return re;
	}

	public boolean addAuthor(String title_id, String author) {
		boolean re = true;
		if (!isAuthorExist(title_id, author)) {
			String sql = "insert into author(title_id,author_name) values (?,?)";
			int n = -1;
			try {
				PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
				pstatement.setString(1, title_id);
				pstatement.setString(2, author);

				n = pstatement.executeUpdate();
				re = true;

			} catch (Exception ex) {
				re = false;
				ex.printStackTrace();
			}

			if (re && n >= 0) {
				System.out.println("new author with" + n);
			} else {
				System.out.println("fail to add" + n);
			}
			return re;
		} else {
			// 该书的作者已经存在
			return true;
		}

	}

	public boolean addKeyWord(String title_id, String keyword) {
		boolean re = true;
		if (!isKeywordExist(title_id, keyword)) {
			String sql = "insert into keyword(title_id,key_word) values (?,?)";
			int n = -1;
			try {
				PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
				pstatement.setString(1, title_id);
				pstatement.setString(2, keyword);

				n = pstatement.executeUpdate();
			} catch (Exception ex) {
				re = false;
				ex.printStackTrace();
			}

			if (re && n >= 0) {
				System.out.println("new Keyword" + n);
			} else {
				System.out.println("fail new Keyword" + n);
			}
			return re;
		}
		return true;
	}

	public boolean isBookExist(String book_id) {
		String sql = "select * from book where book_id = ? ";
		boolean re = false;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, book_id);
			ResultSet rs = pstatement.executeQuery();

			if (rs.next()) {
				re = true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			re = false;
		}

		if (re) {
			System.out.println("Exist book");
		} else {
			System.out.println("Not exist");
		}
		return re;
	}

	private boolean removeFromBorrow(String card_id, String book_id) {
		String sql = "delete from borrow where card_id=(?) and book_id=(?)";
		boolean re = false;
		int n = -1;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, card_id);
			pstatement.setString(2, book_id);

			n = pstatement.executeUpdate();
			re = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			re = false;
		}

		if (re && n >= 0) {
			System.out.println("successfully delete" + n);
		} else {
			System.out.println("fail deleting" + n);
		}
		return re;
	}

	private boolean removeFromBook(String book_id) {
		String sql = "delete from book where book_id=(?)";
		boolean re = false;
		int n = -1;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, book_id);

			n = pstatement.executeUpdate();
			re = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			re = false;
		}

		if (re && n >= 0) {
			System.out.println("able to removw" + n);
		} else {
			System.out.println("fail to remove" + n);
		}
		return re;
	}

	public boolean isUserExist(String card_id) {
		String sql = "select * from user where card_id = ? ";
		boolean re = false;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, card_id);
			ResultSet rs = pstatement.executeQuery();

			if (rs.next()) {
				re = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			re = false;
		}

		if (re) {
			System.out.println("user exist");
		} else {
			System.out.println("user not found");
		}
		return re;
	}

	private boolean removeFromTitle(String book_id) {
		String sql = "DELETE FROM title WHERE EXISTS ( SELECT * FROM book WHERE book.title_id=title.isbn AND book.book_id=?)";
		boolean re = false;
		int n = -1;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, book_id);

			n = pstatement.executeUpdate();
			re = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			re = false;
		}
		if (re && n >= 0) {
			System.out.println("successfully remove" + n);
		} else {
			System.out.println("fail to remove" + n);
		}
		return re;
	}

	private boolean removeFromAuthor(String book_id) {
		String sql = "DELETE FROM author WHERE EXISTS ( SELECT * FROM book WHERE book.title_id=author.title_id AND book.book_id=?)";
		boolean re = false;
		int n = -1;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, book_id);

			n = pstatement.executeUpdate();
			re = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			re = false;
		}

		if (re && n >= 0) {
			System.out.println("successfully remove author");
		} else {
			System.out.println("fail remove author");
		}
		return re;
	}

	private boolean addToRecommendHistory(List<String> card_id, List<String> title_isbn, List<String> title_name,
			List<String> title_press, List<String> r_reason, List<String> title_author) {
		String sql = "insert into recommendhistory(card_id,title_isbn,title_name,title_press,r_reason,title_author) values (?,?,?,?,?,?)";
		boolean re = true;
		int n = -1;
		for (int i = 0; i < card_id.size(); i++) {
			boolean re1 = false;
			try {
				PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
				pstatement.setString(1, card_id.get(i));
				pstatement.setString(2, title_isbn.get(i));
				pstatement.setString(3, title_name.get(i));
				pstatement.setString(4, title_press.get(i));
				pstatement.setString(5, r_reason.get(i));
				pstatement.setString(6, title_author.get(i));

				n = pstatement.executeUpdate();
				re1 = true;
			} catch (Exception ex) {
				re1 = false;
				ex.printStackTrace();
			}

			if (re1) {
				System.out.println("successfully recommend");
			} else {
				System.out.println("fail recommend");
			}
			re = re && re1;
		}
		if (card_id.size() == 0) {
			re = true;
		}
		return re;
	}

	private boolean removeFromRecommend(String title_name) {
		boolean re = false;

		String sql = "select * from recommend where title_name=?";
		List<String> card_id, title_isbn, title_names, title_press, r_reason, title_author;
		card_id = new ArrayList<String>();
		title_isbn = new ArrayList<String>();
		title_names = new ArrayList<String>();
		title_press = new ArrayList<String>();
		r_reason = new ArrayList<String>();
		title_author = new ArrayList<String>();

		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, title_name);
			ResultSet rs = pstatement.executeQuery();

			while (rs.next()) {
				card_id.add(rs.getString("card_id"));
				title_isbn.add(rs.getString("title_isbn"));
				title_press.add(rs.getString("title_press"));
				r_reason.add(rs.getString("r_reason"));
				title_author.add(rs.getString("title_author"));
				title_names.add(title_name);
			}
			re = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			re = false;
		}
		System.out.println("select from recommend" + re);

		sql = "DELETE FROM recommend WHERE title_name=?";

		boolean re1 = false;
		int n = 0;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, title_name);

			n = pstatement.executeUpdate();
			re1 = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			re1 = false;
		}
		if (re1 && n >= 0) {
			System.out.println("delete success");
		} else {
			System.out.println("delete fail");
		}
		re = re && re1;

		// 添加到推荐历史
		re1 = addToRecommendHistory(card_id, title_isbn, title_names, title_press, r_reason, title_author);
		System.out.println("addtorecommendhistory" + re1);
		re = re && re1;
		return re;
	}

	private boolean removeFromKeyWord(String book_id) {
		String sql = "DELETE FROM keyword WHERE EXISTS ( SELECT * FROM book WHERE keyword.title_id=book.title_id AND book.book_id=?)";
		boolean re = false;
		int n = -1;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, book_id);

			n = pstatement.executeUpdate();
			re = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			re = false;
		}

		if (re && n >= 0) {
			System.out.println("delete success");
		} else {
			System.out.println("delete fail");
		}
		return re;
	}

	private String getISBN(String book_id) {
		String sql = "select * from book where book_id=?";
		String title_id = "";
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, book_id);
			ResultSet rs = pstatement.executeQuery();

			if (rs.next()) {
				title_id = rs.getString(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(title_id);
		return title_id;
	}

	private int getBookNumber(String book_id) {
		int title_booknumber = -1;
		String sql = "select title_booknumber from title,book where book_id=? and title_id=isbn";
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, book_id);
			ResultSet rs = pstatement.executeQuery();

			if (rs.next()) {
				title_booknumber = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println(title_booknumber);
		return title_booknumber;
	}

	private String getTitleName(String book_id) {
		String title_name = "";
		String sql = "select title_name from title,book where book_id=? and title_id=isbn";
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, book_id);
			ResultSet rs = pstatement.executeQuery();

			if (rs.next()) {
				title_name = rs.getString(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(title_name);
		return title_name;
	}

	private boolean minusBookNumber(String book_id) {
		int title_booknumber = getBookNumber(book_id);
		boolean re = false;
		if (title_booknumber >= 1) {
			// 书册总数不少于1本。即书目存在
			String sql = "UPDATE title,book SET title_booknumber=title_booknumber-1,title_currentnumber=title_currentnumber-1 WHERE book_id=? AND title_id=isbn";
			int n = -1;
			try {
				PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
				pstatement.setString(1, book_id);

				n = pstatement.executeUpdate();
				re = true;
			} catch (Exception ex) {
				re = false;
				ex.printStackTrace();
			}
			if (re && n >= 0) {
				System.out.println("success");
			} else {
				System.out.println("fail");
			}
		}
		return re;
	}

	public boolean isBookInLib(String book_id) {
		String sql = "select * from book where book_id = ? ";
		boolean re = false;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, book_id);
			ResultSet rs = pstatement.executeQuery();

			if (rs.next()) {
				if (rs.getString("book_state").equals("I")) {
					re = true;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			re = false;
		}

		if (re) {
			System.out.println("ok");
		} else {
			System.out.println("not ok");
		}
		return re;
	}

	private boolean changeBookStateIntoI(String book_id) {
		String sql = "update book set book_state='I' where book_id=(?)";
		boolean re = false;
		int n = -1;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, book_id);

			n = pstatement.executeUpdate();
			re = true;
		} catch (Exception ex) {
			re = false;
			ex.printStackTrace();
		}

		if (re && n >= 0) {
			System.out.println("OK");
		} else {
			System.out.println("not OK");
		}
		return re;
	}

	private boolean changeBookStateIntoO(String book_id) {
		String sql = "update book set book_state='O' where book_id=(?)";
		boolean re = false;
		int n = 1;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, book_id);

			n = pstatement.executeUpdate();
			re = true;
		} catch (Exception ex) {
			re = false;
			ex.printStackTrace();
		}

		if (re && n >= 0) {
			System.out.println("OK");
		} else {
			System.out.println("fail");
		}
		return re;
	}

	private boolean addTitleCurrentNum(String book_id) {
		String sql = "UPDATE title SET title_currentnumber=title_currentnumber+1 "
				+ "WHERE EXISTS ( SELECT * FROM book WHERE title.isbn=book.title_id AND book.book_id=?)";
		boolean re = false;
		int n = -1;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, book_id);

			n = pstatement.executeUpdate();
			re = true;
		} catch (Exception ex) {
			re = false;
			ex.printStackTrace();
		}

		if (re && n >= 0) {
			System.out.println("OK");
		} else {
			System.out.println("fail");
		}
		return re;
	}

	private boolean minusTitleCurrentNum(String book_id) {
		String sql = "UPDATE title SET title_currentnumber=title_currentnumber-1 WHERE EXISTS ( SELECT * FROM book WHERE title.isbn=book.title_id AND book.book_id=?)";
		boolean re = false;
		int n = -1;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, book_id);

			n = pstatement.executeUpdate();
			re = true;
		} catch (Exception ex) {
			re = false;
			ex.printStackTrace();
		}

		if (re && n >= 0) {
			System.out.println("Find it: " + n);
		} else {
			System.out.println("Didn't find it" + n);
		}
		return re;
	}

	public boolean removeOneBook(String book_id) {
		boolean re = false;
		try {
			conn.getConn().setAutoCommit(false);

			int title_booknumber = getBookNumber(book_id);
			if (title_booknumber <= 0) {
				re = false;
			} else if (title_booknumber == 1) {
				boolean re1 = true;
				re1 = removeFromTitle(book_id);
				re = re && re1;
				System.out.println("removefromtitle" + re);
				re1 = removeFromAuthor(book_id);
				re = re && re1;
				System.out.println("removefromtitle" + re);
				re = removeFromKeyWord(book_id);
				re = re && re1;
				System.out.println("removefromkeyword" + re);
			} else {
				minusBookNumber(getISBN(book_id));
			}
			boolean re2 = removeFromBook(book_id);
			re = re && re2;
			System.out.println("removefrombook" + re);

			if (re) {
				conn.getConn().commit();
				conn.getConn().setAutoCommit(true);
			} else {
				conn.getConn().rollback();
				conn.getConn().setAutoCommit(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;

	}

	private Date getBorrowDate(String card_id, String book_id) {
		String sql = "select borrow_date from borrow where card_id=? and book_id=?";
		Date date = null;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, card_id);
			pstatement.setString(2, book_id);
			ResultSet rs = pstatement.executeQuery();

			if (rs.next()) {
				date = rs.getDate("borrow_date");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(date);
		return date;
	}

	public boolean borrowBookFromLib(String card_id, String book_id, Date borrow_date) {
		boolean re = false;
		try {
			conn.getConn().setAutoCommit(false);

			re = changeBookStateIntoO(book_id);
			boolean re1 = minusTitleCurrentNum(book_id);
			re = re1 && re;

			re1 = addToBorrow(card_id, book_id, borrow_date, this.BORROW_TIME, 1, getTitleName(book_id));
			re = re && re1;

			if (re) {
				conn.getConn().commit();
				conn.getConn().setAutoCommit(true);
			} else {
				conn.getConn().rollback();
				conn.getConn().setAutoCommit(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}

	public boolean returnBookToLib(String card_id, String book_id, Date return_date) {
		boolean re = false;
		try {
			conn.getConn().setAutoCommit(false);
			re = changeBookStateIntoI(book_id);
			boolean re1 = addTitleCurrentNum(book_id);
			re = re1 && re;

			re1 = addToHistory(card_id, book_id, getBorrowDate(card_id, book_id), return_date);
			re = re && re1;
			re1 = removeFromBorrow(card_id, book_id);
			re = re && re1;

			if (re) {
				conn.getConn().commit();
				conn.getConn().setAutoCommit(true);
			} else {
				conn.getConn().rollback();
				conn.getConn().setAutoCommit(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}

	public int getBorrowNumber(String card_id) {
		int borrow_number = -1;
		String sql = "select count(*) from borrow where card_id=? ";
		try {

			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, card_id);
			ResultSet rs = pstatement.executeQuery();

			if (rs.next()) {
				borrow_number = rs.getInt(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println(borrow_number);
		return borrow_number;
	}

	public Vector queryRecommend() {
		String sql = "select * from recommend";
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			ResultSet rs = pstatement.executeQuery();
			Vector<Object> row1 = new Vector<Object>();
			row1.add("id");
			row1.add("isbn");
			row1.add("name");
			row1.add("author");
			row1.add("press");
			row1.add("reason");
			data.add(row1);

			while (rs.next()) {
				Vector<Object> row = new Vector<Object>(); // 一行数据
				row.add(rs.getString("card_id"));
				row.add(rs.getString("title_isbn"));
				row.add(rs.getString("title_name"));
				row.add(rs.getString("title_author"));
				row.add(rs.getString("title_press"));
				row.add(rs.getString("r_reason"));

				data.add(row); // 添加一行数据
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return data;
	}

	private boolean addToMessageByRecommend(String title_name) {
		List<String> recommendor = queryPeopleByTitleName(title_name);
		String message = "new massage" + title_name + "book";
		boolean re = true;

		for (int i = 0; i < recommendor.size(); i++) {
			String person = recommendor.get(i);

			boolean re1 = addToMessage(person, message);
			re = re && re1;
		}
		return re;
	}

	private boolean addToMessage(String card_id, String message) {
		String sql = "insert into message(card_id,message) values (?,?)";
		boolean re = false;
		int n = -1;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, card_id);
			pstatement.setString(2, message);

			n = pstatement.executeUpdate();
			re = true;
		} catch (Exception ex) {
			re = false;
			ex.printStackTrace();
		}

		if (re && n >= 0) {
			System.out.println("OK");
		} else {
			System.out.println("Fail");
		}
		return re;
	}

	private List<String> queryPeopleByTitleName(String title_name) {
		String sql = "select * from recommend where title_name=?";

		List<String> data = new ArrayList<String>();
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, title_name);
			ResultSet rs = pstatement.executeQuery();

			while (rs.next()) {
				data.add(rs.getString("card_id"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("info");
		return data;
	}

	private boolean addToPay(String card_id, String book_id, String title_name, String r_reason, String pay_price,
			Date borrow_date, Date pay_date) {
		String sql = "insert into pay(card_id,book_id,title_name,r_reason,pay_price,borrow_date,pay_date) values (?,?,?,?,?,?,?)";
		boolean re = false;
		int n = -1;
		try {
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, card_id);
			pstatement.setString(2, book_id);
			pstatement.setString(3, title_name);
			pstatement.setString(4, r_reason);
			pstatement.setString(5, pay_price);
			pstatement.setDate(6, borrow_date);
			pstatement.setDate(7, pay_date);

			n = pstatement.executeUpdate();
			re = true;
		} catch (Exception ex) {
			re = false;
			ex.printStackTrace();
		}

		if (re && n >= 0) {
			System.out.println("OK");
		} else {
			System.out.println("Fail");
		}
		return re;
	}

	public boolean pay(String card_id, String book_id, String r_reason, String pay_price, Date pay_date) {
		boolean re = false;
		try {
			conn.getConn().setAutoCommit(false);

			String sql = "select * from borrow where card_id=? and book_id=?";
			String title_name = "";
			boolean remove = false;
			try {
				PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
				pstatement.setString(1, card_id);
				pstatement.setString(2, book_id);

				ResultSet rs = pstatement.executeQuery();
				if (rs.next()) {
					title_name = rs.getString("title_name");
					Date borrow_date = rs.getDate("borrow_date");
					remove = addToPay(card_id, book_id, title_name, r_reason, pay_price, borrow_date, pay_date);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			boolean re1 = false;
			if (remove) {
				re1 = removeFromBorrow(card_id, book_id);
			}
			if (remove && re1) {
				conn.getConn().commit();
				conn.getConn().setAutoCommit(true);
			} else {
				conn.getConn().rollback();
				conn.getConn().setAutoCommit(true);
			}
			re = re1 && remove;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
}