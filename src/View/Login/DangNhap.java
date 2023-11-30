package View.Login;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.MetalButtonUI;

import Model.connectSQL;
import View.MenuChinh;
import View.Custom.Custom_list;

public class DangNhap extends JFrame implements KeyListener {

	public static Connection con;
	private JPanel contentPane;
	private CardLayout card;
	private JPanel panel;
	private JPanel panel_1;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	private JLabel thongbaoLB;
	private JButton btnlogin;
	public static ResultSet rs_taikhoan;

	public static boolean action_quanlynhanvien;
	public static boolean action_them;
	public static boolean action_sua;
	public static boolean action_xoa;
	public static boolean action_nhapfile;
	public static boolean action_xuatfile;
	public static boolean action_banhang;

	private JPasswordField pwd;
	private JTextField textField_servername;
	private JTextField textField_login;
	private JButton btnConnect;
	private JComboBox<String> comboBox_authentication;
	private JButton btncencel;
	private JLabel lb_quaylai;
	private JPanel pn_dangnhap;
	private JPanel pn_ketnoi;
	private JLabel lbdatabaseName;
	private JTextField textField_databaseName;
	private JPopupMenu popupMenu;
	private Custom_list server;
	private JScrollPane scrollPane_serverName;
	private JList<String> list_serverName;
	private JList list_databaseName;
	private JPopupMenu popupMenu_dataName;
	private Custom_list dataName;

	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {

						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (Exception e) {
						System.out.println("Look and Feel not set");
					}

					setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DangNhap() {
		// khoi tao gia tri
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				action_them = true;
				action_sua = true;
				action_xoa = true;
				action_nhapfile = true;
				action_xuatfile = true;
				action_banhang = true;
				action_quanlynhanvien = true;
				comboBox_authentication.setSelectedItem("Windows Authentication");
				textField_servername.setText(server.getDefault().getElementAt(server.getDefault().getSize() - 1));
				textField_databaseName.setText(dataName.getDefault().getElementAt(dataName.getDefault().getSize() - 1));
			}
		});
		GUI();
	}

	private void GUI() {
		setResizable(false);
		setTitle("Đăng nhập\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 479);

		card = new CardLayout();
		contentPane = new JPanel(card);
		setContentPane(contentPane);

		pn_ketnoi = new JPanel();
		contentPane.add(pn_ketnoi, "ketnoi");
		pn_ketnoi.setLayout(null);

		JLabel lblDatabase = new JLabel("Kết nối CSDL");
		lblDatabase.setOpaque(true);
		lblDatabase.setForeground(new Color(22, 27, 33));
		lblDatabase.setFont(new Font("Arial", Font.BOLD, 41));
		lblDatabase.setBounds(12, 13, 500, 56);
		pn_ketnoi.add(lblDatabase);

		JLabel lblNewLabel_1 = new JLabel("Sever name:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(41, 105, 105, 25);
		pn_ketnoi.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Authentication:");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(41, 154, 105, 25);
		pn_ketnoi.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Login:");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(41, 203, 105, 25);
		pn_ketnoi.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Password:");
		lblNewLabel_1_1_2.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_1_1_2.setBounds(41, 252, 105, 25);
		pn_ketnoi.add(lblNewLabel_1_1_2);

		comboBox_authentication = new JComboBox();
		comboBox_authentication.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				authentication();
			}
		});
		comboBox_authentication.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_authentication.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Windows Authentication", "SQL Sever Authentication" }));
		comboBox_authentication.setBounds(187, 154, 304, 25);
		pn_ketnoi.add(comboBox_authentication);

		btncencel = new JButton("Cancel");
		btncencel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btncencel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btncencel.setBounds(280, 375, 97, 25);
		pn_ketnoi.add(btncencel);

		btnConnect = new JButton("Connect");
		btnConnect.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLUE, null));
		btnConnect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ketnoidatabase();
				MenuChinh menuchinh = null;
				if (con != null) {
					try {
						menuchinh = new MenuChinh();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					menuchinh.run();
				}
			}
		});
		btnConnect.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConnect.setBounds(150, 375, 97, 25);
		pn_ketnoi.add(btnConnect);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(43, 356, 448, 2);
		pn_ketnoi.add(separator_2);

		pwd = new JPasswordField();
		pwd.addKeyListener(this);
		pwd.setPreferredSize(new Dimension(6, 25));
		pwd.setEchoChar('*');
		pwd.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLUE, null));
		pwd.setFont(new Font("Tahoma", Font.BOLD, 15));
		pwd.setBounds(187, 252, 304, 25);
		pn_ketnoi.add(pwd);

		textField_servername = new JTextField("");
		textField_servername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					popupMenu.show(textField_servername, 0, textField_servername.getHeight());
				}
			}
		});
		textField_servername.addKeyListener(this);
		textField_servername.setFont(new Font("Arial", Font.PLAIN, 16));
		textField_servername.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLUE, null));
		textField_servername.setBounds(187, 107, 304, 25);
		pn_ketnoi.add(textField_servername);
		textField_servername.setColumns(10);

		popupMenu = new JPopupMenu();
		popupMenu.setFocusable(false);
		popupMenu.setPreferredSize(new Dimension(textField_servername.getWidth(), 100));
		popupMenu.setBorder(null);
		/*
		 * them popupmenu cho server name
		 */
		scrollPane_serverName = new JScrollPane();
		popupMenu.add(scrollPane_serverName);

		server = new Custom_list("serverName.txt");
		server.getdata();
		list_serverName = server;
		list_serverName.setFont(new Font("arial", Font.PLAIN, 14));
		list_serverName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
					textField_servername.setText(list_serverName.getSelectedValue());
					popupMenu.setVisible(false);
				}
			}
		});
		scrollPane_serverName.setViewportView(list_serverName);

		textField_login = new JTextField();
		textField_login.addKeyListener(this);
		textField_login.setFont(new Font("Arial", Font.PLAIN, 16));
		textField_login.setColumns(10);
		textField_login.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLUE, null));
		textField_login.setBounds(187, 203, 304, 25);
		pn_ketnoi.add(textField_login);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setBounds(43, 82, 448, 2);
		pn_ketnoi.add(separator_2_1);

		lbdatabaseName = new JLabel("DatebaseName:");
		lbdatabaseName.setFont(new Font("Arial", Font.PLAIN, 16));
		lbdatabaseName.setBounds(41, 301, 115, 25);
		pn_ketnoi.add(lbdatabaseName);

		textField_databaseName = new JTextField();
		textField_databaseName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
					popupMenu_dataName.show(textField_databaseName, 0, textField_databaseName.getHeight());

				}
			}
		});
		textField_databaseName.addKeyListener(this);
		textField_databaseName.setFont(new Font("Arial", Font.PLAIN, 16));
		textField_databaseName.setColumns(10);
		textField_databaseName.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLUE, null));
		textField_databaseName.setBounds(187, 301, 304, 25);
		pn_ketnoi.add(textField_databaseName);

		popupMenu_dataName = new JPopupMenu();
		popupMenu_dataName.setFocusable(false);
		popupMenu_dataName.setPreferredSize(new Dimension(textField_databaseName.getWidth(), 80));

		JScrollPane scrollPane = new JScrollPane();
		popupMenu_dataName.add(scrollPane);

		dataName = new Custom_list("databaseName.txt");
		dataName.getdata();
		list_databaseName = dataName;
		list_databaseName.setFont(new Font("arial", Font.PLAIN, 14));
		list_databaseName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField_databaseName.setText((String) list_databaseName.getSelectedValue());
				popupMenu_dataName.setVisible(false);
			}
		});
		scrollPane.setViewportView(list_databaseName);

	}

	
	// select authentication
	private void authentication() {
		int index = comboBox_authentication.getSelectedIndex();
		if (index == 0) {
			textField_login.setEditable(false);
			pwd.setEditable(false);

		} else if (index == 1) {
			textField_login.setEditable(true);
			pwd.setEditable(true);
		}
	}

	public void ketnoidatabase() {
		String serverName = textField_servername.getText().trim();
		String databaseName = textField_databaseName.getText().trim();
		String login = textField_login.getText().trim();
		String pass = pwd.getText();
		connectSQL c = null;
		if (comboBox_authentication.getSelectedIndex() == 0) {
			c = new connectSQL(serverName, databaseName);
		} else if (comboBox_authentication.getSelectedIndex() == 1) {
			c = new connectSQL(serverName, databaseName, login, pass);
		}
		con = c.connect();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	private void showlayout(String name) {
		card.show(contentPane, name);
	}

}
