import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/*
 * @ myZhou, qzDong
 */

public class Welcome extends JFrame implements ActionListener {

	private static final int WIDTH = 600, HEIGHT = 500;
	// size
	private int WinWidth, WinHeight;

	Image welImage, icon;
	JPanel downPanel, cenPanel;
	ImagePanel upPanel;

	JButton logon, quit;

	JTextField name;
	JPasswordField psdText;

	public Welcome() {
		WinWidth = Toolkit.getDefaultToolkit().getScreenSize().width; // Width
		WinHeight = Toolkit.getDefaultToolkit().getScreenSize().height; // Height

		this.getContentPane().setLayout(null);

		this.setTitle("Entrance Window");
		this.setSize(WIDTH, HEIGHT);

		this.setLocation((WinWidth - WIDTH) / 2, (WinHeight - HEIGHT) / 2);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			icon = ImageIO.read(new File("icon.jpg"));
			welImage = ImageIO.read(new File("wel.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		upPanel = new ImagePanel(welImage);
		cenPanel = new JPanel();
		downPanel = new JPanel();

		upPanel.setBounds(0, 0, 600, 200);

		JLabel username = new JLabel("username");
		username.setBounds(150, 50, 100, 10);
		JLabel password = new JLabel("password");
		password.setBounds(150, 100, 100, 10);
		name = new JTextField();
		name.setBounds(250, 50, 100, 20);
		psdText = new JPasswordField();
		psdText.setBounds(250, 100, 100, 20);
		cenPanel.add(username);
		cenPanel.add(name);
		cenPanel.add(password);
		cenPanel.add(psdText);
		cenPanel.setLayout(null);
		cenPanel.setBounds(0, 200, 600, 200);

		logon = new JButton("enter");
		quit = new JButton("exit");
		logon.addActionListener(this);
		quit.addActionListener(this);

		downPanel.add(logon);
		downPanel.add(quit);
		downPanel.setBounds(0, 400, 600, 500);

		this.add(upPanel);
		this.add(cenPanel);
		this.add(downPanel);

		this.setIconImage(icon);
	}

	class ImagePanel extends JPanel {
		Image image;

		public ImagePanel(Image image) {
			this.image = image;
			this.setBounds(0, 0, image.getWidth(this), image.getHeight(this));
			System.out.println(image.getWidth(this) + "" + image.getHeight(this));
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, this);
		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("enter")) {

			// Get the name and password
			String nameStr = name.getText();
			String psd = psdText.getText();

			System.out.println("sucess with name" + nameStr);
			if (nameStr.length() <= 0) {
				// Empty input
				JOptionPane.showMessageDialog(null, "not enough input");
			}

			LibConnection conn = new LibConnection();

			// LibConnection libCon = new LibConnection();
			// Connection conn = libCon.getConn();
			// AdminFrame mainFrame = new AdminFrame();
			// mainFrame.setVisible(true);

			if (conn.isAdmin(nameStr, psd)) {
				System.out.println("success: admin");

				Administrator admin = new Administrator(conn, nameStr);
				AdminFrame mainFrame = new AdminFrame(admin);
				mainFrame.setVisible(true);
				this.dispose();
			} else if (conn.isUser(nameStr, psd)) {
				System.out.println("success: user");
				// Normal user
				User user = new User(nameStr, conn);
				UserFrame mainFrame = new UserFrame(user);
				mainFrame.setVisible(true);
				this.dispose();
				// Administrator admin = new Administrator(conn, nameStr);
				// AdminFrame mainFrame = new AdminFrame();
				// mainFrame.setVisible(true);
				// this.dispose();
			} else {
				// Invalid information
				JOptionPane.showMessageDialog(null, "wrong username or password");
			}

		} else if (e.getActionCommand().equals("exit")) {
			System.exit(0);
		}

	}

}
