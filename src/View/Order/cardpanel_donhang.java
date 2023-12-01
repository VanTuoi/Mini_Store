package View.Order;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import View.Login.DangNhap;

public class cardpanel_donhang extends JPanel {
	private static final long serialVersionUID = 1L;
	static int index;
	private JTable table_DH;
	static JPanel pn_center;
	private JTextField textField_thanhtimkiem;
	private JComboBox comboBox;
	
	private JLabel lbrefresh;
	private JButton btnxoa_donhang;
	private JButton btnnhapfile;
	private JButton btnxuatfile;
	

	public cardpanel_donhang() {
		GUI();
		showDH();
	}

	private void GUI() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 20));

		lbrefresh = new JLabel("Refresh");
		lbrefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					refresh();
				}
			}
		});
		lbrefresh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbrefresh.setIconTextGap(10);
		lbrefresh.setIcon(new ImageIcon("Imager\\refresh-25.png"));
		lbrefresh.setFont(new Font("Arial", Font.PLAIN, 22));
		panel.add(lbrefresh);

		btnxoa_donhang = new JButton("Xóa");
		btnxoa_donhang.setEnabled(DangNhap.action_xoa);
		btnxoa_donhang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnxoa_donhang.setIcon(new ImageIcon("Imager\\xoa-25.png"));
		btnxoa_donhang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "tính năng đang phát triển");
			}
		});
		btnxoa_donhang.setBorder(null);
		btnxoa_donhang.setBorderPainted(false);
		btnxoa_donhang.setContentAreaFilled(false);
		btnxoa_donhang.setIconTextGap(10);
		btnxoa_donhang.setFont(new Font("Arial", Font.PLAIN, 22));
		panel.add(btnxoa_donhang);

		btnnhapfile = new JButton("Nhập File");
		btnnhapfile.setBorder(null);
		btnnhapfile.setBorderPainted(false);
		btnnhapfile.setContentAreaFilled(false);
		btnnhapfile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "tính năng đang phát triển");
			}
		});
		btnnhapfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnnhapfile.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnnhapfile.setIconTextGap(10);
		btnnhapfile.setIcon(new ImageIcon("Imager\\nhapfile-30.png"));
		btnnhapfile.setFont(new Font("Arial", Font.PLAIN, 22));
		panel.add(btnnhapfile);

		
		btnxuatfile = new JButton("Xuất File");
		btnxuatfile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "tính năng đang phát triển");
			}
		});
		btnxuatfile.setBorder(null);
		btnxuatfile.setBorderPainted(false);
		btnxuatfile.setContentAreaFilled(false);
		btnxuatfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnxuatfile.setIcon(new ImageIcon("Imager\\xuatfile2-30.png"));
		btnxuatfile.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnxuatfile.setFont(new Font("Arial", Font.PLAIN, 22));
		panel.add(btnxuatfile);

//		add(tabbedPane, BorderLayout.CENTER);

		pn_center = new JPanel(new BorderLayout());
		add(pn_center, BorderLayout.CENTER);
		
		JPanel pn_thanhtimkiem = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 10));
		pn_center.add(pn_thanhtimkiem, BorderLayout.NORTH);
		pn_thanhtimkiem.setBackground(Color.WHITE);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Imager\\timkiem-25.png"));
		pn_thanhtimkiem.add(lblNewLabel);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setBorder(null);
		pn_thanhtimkiem.add(splitPane);

		textField_thanhtimkiem = new JTextField();
		textField_thanhtimkiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					JOptionPane.showMessageDialog(null, "tính năng đang phát triển");
				}
			}
		});
		textField_thanhtimkiem.setFont(new Font("Arial", Font.PLAIN, 20));
		splitPane.setLeftComponent(textField_thanhtimkiem);
		textField_thanhtimkiem.setColumns(30);

		comboBox = new JComboBox();
		comboBox.setBorder(UIManager.getBorder("TextField.border"));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "MaHD"}));
		comboBox.setFont(new Font("Arial", Font.PLAIN, 17));
		comboBox.setMaximumRowCount(15);
		splitPane.setRightComponent(comboBox);
		
		table_DH = new JTable();
//		table_DH.setBackground(Color.red);
		
		JScrollPane scrollPane = new JScrollPane(table_DH);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 19));
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.setViewportView(table_DH);
		
		pn_center.add(scrollPane, BorderLayout.CENTER);
		
		
			
		table_DH.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		DefaultTableModel setEdit = new DefaultTableModel(
			new String[] { "STT", "Mã đơn hàng", "Thành tiền", "Ngày tạo" }, 0) {

			// ghi de phuong thuc iscelleditable
			@Override
			public boolean isCellEditable(int row, int column) {
				// khong cho chinh sua column 3 return column !=3

				// khong cho chinh sua
				return false;
			}
		};
		table_DH.setModel(setEdit);
		table_DH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					// lay ROW MÀ MOUSE ĐANG TRỎ
					int row = table_DH.rowAtPoint(e.getPoint());
					if (row != -1) {
						table_DH.setRowSelectionInterval(row, row);
					}
				}
			}
		});
		table_DH.setFont(new Font("Arial", Font.PLAIN, 22));
		table_DH.setFillsViewportHeight(true);
		table_DH.setRowHeight(45);
		
		table_DH.getColumn("STT").setMaxWidth(70);
		table_DH.getColumn("STT").setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public void setHorizontalAlignment(int alignment) {
				super.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			}
		});
		
		// custom table
		table_DH.getColumn("Mã đơn hàng").setMinWidth(200);
		table_DH.getColumn("Mã đơn hàng").setMaxWidth(250);
		table_DH.getColumn("Mã đơn hàng").setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public void setHorizontalAlignment(int alignment) {
				super.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
			}
		});
		

		table_DH.getColumn("Thành tiền").setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public void setHorizontalAlignment(int alignment) {
				super.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
			}
		});
		table_DH.getColumn("Ngày tạo").setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public void setHorizontalAlignment(int alignment) {
				super.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			}
		});

		
		JTableHeader header = table_DH.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 20));
		header.setBackground(new Color(240, 240, 240));
		header.setOpaque(false);
		header.setPreferredSize(new Dimension(100, 50));
		
	}

	private void refresh() {
		DefaultTableModel datarow = (DefaultTableModel) table_DH.getModel();
		datarow.setRowCount(0);
		showDH();
	}

	private void showDH() {
		DefaultTableModel datarow = (DefaultTableModel) table_DH.getModel();
		String sql = "SELECT HoaDonBan.MaHD, SUM(ChiTiet.ThanhTien) AS ThanhTien, HoaDonBan.NgayTao\n"
				+ "FROM dbo.HoaDonBan AS HoaDonBan\n"
				+ "LEFT JOIN dbo.ChiTietHoaDon AS ChiTiet ON HoaDonBan.MaHD = ChiTiet.MaHD\n"
				+ "GROUP BY HoaDonBan.MaHD , HoaDonBan.NgayTao";
		try {
			Statement st = DangNhap.con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			for (int i = 1; rs.next(); i++) {
				String temp[] = { i + "", rs.getString("mahd"), rs.getString("ThanhTien"), rs.getString("NgayTao")+""};
				datarow.addRow(temp);
			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Lỗi lấy dữ liệu hóa đơn từ db" + e.getMessage());
		}
	}

	private void xoaDH(int[] soluong) {
		
	}
}
