import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/*
 * @ myZhou, qzDong
 */
public class LibConnection {

	Connection conn;
	
	
	public LibConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
		//	Class.forName("com.mysql.jdbc.Driver");
			
		//	String url = "jdbc:mysql://localhost:3306/library1";
		//	String user = "root";
		//	String passWord = "111111";
			
			//conn = DriverManager.getConnection(url, user, passWord);
			 conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/task5","root","111111");
			if (conn != null) {
				System.out.println("connection is set");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	
	
	public boolean isAdmin(String id,String password)
	{
		boolean re=false;
		//String sql = "select * from manager where manager_id = ?";
		
		try {
			String sql = "select * from manager where manager_id = ?";
			PreparedStatement pstatement = conn.prepareStatement(sql);
			pstatement.setString(1, id);
			ResultSet rs = pstatement.executeQuery();
			
			if(rs.next())
			{
			    if(password.equals(rs.getString(2)))
			    {
			    	
			    	re=true;
			    }
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			re=false;
		}
		return re;
	}
	
	public boolean isUser(String id,String password)
	{
		boolean re=false;
		//String sql = "select * from user where card_id = ?";
		
		try {

			String sql = "select * from user where card_id = ?";
			PreparedStatement pstatement = conn.prepareStatement(sql);
			pstatement.setString(1, id);
			ResultSet rs = pstatement.executeQuery();
			
			if(rs.next())
			{
			    if(password.equals(rs.getString("user_password")))
			    {
			    	re=true;
			    }
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			re=false;
		}
		return re;
	}
	
	
	/**
	 public static void main(String[] args) {
		 LibConnection libCon = new LibConnection();
		 Connection conn = libCon.getConn();
		 boolean re=false;
			//String sql = "select * from manager where manager_id = ?";
			String id="000001";
			String password="wang";
			Statement stmt;
			
			try {
				//stmt = ((Connection) conn).createStatement(); 
				//String sql = "select * from manager where manager_id = ?";
				//String sql = "select * from manager where manager_id = ?";
				String sql = "select * from user where card_id = ?";
				PreparedStatement pstatement =  conn.prepareStatement(sql);
				pstatement.setString(1, id);
			
				ResultSet rs = pstatement.executeQuery();
				
				if(rs.next())
				{
					
					
					if(password.equals(rs.getString("user_password")))
				    {
				    	re=true;
				    	System.out.println("get psd");
				    }
				    
					//String psd = rs.getString("Manager_pwd");
					//re=true;
					//System.out.println("password="+psd);
				}
				//System.out.println("password="+psd);
				
			} catch (Exception ex) {
				ex.printStackTrace();
				re=false;
			}
			
			System.out.println(re);
			
		 
	 }*/
	 
 
	 
	
	



	
	
}
