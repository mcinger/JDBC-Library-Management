import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

/*
 * @ myZhou, qzDong
 */
public class User {

	String card_id;
	LibConnection conn;
	String user_id;
	String user_name;
	String card_date;
	String card_time;
	String user_phone;

	final static int MAX_BORROW_TIMES = 2;
	final static int MAX_BORROW_TIME = 30;

	public User(String card_id, LibConnection conn) {
		this.card_id = card_id;
		this.conn = conn;
		this.getUserInformation();
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public LibConnection getConn() {
		return conn;
	}

	public void setConn(LibConnection conn) {
		this.conn = conn;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getCard_date() {
		return card_date;
	}

	public String getCard_time() {
		return card_time;
	}

	public String getUser_phone() {
		return user_phone;
	}

	private boolean getUserInformation() {
		boolean re = false;
		try {

			String sql = "select * from user where card_id=?";
			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, this.card_id);
			ResultSet rs = pstatement.executeQuery();

			if (rs.next()) {
				user_id = rs.getString(3);
				user_name = rs.getString(2);
				card_date = rs.getDate(4).toString();
				card_time = rs.getInt(5) + "time";
				user_phone = rs.getString(6);

				re = true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return re;
	}

	public Vector queryBorrow(String card_id) {
		String sql = "select * from borrow where card_id = ?";
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		try {

			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, card_id);
			ResultSet rs = pstatement.executeQuery();

			ResultSetMetaData meta = rs.getMetaData();

			Vector<Object> row1 = new Vector<Object>();

			row1.add("title_name");
			row1.add("book_id");
			row1.add("borrow_date");
			row1.add("borrow_times");

			data.add(row1);

			while (rs.next()) {
				Vector<Object> row = new Vector<Object>();
				row.add(rs.getString("title_name"));
				row.add(rs.getString("book_id"));
				row.add(rs.getDate("borrow_date"));
				row.add(rs.getInt("borrow_times"));

				data.add(row);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return data;
	}

	public boolean borrowAgain(java.sql.Date to_date, String card_id, String book_id) {
		String sql = "update borrow set borrow_times=borrow_times+1,borrow_date=? where book_id=(?) and card_id=(?)";

		boolean re = false;
		int n = 0;
		try {

			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setDate(1, to_date);
			pstatement.setString(2, book_id);
			pstatement.setString(3, card_id);

			n = pstatement.executeUpdate();
			re = true;
		} catch (Exception ex) {
			re = false;
			ex.printStackTrace();
		}

		if (re) {
			System.out.println("borrow again successful");
		} else {
			System.out.println("borrow again fail");
		}
		return re;
	}

	public int getBorrowTimes(String book_id) {
		int borrow_times = -1;

		String sql = "select borrow_times from borrow where card_id=? and book_id=?";
		try {

			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, card_id);
			pstatement.setString(2, book_id);
			ResultSet rs = pstatement.executeQuery();

			if (rs.next()) {
				borrow_times = rs.getInt("borrow_times");
				System.out.println("borrow_times");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println(borrow_times);
		return borrow_times;
	}

	public Vector queryByTitleName(String title_name) {
		final String final_title_name = new String(title_name);
		String sql = "select * from title where title_name = ?";
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		try {

			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, title_name);
			ResultSet rs = pstatement.executeQuery();

			ResultSetMetaData meta = rs.getMetaData();

			Vector<Object> row1 = new Vector<Object>();

			row1.add("isbn");
			row1.add("title_name");
			row1.add("title_press");
			row1.add("title_date");
			row1.add("title_price");
			row1.add("title_booknumber");
			row1.add("title_currentnumber");

			data.add(row1);

			while (rs.next()) {
				Vector<Object> row = new Vector<Object>();
				row.add(rs.getString("isbn"));
				row.add(rs.getString("title_name"));
				row.add(rs.getString("title_press"));
				row.add(rs.getDate("title_date"));
				row.add(rs.getString("title_price"));
				row.add(rs.getInt("title_booknumber"));
				row.add(rs.getInt("title_currentnumber"));

				data.add(row);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("query successfully");
		return data;
	}

	public Vector queryByAuthorName(String author_name) {
		String sql = "select * from title,author where author_name = ? and author.title_id=title.isbn";
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		try {

			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, author_name);
			ResultSet rs = pstatement.executeQuery();

			Vector<Object> row1 = new Vector<Object>();

			row1.add("isbn");
			row1.add("title_name");
			row1.add("title_press");
			row1.add("title_date");
			row1.add("title_price");
			row1.add("title_booknumber");
			row1.add("title_currentnumber");

			data.add(row1);

			while (rs.next()) {
				Vector<Object> row = new Vector<Object>();
				row.add(rs.getString("isbn"));
				row.add(rs.getString("title_name"));
				row.add(rs.getString("title_press"));
				row.add(rs.getDate("title_date"));
				row.add(rs.getString("title_price"));
				row.add(rs.getInt("title_booknumber"));
				row.add(rs.getInt("title_currentnumber"));

				data.add(row);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("query by author name success");
		return data;
	}

	public Vector queryByKeyword(String keyword) {
		String sql = "select * from title,keyword where key_word = ? and keyword.title_id=title.isbn";
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		try {

			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, keyword);
			ResultSet rs = pstatement.executeQuery();

			ResultSetMetaData meta = rs.getMetaData();

			Vector<Object> row1 = new Vector<Object>();

			row1.add("isbn");
			row1.add("title_name");
			row1.add("title_press");
			row1.add("title_date");
			row1.add("title_price");
			row1.add("title_booknumber");
			row1.add("title_currentnumber");

			data.add(row1);

			while (rs.next()) {
				Vector<Object> row = new Vector<Object>();
				row.add(rs.getString("isbn"));
				row.add(rs.getString("title_name"));
				row.add(rs.getString("title_press"));
				row.add(rs.getDate("title_date"));
				row.add(rs.getString("title_price"));
				row.add(rs.getInt("title_booknumber"));
				row.add(rs.getInt("title_currentnumber"));

				data.add(row);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("query by keyword success");
		return data;
	}

	public Vector queryByISBN(String isbn) {
		String sql = "select * from title where isbn = ? ";
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		try {

			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, isbn);
			ResultSet rs = pstatement.executeQuery();

			ResultSetMetaData meta = rs.getMetaData();

			Vector<Object> row1 = new Vector<Object>();

			row1.add("isbn");
			row1.add("title_name");
			row1.add("title_press");
			row1.add("title_date");
			row1.add("title_price");
			row1.add("title_booknumber");
			row1.add("title_currentnumber");

			data.add(row1);

			while (rs.next()) {
				Vector<Object> row = new Vector<Object>();
				row.add(rs.getString("isbn"));
				row.add(rs.getString("title_name"));
				row.add(rs.getString("title_press"));
				row.add(rs.getDate("title_date"));
				row.add(rs.getString("title_price"));
				row.add(rs.getInt("title_booknumber"));
				row.add(rs.getInt("title_currentnumber"));

				data.add(row);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("query by isbn ok");
		return data;
	}

	public Vector queryByPress(String title_press) {
		String sql = "select * from title where title_press = ? ";
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		try {

			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, title_press);
			ResultSet rs = pstatement.executeQuery();

			ResultSetMetaData meta = rs.getMetaData();

			Vector<Object> row1 = new Vector<Object>();

			row1.add("isbn");
			row1.add("title_name");
			row1.add("title_press");
			row1.add("title_date");
			row1.add("title_price");
			row1.add("title_booknumber");
			row1.add("title_currentnumber");

			data.add(row1);

			while (rs.next()) {
				Vector<Object> row = new Vector<Object>();
				row.add(rs.getString("isbn"));
				row.add(rs.getString("title_name"));
				row.add(rs.getString("title_press"));
				row.add(rs.getDate("title_date"));
				row.add(rs.getString("title_price"));
				row.add(rs.getInt("title_booknumber"));
				row.add(rs.getInt("title_currentnumber"));

				data.add(row);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("query by press success");
		return data;
	}

	public boolean recommendBook(String title_isbn, String title_name, String title_author, String title_press,
			String r_reason) {
		String sql = "insert into recommend(card_id,title_isbn,title_name,title_press,r_reason,title_author) values (?,?,?,?,?,?)";

		boolean re = false;
		int n = 0;
		try {

			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, this.getCard_id());
			pstatement.setString(2, title_isbn);
			pstatement.setString(3, title_name);
			pstatement.setString(4, title_press);
			pstatement.setString(5, r_reason);
			pstatement.setString(6, title_author);

			n = pstatement.executeUpdate();
			re = true;

		} catch (Exception ex) {
			re = false;
			ex.printStackTrace();
		}

		if (re) {
			System.out.println("recommend book ok");
		} else {
			System.out.println("recommend book fail");
		}
		return re;
	}

	public List<String> queryMessage() {
		String sql = "select * from message where card_id = " + this.getCard_id();
		List<String> data = new ArrayList<String>();
		try {

			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);

			ResultSet rs = pstatement.executeQuery();

			while (rs.next()) {
				Vector<Object> row = new Vector<Object>();

				data.add(rs.getString("message"));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("query message ok");
		return data;
	}

	public Vector queryMyRecommend() {
		String sql = "select * from recommend where card_id = " + this.getCard_id();
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		try {

			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			ResultSet rs = pstatement.executeQuery();

			ResultSetMetaData meta = rs.getMetaData();

			Vector<Object> row1 = new Vector<Object>();

			row1.add("isbn");
			row1.add("title_name");
			row1.add("title_author");
			row1.add("title_press");
			row1.add("r_reason");

			data.add(row1);

			while (rs.next()) {
				Vector<Object> row = new Vector<Object>();
				row.add(rs.getString("title_isbn"));
				row.add(rs.getString("title_name"));
				row.add(rs.getString("title_author"));
				row.add(rs.getString("title_press"));
				row.add(rs.getString("r_reason"));

				data.add(row);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		/*
		 * sql =
		 * "select * from recommendhistory where card_id = "+this.getCard_id();
		 * try {
		 * 
		 * PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
		 * ResultSet rs = pstatement.executeQuery();
		 * 
		 * ResultSetMetaData meta = rs.getMetaData();
		 * 
		 * while(rs.next()) { Vector<Object> row = new Vector<Object>();
		 * row.add(rs.getString("title_isbn"));
		 * row.add(rs.getString("title_name"));
		 * row.add(rs.getString("title_author"));
		 * row.add(rs.getString("title_press"));
		 * row.add(rs.getString("r_reason")); row.add("未采纳");
		 * 
		 * data.add(row); }
		 * 
		 * } catch (Exception ex) { ex.printStackTrace(); }
		 */

		return data;
	}

	public boolean removeFromMessage(String card_id) {
		String sql = "delete from message where card_id=(?)";

		boolean re = false;
		int n = 0;
		try {

			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, card_id);

			n = pstatement.executeUpdate();

			re = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			re = false;
		}

		if (re) {
			System.out.println("delete message success");
		} else {
			System.out.println("delete message fail");
		}

		return re;
	}

	public List<String> queryOverDue() {
		java.util.Date current_date = Calendar.getInstance().getTime();

		String sql = "select * from borrow where card_id = ?";
		List<String> data = new ArrayList<String>();
		try {

			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, this.getCard_id());
			ResultSet rs = pstatement.executeQuery();

			while (rs.next()) {

				java.sql.Date borrow_date = rs.getDate("borrow_date");
				if (Math.abs((borrow_date.getTime() - current_date.getTime())
						/ (24 * 60 * 60 * 1000)) > this.MAX_BORROW_TIME) {
					data.add(rs.getString("title_name"));
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return data;
	}

	public boolean addToComment(String card_id, String title_name, String comment_content, Date comment_date) {
		String sql = "insert into comment(card_id,title_name,comment_content,comment_date) values (?,?,?,?)";

		boolean re = false;
		int n = 0;
		try {

			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, card_id);
			pstatement.setString(2, title_name);
			pstatement.setString(3, comment_content);
			pstatement.setDate(4, comment_date);

			n = pstatement.executeUpdate();
			re = true;

		} catch (Exception ex) {
			re = false;
			ex.printStackTrace();
		}

		if (re) {
			System.out.println("add comment ok");
		} else {
			System.out.println("add comment fail");
		}
		return re;
	}

	public Vector queryMyComment() {
		String sql = "select * from comment where card_id = ?";
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		try {

			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, this.getCard_id());
			ResultSet rs = pstatement.executeQuery();

			ResultSetMetaData meta = rs.getMetaData();

			Vector<Object> row1 = new Vector<Object>();

			row1.add("card_id");
			row1.add("title_name");
			row1.add("comment_date");
			row1.add("comment_content");

			data.add(row1);

			while (rs.next()) {
				Vector<Object> row = new Vector<Object>(); // 一行数据
				row.add(rs.getString("card_id"));
				row.add(rs.getString("title_name"));
				row.add(rs.getDate("comment_date"));
				row.add(rs.getString("comment_content"));

				data.add(row);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return data;
	}

	public Vector queryComment(String title_name) {
		String sql = "select * from comment where title_name = ?";
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		try {

			PreparedStatement pstatement = conn.getConn().prepareStatement(sql);
			pstatement.setString(1, title_name);
			ResultSet rs = pstatement.executeQuery();

			ResultSetMetaData meta = rs.getMetaData();

			Vector<Object> row1 = new Vector<Object>();

			row1.add("card_id");
			row1.add("title_name");
			row1.add("comment_date");
			row1.add("comment_content");

			data.add(row1);

			while (rs.next()) {
				Vector<Object> row = new Vector<Object>();
				row.add(rs.getString("card_id"));
				row.add(rs.getString("title_name"));
				row.add(rs.getDate("comment_date"));
				row.add(rs.getString("comment_content"));

				data.add(row);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return data;
	}

}
