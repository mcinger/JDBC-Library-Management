import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
/*
 * @ myZhou, qzDong
 */

public class UserFrame extends JFrame {

	final User user;
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	private JTable table;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;

	Image icon;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// UserFrame frame = new UserFrame();
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UserFrame(User user) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 542);

		this.user = user;
		try {
			icon = ImageIO.read(new File("icon.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setIconImage(icon);

		this.setTitle("user window");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("BorrowAgain", null, panel, null);
		panel.setLayout(null);

		JLabel label = new JLabel("Card id");
		label.setBounds(10, 20, 68, 20);
		panel.add(label);

		JLabel label_1 = new JLabel("Card_id");
		label_1.setBounds(88, 23, 200, 15);
		panel.add(label_1);

		JLabel label_2 = new JLabel("User name");
		label_2.setBounds(10, 55, 68, 20);
		panel.add(label_2);

		JLabel label_3 = new JLabel("User_name");
		label_3.setBounds(88, 58, 200, 15);
		panel.add(label_3);

		JLabel lblNewLabel = new JLabel("User id");
		lblNewLabel.setBounds(10, 90, 99, 20);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("user_id");
		lblNewLabel_1.setBounds(111, 93, 200, 15);
		panel.add(lblNewLabel_1);

		JLabel label_4 = new JLabel("Card date");
		label_4.setBounds(10, 125, 60, 20);
		panel.add(label_4);

		JLabel label_5 = new JLabel("Card_date");
		label_5.setBounds(111, 125, 200, 20);
		panel.add(label_5);

		JLabel label_6 = new JLabel("Card time");
		label_6.setBounds(10, 160, 84, 20);
		panel.add(label_6);

		JLabel label_7 = new JLabel("Card_time");
		label_7.setBounds(127, 160, 200, 20);
		panel.add(label_7);

		JLabel label_8 = new JLabel("User phone");
		label_8.setBounds(10, 195, 99, 20);
		panel.add(label_8);

		JLabel lblNewLabel_2 = new JLabel("User_phone");
		lblNewLabel_2.setBounds(106, 198, 200, 20);
		panel.add(lblNewLabel_2);

		label_1.setText(user.getCard_id());
		label_3.setText(user.getUser_name());
		lblNewLabel_1.setText(user.getUser_id());
		label_5.setText(user.getCard_date());
		label_7.setText(user.getCard_time());
		lblNewLabel_2.setText(user.getUser_phone());

		table = new JTable();
		table.setBounds(10, 252, 606, 158);
		panel.add(table);

		setTable();

		JLabel label_12 = new JLabel("book_id");
		label_12.setBounds(10, 420, 144, 20);
		panel.add(label_12);

		textField = new JTextField();
		textField.setBounds(134, 420, 154, 21);
		panel.add(textField);
		textField.setColumns(10);

		JLabel label_20 = new JLabel("date");
		label_20.setBounds(295, 423, 68, 17);
		panel.add(label_20);

		textField_7 = new JTextField();
		textField_7.setBounds(367, 420, 99, 21);
		panel.add(textField_7);
		textField_7.setColumns(10);

		JButton button_1 = new JButton("borrow again");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String book_id = textField.getText();
				String date_str = textField_7.getText();

				// 处理办卡日期格式
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				try {
					date = sdf.parse(date_str);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (book_id.length() <= 0) {
					JOptionPane.showMessageDialog(null, "not enough input");
				} else if (date_str.length() <= 0) {
					JOptionPane.showMessageDialog(null, "date short");
				} else if (!sdf.format(date).equalsIgnoreCase(date_str)) {
					JOptionPane.showMessageDialog(null, "date format incorrect");
				} else if (UserFrame.this.user.getBorrowTimes(book_id) > 0
						&& UserFrame.this.user.getBorrowTimes(book_id) < User.MAX_BORROW_TIMES) {
					java.sql.Date borrow_date = new java.sql.Date(date.getTime());
					UserFrame.this.user.borrowAgain(borrow_date, UserFrame.this.user.getCard_id(), book_id);
					JOptionPane.showMessageDialog(null, "borrow ok");

					setTable();
				} else if (UserFrame.this.user.getBorrowTimes(book_id) >= User.MAX_BORROW_TIMES) {
					JOptionPane.showMessageDialog(null, "time over");
				} else if (UserFrame.this.user.getBorrowTimes(book_id) == -1) {
					JOptionPane.showMessageDialog(null, "not in store");
				}
			}
		});
		button_1.setBounds(523, 419, 93, 23);
		panel.add(button_1);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Query", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel label_9 = new JLabel("Query");
		label_9.setFont(new Font("Dialog", Font.PLAIN, 15));
		label_9.setBounds(10, 10, 91, 25);
		panel_1.add(label_9);

		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(112, 45, 151, 21);
		comboBox.addItem("title_name");
		comboBox.addItem("author_name");
		comboBox.addItem("keyword");
		comboBox.addItem("isbn");
		comboBox.addItem("title_price");
		panel_1.add(comboBox);

		JLabel label_10 = new JLabel("selection");
		label_10.setBounds(10, 45, 75, 20);
		panel_1.add(label_10);

		textField_1 = new JTextField();
		textField_1.setBounds(112, 80, 185, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);

		JLabel label_11 = new JLabel("input");
		label_11.setBounds(10, 80, 75, 20);
		panel_1.add(label_11);

		final JTable table_1 = new JTable();
		table_1.setBounds(11, 175, 605, 236);
		panel_1.add(table_1);

		JButton button = new JButton("query");

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = comboBox.getSelectedIndex();

				if (i == 0) {
					// 按图书名称查询
					String title_name = textField_1.getText();

					if (title_name.length() <= 0) {
						JOptionPane.showMessageDialog(null, "title_name short");
					} else {
						Vector<Object> mheader = new Vector();
						Vector<Object> mv;

						mv = UserFrame.this.user.queryByTitleName(title_name);
						mheader.clear();
						mheader = new Vector();
						mheader.add("isbn");
						mheader.add("title_name");
						mheader.add("title_press");
						mheader.add("title_date");
						mheader.add("title_price");
						mheader.add("title_booknumber");
						mheader.add("title_currentnumber");

						table_1.setModel(new DefaultTableModel(mv, mheader));
					}
				} else if (i == 1) {
					// 按作者姓名查询
					String author_name = textField_1.getText();

					if (author_name.length() <= 0) {
						JOptionPane.showMessageDialog(null, "author short");
					} else {
						Vector<Object> mheader = new Vector();
						Vector<Object> mv;

						mv = UserFrame.this.user.queryByAuthorName(author_name);
						mheader.clear();
						mheader = new Vector();
						mheader = new Vector();
						mheader.add("isbn");
						mheader.add("title_name");
						mheader.add("title_press");
						mheader.add("title_date");
						mheader.add("title_price");
						mheader.add("title_booknumber");
						mheader.add("title_currentnumber");

						table_1.setModel(new DefaultTableModel(mv, mheader));
					}
				} else if (i == 2) {
					// 按关键词查询
					String keyword = textField_1.getText();

					if (keyword.length() <= 0) {
						JOptionPane.showMessageDialog(null, "keyword short");
					} else {
						Vector<Object> mheader = new Vector();
						Vector<Object> mv;

						mv = UserFrame.this.user.queryByKeyword(keyword);
						mheader.clear();
						mheader = new Vector();
						mheader = new Vector();
						mheader.add("isbn");
						mheader.add("title_name");
						mheader.add("title_press");
						mheader.add("title_date");
						mheader.add("title_price");
						mheader.add("title_booknumber");
						mheader.add("title_currentnumber");

						table_1.setModel(new DefaultTableModel(mv, mheader));
					}
				} else if (i == 3) {
					// 按isbn查询
					String isbn = textField_1.getText();

					if (isbn.length() <= 0) {
						JOptionPane.showMessageDialog(null, "isbn short");
					} else {
						Vector<Object> mheader = new Vector();
						Vector<Object> mv;

						mv = UserFrame.this.user.queryByISBN(isbn);
						mheader.clear();
						mheader = new Vector();
						mheader = new Vector();
						mheader.add("isbn");
						mheader.add("title_name");
						mheader.add("title_press");
						mheader.add("title_date");
						mheader.add("title_price");
						mheader.add("title_booknumber");
						mheader.add("title_currentnumber");

						table_1.setModel(new DefaultTableModel(mv, mheader));
					}
				} else if (i == 4) {
					// 按出版社查询
					String title_press = textField_1.getText();

					if (title_press.length() <= 0) {
						JOptionPane.showMessageDialog(null, "press length short");
					} else {
						Vector<Object> mheader = new Vector();
						Vector<Object> mv;

						mv = UserFrame.this.user.queryByPress(title_press);
						mheader.clear();
						mheader = new Vector();
						mheader = new Vector();
						mheader.add("isbn");
						mheader.add("title_name");
						mheader.add("title_press");
						mheader.add("title_date");
						mheader.add("title_price");
						mheader.add("title_booknumber");
						mheader.add("title_currentnumber");

						table_1.setModel(new DefaultTableModel(mv, mheader));
					}
				}
			}
		});
		button.setBounds(10, 121, 93, 23);
		panel_1.add(button);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Recommed", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel label_13 = new JLabel("input");
		label_13.setFont(new Font("Dialog", Font.PLAIN, 15));
		label_13.setBounds(10, 10, 75, 23);
		panel_2.add(label_13);

		JLabel lblisbn = new JLabel("isbn");// \u56FE\u4E66isbn\uFF1A
		lblisbn.setBounds(10, 40, 60, 20);
		panel_2.add(lblisbn);

		textField_2 = new JTextField();
		textField_2.setBounds(80, 40, 148, 21);
		panel_2.add(textField_2);
		textField_2.setColumns(10);

		JLabel label_14 = new JLabel("13 number");
		label_14.setBounds(238, 43, 88, 20);
		panel_2.add(label_14);

		JLabel label_15 = new JLabel("title_name");
		label_15.setBounds(10, 75, 60, 20);
		panel_2.add(label_15);

		textField_3 = new JTextField();
		textField_3.setBounds(80, 75, 148, 21);
		panel_2.add(textField_3);
		textField_3.setColumns(10);

		JLabel label_16 = new JLabel("title_press");
		label_16.setBounds(10, 110, 54, 20);
		panel_2.add(label_16);

		textField_4 = new JTextField();
		textField_4.setBounds(80, 110, 148, 21);
		panel_2.add(textField_4);
		textField_4.setColumns(10);

		JLabel label_17 = new JLabel("Reason");
		label_17.setBounds(10, 180, 75, 20);
		panel_2.add(label_17);

		textField_5 = new JTextField();
		textField_5.setBounds(79, 180, 472, 168);
		panel_2.add(textField_5);
		textField_5.setColumns(10);

		JLabel label_18 = new JLabel("title_author");
		label_18.setBounds(10, 145, 54, 15);
		panel_2.add(label_18);

		textField_6 = new JTextField();
		textField_6.setBounds(78, 145, 200, 21);
		panel_2.add(textField_6);
		textField_6.setColumns(10);

		JButton button_2 = new JButton("recommendBook");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String isbn = textField_2.getText();
				String title_name = "" + textField_3.getText();
				String title_press = "" + textField_4.getText();
				String title_author = textField_6.getText();
				String r_reason = textField_5.getText();

				if (title_name.length() <= 0) {
					JOptionPane.showMessageDialog(null, "name short");
				} else if (r_reason.length() <= 0) {
					JOptionPane.showMessageDialog(null, "no reason");
				} else if (title_author.length() < 0) {
					JOptionPane.showMessageDialog(null, "author short");
				} else {
					if (UserFrame.this.user.recommendBook(isbn, title_name, title_author, title_press, r_reason)) {
						JOptionPane.showMessageDialog(null, "recommed ok");
					}
				}
			}
		});
		button_2.setBounds(427, 400, 93, 23);
		panel_2.add(button_2);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("look Recommend", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel label_19 = new JLabel("query");
		label_19.setFont(new Font("Dialog", Font.PLAIN, 15));
		label_19.setBounds(10, 10, 145, 26);
		panel_3.add(label_19);

		final JTable table_3 = new JTable();
		table_3.setBounds(20, 107, 596, 296);
		panel_3.add(table_3);

		JButton button_3 = new JButton("query recommend");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 查看我的推荐信息

				Vector<Object> mheader = new Vector();
				Vector<Object> mv;

				mv = UserFrame.this.user.queryMyRecommend();
				mheader.clear();
				mheader = new Vector();
				mheader.add("isbn");
				mheader.add("title_name");
				mheader.add("title_author");
				mheader.add("title_press");
				mheader.add("r_reason");

				table_3.setModel(new DefaultTableModel(mv, mheader));
			}
		});
		button_3.setBounds(30, 46, 93, 23);
		panel_3.add(button_3);

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Feedback", null, panel_4, null);
		panel_4.setLayout(null);

		JLabel label_21 = new JLabel("Input");
		label_21.setFont(new Font("Dialog", Font.PLAIN, 15));
		label_21.setBounds(10, 10, 82, 24);
		panel_4.add(label_21);

		JLabel label_22 = new JLabel("title_name");
		label_22.setBounds(10, 45, 68, 20);
		panel_4.add(label_22);

		textField_8 = new JTextField();
		textField_8.setBounds(88, 45, 166, 21);
		panel_4.add(textField_8);
		textField_8.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("date");
		lblNewLabel_3.setBounds(10, 80, 82, 20);
		panel_4.add(lblNewLabel_3);

		textField_9 = new JTextField();
		textField_9.setBounds(87, 80, 166, 21);
		panel_4.add(textField_9);
		textField_9.setColumns(10);

		JLabel label_23 = new JLabel("format:2011/09/10");
		label_23.setBounds(275, 80, 110, 20);
		panel_4.add(label_23);

		JLabel label_24 = new JLabel("content");
		label_24.setBounds(10, 115, 54, 20);
		panel_4.add(label_24);

		final JTextArea textArea = new JTextArea();
		textArea.setBounds(88, 155, 401, 200);
		panel_4.add(textArea);

		JButton button_5 = new JButton("Add comment");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String title_name = textField_8.getText();
				String comment_date_str = textField_9.getText();
				String comment_content = textArea.getText();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				try {
					date = sdf.parse(comment_date_str);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (title_name.length() <= 0) {
					JOptionPane.showMessageDialog(null, "name short");
				} else if (comment_date_str.length() <= 0) {
					JOptionPane.showMessageDialog(null, "date short");
				} else if (!sdf.format(date).equalsIgnoreCase(comment_date_str)) {
					JOptionPane.showMessageDialog(null, "date formast not correct");
				} else if (comment_content.length() <= 0) {
					JOptionPane.showMessageDialog(null, "comment short");
				} else if (comment_content.length() > 1000) {
					JOptionPane.showMessageDialog(null, "comment long");
				} else {
					java.sql.Date comment_date = new java.sql.Date(date.getTime());
					boolean re = UserFrame.this.user.addToComment(UserFrame.this.user.getCard_id(), title_name,
							comment_content, comment_date);
					if (re) {
						JOptionPane.showMessageDialog(null, "add comment ok");
					}
				}
			}
		});
		button_5.setBounds(425, 392, 93, 23);
		panel_4.add(button_5);

		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Query Comment", null, panel_5, null);
		panel_5.setLayout(null);

		JLabel label_25 = new JLabel("Hit button below");
		label_25.setFont(new Font("Dialog", Font.PLAIN, 15));
		label_25.setBounds(10, 10, 86, 28);
		panel_5.add(label_25);

		JLabel label_26 = new JLabel("title_name");
		label_26.setBounds(191, 80, 78, 20);
		panel_5.add(label_26);

		textField_10 = new JTextField();
		textField_10.setBounds(267, 80, 169, 21);
		panel_5.add(textField_10);
		textField_10.setColumns(10);

		final JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(10, 289, 595, 152);
		panel_5.add(textArea_1);

		final JTable table_4 = new JTable();
		table_4.setBounds(10, 130, 606, 121);
		panel_5.add(table_4);

		JLabel label_27 = new JLabel("By name Result");
		label_27.setBounds(10, 260, 86, 20);
		panel_5.add(label_27);

		JButton button_6 = new JButton("query comment");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Vector<Object> mheader = new Vector();
				final Vector<Object> mv = UserFrame.this.user.queryMyComment();
				mheader.clear();
				mheader = new Vector();
				mheader.add("card_id");
				mheader.add("title_name");
				mheader.add("comment_date");
				mheader.add("comment_content");

				table_4.setModel(new DefaultTableModel(mv, mheader));
				table_4.addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent e) {

						if (e.getClickCount() == 1) {
							textArea_1.setText(((Vector) mv.get(table_4.getSelectedRow())).get(3).toString());
						}
					}
				});
			}
		});
		button_6.setBounds(3, 48, 126, 23);
		panel_5.add(button_6);

		JButton button_7 = new JButton("By name");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title_name = textField_10.getText();

				if (title_name.length() <= 0) {
					JOptionPane.showMessageDialog(null, "name short");
				} else {
					Vector<Object> mheader = new Vector();
					final Vector<Object> mv = UserFrame.this.user.queryComment(title_name);
					mheader.clear();
					mheader = new Vector();
					mheader.add("card_id");
					mheader.add("title_name");
					mheader.add("comment_date");
					mheader.add("comment_content");

					table_4.setModel(new DefaultTableModel(mv, mheader));
					table_4.addMouseListener(new MouseAdapter() {

						public void mouseClicked(MouseEvent e) {

							if (e.getClickCount() == 1) {
								textArea_1.setText(((Vector) mv.get(table_4.getSelectedRow())).get(3).toString());
							}
						}
					});
				}
			}
		});
		button_7.setBounds(192, 48, 126, 23);
		panel_5.add(button_7);

		List overdue = user.queryOverDue();

		for (int i = 0; i < overdue.size(); i++) {
			JOptionPane.showMessageDialog(null, "name with" + overdue.get(i) + "is overdue");
		}

		List message = user.queryMessage();
		for (int i = 0; i < message.size(); i++) {
			JOptionPane.showMessageDialog(null, message.get(i));
		}
		user.removeFromMessage(user.getCard_id());

	}

	private void setTable() {
		Vector<Object> mheader1 = new Vector();
		Vector<Object> mv1;

		mv1 = user.queryBorrow(user.getCard_id());
		mheader1.clear();
		mheader1 = new Vector();
		mheader1.add("title_name");
		mheader1.add("book_id");
		mheader1.add("borrow_date");
		mheader1.add("borrow_times;");

		table.setModel(new DefaultTableModel(mv1, mheader1));
	}
}
