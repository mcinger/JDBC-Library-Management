import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;
/*
 * @ myZhou, qzDong
 */

public class AdminFrame extends JFrame {

	Administrator admin;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_24;
	private JTextField textField_25;
	private JTextField textField_26;
	private JTextField textField_27;

	Image icon;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// AdminFrame frame = new AdminFrame();
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame. final Administrator admin
	 */

	public AdminFrame(final Administrator admin) {
		this.admin = admin;

		// Load the picture
		try {
			File file = new File("icon.jpg");
			icon = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// this.setIconImage(icon);

		this.setTitle("admin window");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Add User", null, panel, null);
		panel.setLayout(null);

		JLabel label_2 = new JLabel("first");
		label_2.setBounds(337, 12, 0, 0);
		panel.add(label_2);

		JLabel label = new JLabel("card_id");
		label.setBounds(20, 50, 68, 23);
		panel.add(label);

		textField = new JTextField();
		textField.setBounds(92, 50, 100, 20);
		panel.add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("user_name");
		label_1.setBounds(286, 50, 68, 19);
		panel.add(label_1);

		textField_1 = new JTextField();
		textField_1.setBounds(351, 50, 119, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel = new JLabel("user_id");
		lblNewLabel.setBounds(20, 85, 84, 20);
		panel.add(lblNewLabel);

		textField_2 = new JTextField();
		textField_2.setBounds(114, 85, 192, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JLabel label_3 = new JLabel("password");
		label_3.setBounds(20, 120, 89, 20);
		panel.add(label_3);

		JLabel label_4 = new JLabel("copy password");
		label_4.setBounds(20, 155, 154, 20);
		panel.add(label_4);

		passwordField = new JPasswordField();
		passwordField.setBounds(114, 120, 119, 20);
		panel.add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(182, 155, 119, 21);
		panel.add(passwordField_1);

		JLabel label_6 = new JLabel("Time");
		label_6.setBounds(335, 190, 108, 20);
		panel.add(label_6);

		JLabel label_7 = new JLabel("date");
		label_7.setBounds(20, 190, 68, 20);
		panel.add(label_7);

		textField_3 = new JTextField();
		textField_3.setBounds(92, 190, 100, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(444, 190, 66, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);

		JLabel label_8 = new JLabel("times");
		label_8.setBounds(520, 190, 54, 20);
		panel.add(label_8);

		JLabel label_9 = new JLabel("userphone");
		label_9.setBounds(20, 225, 84, 20);
		panel.add(label_9);

		textField_5 = new JTextField();
		textField_5.setBounds(114, 225, 140, 20);
		panel.add(textField_5);
		textField_5.setColumns(10);

		JButton button = new JButton("Adduser");

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("hit hand in");

				String card_id = textField.getText();
				String user_name = textField_1.getText();
				String user_id = textField_2.getText();
				String user_password = passwordField.getText();
				String copy_of_user_password = passwordField_1.getText();
				String card_date = textField_3.getText();
				String card_time = textField_4.getText();
				String user_phone = textField_5.getText();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				try {
					date = sdf.parse(card_date);
				} catch (Exception e) {
					e.printStackTrace();
				}

				boolean boo_card_time = true;
				try {
					Integer.parseInt(card_time);
				} catch (Exception e) {
					boo_card_time = false;
					e.printStackTrace();
				}

				// need prove
				boolean re = false;
				if (card_id.length() <= 0) {
					JOptionPane.showMessageDialog(null, "card.id not exist");
				} else if (card_id.length() != 6) {
					JOptionPane.showMessageDialog(null, "cad_id not long enough");
				} else if (user_name.length() <= 0) {
					JOptionPane.showMessageDialog(null, "user_name not exist");
				} else if (user_name.length() > 100) {
					JOptionPane.showMessageDialog(null, "user_name too long");
				} else if (user_id.length() <= 0) {
					JOptionPane.showMessageDialog(null, "user_id short");
				} else if (user_id.length() != 18) {
					JOptionPane.showMessageDialog(null, "user_id not enough");
				} else if (user_password.length() <= 0) {
					JOptionPane.showMessageDialog(null, "password short");
				} else if (user_password.length() > 8) {
					JOptionPane.showMessageDialog(null, "password long");
				} else if (copy_of_user_password.length() <= 0) {
					JOptionPane.showMessageDialog(null, "password not accurate");
				} else if (!copy_of_user_password.equals(user_password)) {
					JOptionPane.showMessageDialog(null, "not correct copy");
				} else if (card_date.length() <= 0) {
					JOptionPane.showMessageDialog(null, "date short");
				} else if (!sdf.format(date).equalsIgnoreCase(card_date)) {
					JOptionPane.showMessageDialog(null, "not accurate");
				} else if (card_time.length() <= 0) {
					JOptionPane.showMessageDialog(null, "time short");
				} else if (!boo_card_time) {
					JOptionPane.showMessageDialog(null, "time not good");
				} else if (user_phone.length() <= 0) {
					JOptionPane.showMessageDialog(null, "phone short");
				} else if (user_phone.length() > 12) {
					JOptionPane.showMessageDialog(null, "phone long");
				} else {
					java.sql.Date card_date_date = new java.sql.Date(date.getTime());
					re = admin.addUser(card_id, copy_of_user_password, user_id, user_name, card_date_date,
							Integer.parseInt(card_time), user_phone);
				}

				if (re) {
					System.out.println("success add user");
					JOptionPane.showMessageDialog(null, "well done");
				}
			}
		});
		button.setBounds(334, 225, 93, 23);
		panel.add(button);

		JLabel label_10 = new JLabel("Reomve User");
		label_10.setBounds(10, 260, 601, 15);
		panel.add(label_10);

		JLabel label_5 = new JLabel("yyyy/MM/dd");
		label_5.setBounds(200, 190, 106, 15);
		panel.add(label_5);

		JLabel label_11 = new JLabel("Input");
		label_11.setFont(new Font("Dialog", Font.PLAIN, 15));
		label_11.setBounds(20, 285, 78, 25);
		panel.add(label_11);

		JLabel label_12 = new JLabel("Add User");
		label_12.setFont(new Font("Dialog", Font.PLAIN, 15));
		label_12.setBounds(20, 12, 84, 28);
		panel.add(label_12);

		JLabel label_13 = new JLabel("card_id");
		label_13.setBounds(20, 322, 68, 20);
		panel.add(label_13);

		textField_6 = new JTextField();
		textField_6.setBounds(108, 322, 100, 21);
		panel.add(textField_6);
		textField_6.setColumns(10);

		JButton button_1 = new JButton("ReomverUser");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("click button1");

				String card_id = textField_6.getText();

				boolean re = false;
				if (card_id.length() < 0) {
					JOptionPane.showMessageDialog(null, "card_id short");
				} else if (card_id.length() != 6) {
					JOptionPane.showMessageDialog(null, "card_id should be length 6");
				} else {
					re = admin.removeUser(card_id);
				}

				if (re) {
					System.out.println("remove ok");
					JOptionPane.showMessageDialog(null, "successfully remove");
				}
			}
		});
		button_1.setBounds(20, 352, 93, 23);
		panel.add(button_1);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Adding", null, panel_1, null);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { contentPane }));
		panel_1.setLayout(null);

		JLabel label_14 = new JLabel("Book_id");
		label_14.setBounds(20, 45, 70, 20);
		panel_1.add(label_14);

		JLabel label_15 = new JLabel("Input");
		label_15.setFont(new Font("Dialog", Font.PLAIN, 15));
		label_15.setBounds(20, 10, 70, 25);
		panel_1.add(label_15);

		textField_7 = new JTextField();
		textField_7.setBounds(92, 45, 132, 20);
		panel_1.add(textField_7);
		textField_7.setColumns(10);

		JLabel label_16 = new JLabel("16 number length");
		label_16.setBounds(250, 45, 93, 20);
		panel_1.add(label_16);

		JLabel lblNewLabel_1 = new JLabel("ISBN");
		lblNewLabel_1.setBounds(20, 80, 70, 20);
		panel_1.add(lblNewLabel_1);

		textField_8 = new JTextField();
		textField_8.setBounds(92, 80, 132, 21);
		panel_1.add(textField_8);
		textField_8.setColumns(10);

		JLabel label_17 = new JLabel("Title_name");
		label_17.setBounds(283, 80, 85, 20);
		panel_1.add(label_17);

		textField_9 = new JTextField();
		textField_9.setBounds(354, 80, 191, 21);
		panel_1.add(textField_9);
		textField_9.setColumns(10);

		JLabel label_18 = new JLabel("author_list");
		label_18.setBounds(20, 115, 54, 20);
		panel_1.add(label_18);

		textField_10 = new JTextField();
		textField_10.setBounds(63, 115, 305, 21);
		panel_1.add(textField_10);
		textField_10.setColumns(10);

		JLabel label_19 = new JLabel("Multiple Author");
		label_19.setBounds(394, 115, 178, 20);
		panel_1.add(label_19);

		JLabel label_20 = new JLabel("Title_press");
		label_20.setBounds(20, 150, 54, 20);
		panel_1.add(label_20);

		textField_11 = new JTextField();
		textField_11.setBounds(73, 150, 224, 20);
		panel_1.add(textField_11);
		textField_11.setColumns(10);

		JLabel label_21 = new JLabel("Date");
		label_21.setBounds(20, 185, 85, 20);
		panel_1.add(label_21);

		textField_12 = new JTextField();
		textField_12.setBounds(112, 185, 112, 21);
		panel_1.add(textField_12);
		textField_12.setColumns(10);

		JLabel label_22 = new JLabel("format:2011/09/10");
		label_22.setBounds(234, 185, 106, 20);
		panel_1.add(label_22);

		JLabel label_23 = new JLabel("Price");
		label_23.setBounds(364, 150, 70, 20);
		panel_1.add(label_23);

		textField_13 = new JTextField();
		textField_13.setBounds(428, 150, 66, 20);
		panel_1.add(textField_13);
		textField_13.setColumns(10);

		JLabel label_24 = new JLabel("dollar");
		label_24.setBounds(504, 150, 54, 15);
		panel_1.add(label_24);

		JLabel label_25 = new JLabel("Keyword");
		label_25.setBounds(20, 225, 54, 20);
		panel_1.add(label_25);

		textField_14 = new JTextField("");
		textField_14.setBounds(104, 225, 277, 20);
		panel_1.add(textField_14);
		textField_14.setColumns(10);

		JLabel label_26 = new JLabel("keyword can be a lot");

		label_26.setBounds(394, 225, 178, 20);
		panel_1.add(label_26);

		JLabel label_27 = new JLabel("Operation_date");
		label_27.setBounds(354, 185, 80, 20);
		panel_1.add(label_27);

		textField_15 = new JTextField();
		textField_15.setBounds(428, 185, 144, 21);
		panel_1.add(textField_15);
		textField_15.setColumns(10);

		JButton button_2 = new JButton("Add product");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("hit button");

				String book_id = textField_7.getText();
				String isbn = textField_8.getText();
				String title_name = textField_9.getText();
				String author_list_str = textField_10.getText();
				String title_press = textField_11.getText();
				String title_date = textField_12.getText();
				String title_price = textField_13.getText();
				String keyword_list_str = "";
				keyword_list_str += textField_14.getText();
				String operation_date = textField_15.getText();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				try {
					date = sdf.parse(title_date);
				} catch (Exception e) {
					e.printStackTrace();
				}

				Date op_date = new Date();
				try {
					op_date = sdf.parse(operation_date);
				} catch (Exception e) {
					e.printStackTrace();
				}

				boolean boo_title_price = true;
				try {
					Double.parseDouble(title_price);
				} catch (Exception e) {
					boo_title_price = false;
					e.printStackTrace();
				}

				List<String> author_list = new ArrayList<String>();
				int position = 0;
				for (int i = 0; i < author_list_str.length(); i++) {
					if (author_list_str.charAt(i) == ',') {
						author_list.add(author_list_str.substring(position, i));
						position = i + 1;
					}
				}
				author_list.add(author_list_str.substring(position));

				List<String> keyword_list = new ArrayList<String>();
				position = 0;
				for (int i = 0; i < keyword_list_str.length(); i++) {
					if (keyword_list_str.charAt(i) == ',') {
						keyword_list.add(keyword_list_str.substring(position, i));
						position = i + 1;
					}
				}
				keyword_list.add(keyword_list_str.substring(position));

				boolean re = false;
				if (book_id.length() <= 0) {
					JOptionPane.showMessageDialog(null, "book_id short");
				} else if (book_id.length() != 16) {
					JOptionPane.showMessageDialog(null, "book_id musth be 16 number");
				} else if (isbn.length() <= 0) {
					JOptionPane.showMessageDialog(null, "Isbn too short");
				} else if (isbn.length() != 13) {
					JOptionPane.showMessageDialog(null, "Isbn must be 13 number");
				} else if (title_name.length() <= 0) {
					JOptionPane.showMessageDialog(null, "no title_name");
				} else if (author_list_str.length() <= 0) {
					JOptionPane.showMessageDialog(null, "no author");
				} else if (title_press.length() <= 0) {
					JOptionPane.showMessageDialog(null, "no press");
				} else if (title_date.length() <= 0) {
					JOptionPane.showMessageDialog(null, "no title_date");
				} else if (!sdf.format(date).equalsIgnoreCase(title_date)) {
					JOptionPane.showMessageDialog(null, "date format not correct");
				} else if (title_price.length() <= 0) {
					JOptionPane.showMessageDialog(null, "no price");
				} else if (!boo_title_price) {
					JOptionPane.showMessageDialog(null, "price should be in double");
				} else if (operation_date.length() <= 0) {
					JOptionPane.showMessageDialog(null, "on operation_date");
				} else if (!sdf.format(op_date).equalsIgnoreCase(operation_date)) {
					JOptionPane.showMessageDialog(null, "operation_date format not correct");
				} else {
					java.sql.Date publish_date_date = new java.sql.Date(date.getTime());
					java.sql.Date operation_date_date = new java.sql.Date(op_date.getTime());

					try {
						admin.conn.getConn().setAutoCommit(false);
					} catch (Exception e) {
						e.printStackTrace();
					}

					boolean re1 = false;
					re = true;

					for (int i = 0; i < author_list.size(); i++) {
						re1 = admin.addAuthor(isbn, author_list.get(i));
						re = re && re1;
						System.out.println("add author ok" + re1);
					}

					re1 = admin.addOneBook(book_id, operation_date_date, admin.getId(), isbn, title_name, title_press,
							publish_date_date, Double.parseDouble(title_price), 1);
					re = re && re1;
					System.out.println("add book ok" + re1);

					for (int i = 0; i < keyword_list.size(); i++) {
						re1 = admin.addKeyWord(isbn, keyword_list.get(i));
						re = re && re1;
						System.out.println("add keyword ok" + re1);
					}
				}

				if (re) {

					try {
						admin.conn.getConn().commit();
						admin.conn.getConn().setAutoCommit(true);
					} catch (Exception e) {
						e.printStackTrace();
					}

					System.out.println("Ok");
					JOptionPane.showMessageDialog(null, "Bingo");
				} else {
					try {
						admin.conn.getConn().rollback();
						admin.conn.getConn().setAutoCommit(true);
					} catch (Exception e) {
						e.printStackTrace();
					}

					System.out.println("Error");
					JOptionPane.showMessageDialog(null, "something happen");
				}
			}
		});
		button_2.setBounds(12, 260, 93, 23);
		panel_1.add(button_2);

		JLabel label_28 = new JLabel(
				"******************************************************************************************************");
		label_28.setBounds(0, 295, 621, 20);
		panel_1.add(label_28);

		JLabel label_29 = new JLabel("Remove");
		label_29.setFont(new Font("Dialog", Font.PLAIN, 15));
		label_29.setBounds(12, 318, 78, 32);
		panel_1.add(label_29);

		JLabel label_30 = new JLabel("Book_id");
		label_30.setBounds(20, 355, 70, 20);
		panel_1.add(label_30);

		textField_16 = new JTextField();
		textField_16.setBounds(93, 355, 167, 21);
		panel_1.add(textField_16);
		textField_16.setColumns(10);

		JLabel label_31 = new JLabel("Must length 16");
		label_31.setBounds(283, 355, 99, 20);
		panel_1.add(label_31);

		JButton button_3 = new JButton("RemoveBook");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("hit button3");

				String book_id = textField_16.getText();

				boolean re = false;
				if (book_id.length() <= 0) {
					JOptionPane.showMessageDialog(null, "no book id");
				} else if (book_id.length() != 16) {
					JOptionPane.showMessageDialog(null, "Book_id length not 16");
				} else if (!admin.isBookExist(book_id)) {
					JOptionPane.showMessageDialog(null, "No book with input id");
				} else {
					re = admin.removeOneBook(book_id);
				}

				if (re) {
					System.out.println("Ok");
					JOptionPane.showMessageDialog(null, "Successfully Remove");
				} else {
					System.out.println("Error");
					JOptionPane.showMessageDialog(null, "Remove error");
				}
			}
		});
		button_3.setBounds(12, 385, 93, 23);
		panel_1.add(button_3);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("BorrowBook", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel label_32 = new JLabel("Input");
		label_32.setFont(new Font("Dialog", Font.PLAIN, 15));
		label_32.setBounds(10, 10, 82, 25);
		panel_2.add(label_32);

		JLabel label_33 = new JLabel("Card_id");
		label_33.setBounds(10, 45, 65, 20);
		panel_2.add(label_33);

		textField_17 = new JTextField();
		textField_17.setBounds(85, 45, 120, 21);
		panel_2.add(textField_17);
		textField_17.setColumns(10);

		JLabel label_34 = new JLabel("Book_id");
		label_34.setBounds(10, 80, 65, 20);
		panel_2.add(label_34);

		textField_18 = new JTextField();
		textField_18.setBounds(85, 80, 185, 21);
		panel_2.add(textField_18);
		textField_18.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("BorrowDate");
		lblNewLabel_2.setBounds(10, 115, 65, 20);
		panel_2.add(lblNewLabel_2);

		textField_19 = new JTextField();
		textField_19.setBounds(84, 115, 131, 21);
		panel_2.add(textField_19);
		textField_19.setColumns(10);

		JLabel label_35 = new JLabel("format:2011/09/10");
		label_35.setBounds(244, 115, 113, 20);
		panel_2.add(label_35);

		JButton button_4 = new JButton("Lend Book");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("hit button lend");

				String card_id = textField_17.getText();
				String book_id = textField_18.getText();
				String borrow_date_str = textField_19.getText();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				try {
					date = sdf.parse(borrow_date_str);
				} catch (Exception e) {
					e.printStackTrace();
				}

				boolean re = false;
				if (card_id.length() <= 0) {
					JOptionPane.showMessageDialog(null, "Card_id short");
				} else if (card_id.length() > 6) {
					JOptionPane.showMessageDialog(null, "Card_id nlong");
				} else if (!admin.isUserExist(card_id)) {
					JOptionPane.showMessageDialog(null, "not exist Card_id");
				}
				if (book_id.length() <= 0) {
					JOptionPane.showMessageDialog(null, "book_id short");
				} else if (book_id.length() > 16) {
					JOptionPane.showMessageDialog(null, "book_id too long");
				} else if (!admin.isBookExist(book_id)) {
					JOptionPane.showMessageDialog(null, "not book with this id");
				} else if (borrow_date_str.length() <= 0) {
					JOptionPane.showMessageDialog(null, "date short");
				} else if (!sdf.format(date).equalsIgnoreCase(borrow_date_str)) {
					JOptionPane.showMessageDialog(null, "date format incorrect");
				} else if (!admin.isBookInLib(book_id)) {
					JOptionPane.showMessageDialog(null, "this book no longer in Library");
				} else if (admin.getBorrowNumber(card_id) >= admin.MAX_BORROW_NUMBER) {
					JOptionPane.showMessageDialog(null, "Already over borrow time");
				} else {
					java.sql.Date borrow_date = new java.sql.Date(date.getTime());
					re = admin.borrowBookFromLib(card_id, book_id, borrow_date);
				}

				if (re) {
					System.out.println("ok");
					JOptionPane.showMessageDialog(null, "borrow success");
				} else {
					System.out.println("error");
					JOptionPane.showMessageDialog(null, "borrow error");
				}
			}
		});
		button_4.setBounds(437, 117, 93, 23);
		panel_2.add(button_4);

		JLabel label_36 = new JLabel(
				"*******************************************************************************************************");
		label_36.setBounds(0, 200, 621, 20);
		panel_2.add(label_36);

		JLabel label_37 = new JLabel("Input");
		label_37.setFont(new Font("Dialog", Font.PLAIN, 15));
		label_37.setBounds(10, 230, 82, 25);
		panel_2.add(label_37);

		JLabel label_38 = new JLabel("Card_id");
		label_38.setBounds(10, 265, 82, 20);
		panel_2.add(label_38);

		textField_20 = new JTextField();
		textField_20.setBounds(85, 260, 120, 21);
		panel_2.add(textField_20);
		textField_20.setColumns(10);

		JLabel label_39 = new JLabel("Book_id");
		label_39.setBounds(10, 300, 65, 20);
		panel_2.add(label_39);

		textField_21 = new JTextField();
		textField_21.setBounds(85, 300, 185, 21);
		panel_2.add(textField_21);
		textField_21.setColumns(10);

		JLabel label_40 = new JLabel("ReturnDate");
		label_40.setBounds(10, 335, 65, 20);
		panel_2.add(label_40);

		textField_22 = new JTextField();
		textField_22.setBounds(85, 335, 130, 21);
		panel_2.add(textField_22);
		textField_22.setColumns(10);

		JLabel label_41 = new JLabel("format:2011/09/10");
		label_41.setBounds(244, 338, 113, 20);
		panel_2.add(label_41);

		JButton button_5 = new JButton("Return");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("hit button Return");

				String card_id = textField_20.getText();
				String book_id = textField_21.getText();
				String back_date_str = textField_22.getText();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				try {
					date = sdf.parse(back_date_str);
				} catch (Exception e) {
					e.printStackTrace();
				}

				boolean re = false;
				if (card_id.length() <= 0) {
					JOptionPane.showMessageDialog(null, "card_id short");
				} else if (card_id.length() > 6) {
					JOptionPane.showMessageDialog(null, "Card_id long");
				} else if (!admin.isUserExist(card_id)) {
					JOptionPane.showMessageDialog(null, "No user with this caid");
				}
				if (book_id.length() <= 0) {
					JOptionPane.showMessageDialog(null, "Book_id short");
				} else if (book_id.length() > 16) {
					JOptionPane.showMessageDialog(null, "Book_id long");
				} else if (!admin.isBookExist(book_id)) {
					JOptionPane.showMessageDialog(null, "No this book in Library");
				} else if (back_date_str.length() <= 0) {
					JOptionPane.showMessageDialog(null, "date no input");
				} else if (!sdf.format(date).equalsIgnoreCase(back_date_str)) {
					JOptionPane.showMessageDialog(null, "date not correct");
				} else if (admin.isBookInLib(book_id)) {
					JOptionPane.showMessageDialog(null, "No this book in Library");
				} else {
					java.sql.Date return_date = new java.sql.Date(date.getTime());
					// 还书事务
					re = admin.returnBookToLib(card_id, book_id, return_date);
				}

				if (re) {
					System.out.println("OK");
					JOptionPane.showMessageDialog(null, "return success");
				} else {
					System.out.println("error");
					JOptionPane.showMessageDialog(null, "Return error");
				}
			}

		});
		button_5.setBounds(437, 334, 93, 23);
		panel_2.add(button_5);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Recommend", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel label_42 = new JLabel("recommend Result");
		label_42.setFont(new Font("Dialog", Font.PLAIN, 15));
		label_42.setBounds(10, 10, 97, 20);
		panel_3.add(label_42);

		final JTable table = new JTable();
		table.setBounds(10, 84, 601, 332);
		panel_3.add(table);

		JButton button_6 = new JButton("Recommend");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("hit button recommend");

				Vector<Object> mheader1 = new Vector<Object>();
				Vector<Object> mv1;
				mv1 = admin.queryRecommend();
				mheader1.clear();
				mheader1.add("Card_id");
				mheader1.add("isbn");
				mheader1.add("title_name");
				mheader1.add("title_author");
				mheader1.add("title_press");
				mheader1.add("r_reason");
				table.setModel(new DefaultTableModel(mv1, mheader1));
			}
		});
		button_6.setBounds(10, 40, 142, 23);
		panel_3.add(button_6);

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Add To Pay", null, panel_4, null);
		panel_4.setLayout(null);

		JLabel label_43 = new JLabel("Card_id");
		label_43.setBounds(10, 50, 77, 20);
		panel_4.add(label_43);

		JLabel label_44 = new JLabel("Input");
		label_44.setFont(new Font("Dialog", Font.PLAIN, 15));
		label_44.setBounds(10, 10, 99, 28);
		panel_4.add(label_44);

		textField_23 = new JTextField();
		textField_23.setBounds(80, 50, 112, 21);
		panel_4.add(textField_23);
		textField_23.setColumns(10);

		JLabel label_45 = new JLabel("Book_id");
		label_45.setBounds(10, 85, 66, 20);
		panel_4.add(label_45);

		textField_24 = new JTextField();
		textField_24.setBounds(80, 85, 161, 21);
		panel_4.add(textField_24);
		textField_24.setColumns(10);

		JLabel label_46 = new JLabel("Reason");
		label_46.setBounds(10, 120, 77, 20);
		panel_4.add(label_46);

		textField_25 = new JTextField();
		textField_25.setBounds(80, 120, 271, 21);
		panel_4.add(textField_25);
		textField_25.setColumns(10);

		JLabel label_47 = new JLabel("Price");
		label_47.setBounds(10, 155, 66, 20);
		panel_4.add(label_47);

		textField_26 = new JTextField();
		textField_26.setBounds(80, 155, 66, 21);
		panel_4.add(textField_26);
		textField_26.setColumns(10);

		JLabel label_48 = new JLabel("dollar");
		label_48.setBounds(156, 155, 54, 20);
		panel_4.add(label_48);

		JLabel label_49 = new JLabel("PayDate");
		label_49.setBounds(10, 190, 66, 20);
		panel_4.add(label_49);

		textField_27 = new JTextField();
		textField_27.setBounds(80, 190, 112, 21);
		panel_4.add(textField_27);
		textField_27.setColumns(10);

		JLabel label_50 = new JLabel("format:2010/09/10");
		label_50.setBounds(202, 190, 199, 20);
		panel_4.add(label_50);

		JButton button_7 = new JButton("AddToPay");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("hit button add");

				String card_id = textField_23.getText();
				String book_id = textField_24.getText();
				String r_reason = textField_25.getText();
				String pay_price = textField_26.getText();
				String pay_date_str = textField_27.getText();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				try {
					date = sdf.parse(pay_date_str);
				} catch (Exception e) {
					e.printStackTrace();
				}

				boolean re = false;
				if (card_id.length() <= 0) {
					JOptionPane.showMessageDialog(null, "Card_id short");
				} else if (card_id.length() != 6) {
					JOptionPane.showMessageDialog(null, "Card_id must length 6");
				} else if (book_id.length() <= 0) {
					JOptionPane.showMessageDialog(null, "Book_id short");
				} else if (book_id.length() != 16) {
					JOptionPane.showMessageDialog(null, "Book_id must length 16");
				} else if (r_reason.length() <= 0) {
					JOptionPane.showMessageDialog(null, "Reason short");
				} else if (r_reason.length() > 200) {
					JOptionPane.showMessageDialog(null, "Reason too long");
				} else if (pay_price.length() <= 0) {
					JOptionPane.showMessageDialog(null, "price short");
				} else if (pay_date_str.length() <= 0) {
					JOptionPane.showMessageDialog(null, "date short");
				} else if (!sdf.format(date).equalsIgnoreCase(pay_date_str)) {
					JOptionPane.showMessageDialog(null, "date format incorrect");
				} else {
					java.sql.Date pay_date = new java.sql.Date(date.getTime());

					re = admin.pay(card_id, book_id, r_reason, pay_price, pay_date);
				}

				if (re) {
					System.out.println("Ok");
					JOptionPane.showMessageDialog(null, "successfully add to pay");
				} else {
					System.out.println("error");
					JOptionPane.showMessageDialog(null, "add to pay error");
				}
			}
		});
		button_7.setBounds(10, 243, 93, 23);
		panel_4.add(button_7);
	}

}
