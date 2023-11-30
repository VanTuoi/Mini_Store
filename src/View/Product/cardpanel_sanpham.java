package View.Product;

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
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.processing.FilerException;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Model.ob_sanpham;
import View.Login.DangNhap;

public class cardpanel_sanpham extends JPanel {

	private JTable table_sp;
	private JTextField textField_timkiem;
	private JComboBox comboBox;
	private JButton btnthem_sp;
	private JButton btnxoa_sp;
	private JLabel lbrefresh;

	public cardpanel_sanpham() {
		GUI();
		/*
		 * show san pham
		 */
		showSanpham();
	}

	private void GUI() {

		setPreferredSize(new Dimension(1120, 749));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));

		table_sp = new JTable();
		table_sp.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table_sp.setFont(new Font("Arial", Font.PLAIN, 22));
		table_sp.setPreferredScrollableViewportSize(new Dimension(350, 400));
		table_sp.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã hàng hóa", "Tên hàng hóa", "Đơn vị tính","Giá nhập","Giá bán","Số lượng còn lại" }) {
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		});
		table_sp.getColumnModel().getColumn(0).setMaxWidth(70);
		table_sp.setRowHeight(45);
		table_sp.setFillsViewportHeight(true);

		JTableHeader header = table_sp.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 20));
		header.setBackground(new Color(240, 240, 240));
		header.setOpaque(false);
		header.setPreferredSize(new Dimension(100, 50));

		JScrollPane scrollPane = new JScrollPane(table_sp);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JPanel panel_SP = new JPanel(new BorderLayout());
		panel_SP.add(scrollPane, BorderLayout.CENTER);

		add(panel_SP, BorderLayout.CENTER);
		
		JPanel panel_timkiem = new JPanel();
		panel_timkiem.setBackground(Color.WHITE);
		FlowLayout fl_panel_timkiem = (FlowLayout) panel_timkiem.getLayout();
		fl_panel_timkiem.setVgap(10);
		fl_panel_timkiem.setAlignment(FlowLayout.LEFT);
		panel_SP.add(panel_timkiem, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Imager\\timkiem-25.png"));
		panel_timkiem.add(lblNewLabel);

		JSplitPane splitPane = new JSplitPane();
		panel_timkiem.add(splitPane);

		textField_timkiem = new JTextField();
		textField_timkiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String txt = textField_timkiem.getText().trim();
				String dieukien = (String) comboBox.getSelectedItem();
				if (e.getKeyCode() == 10) {
					ds_timkiemSP(txt, dieukien);
				}
			}

			private void ds_timkiemSP(String txt, String dieukien) {
				DefaultTableModel datarow = (DefaultTableModel) table_sp.getModel();
				datarow.setRowCount(0);
				String sql = "select * from MatHang where " + dieukien + " like N'%" + txt + "%' ";
				try {
					Statement st = DangNhap.con.createStatement();
					ResultSet rs = st.executeQuery(sql);

					for (int i = 1; rs.next(); i++) {
						String temp[] = { i + "", rs.getString("mahang"), rs.getString("tenhang"), rs.getString("donvitinh"),
								rs.getString("gianhap"),rs.getString("giabanle"),rs.getString("soluong") };
						datarow.addRow(temp);
					}
				} catch (Exception e) {

					System.out.println("cardpanel_sanpham -ds_timkiemSP: " + e.getMessage());
				}
			}
		});
		textField_timkiem.setHorizontalAlignment(SwingConstants.LEFT);
		textField_timkiem.setFont(new Font("Arial", Font.PLAIN, 20));
		textField_timkiem.setColumns(30);
		splitPane.setLeftComponent(textField_timkiem);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "MaHang", "TenHang" }));
		comboBox.setFont(new Font("Arial", Font.PLAIN, 17));
		splitPane.setRightComponent(comboBox);

		JPanel panel_bar = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_bar.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		flowLayout.setVgap(20);
		flowLayout.setHgap(30);
		add(panel_bar, BorderLayout.NORTH);

		lbrefresh = new JLabel("Refresh");
		lbrefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refresh();
			}
		});
		lbrefresh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbrefresh.setIconTextGap(7);
		lbrefresh.setIcon(new ImageIcon("Imager\\refresh-25.png"));
		lbrefresh.setFont(new Font("Arial", Font.PLAIN, 22));
		panel_bar.add(lbrefresh);

		btnthem_sp = new JButton("Thêm hàng hóa");
		btnthem_sp.setEnabled(DangNhap.action_them);
		btnthem_sp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				themsanpham them = new themsanpham();
				them.run();
			}
		});
		btnthem_sp.setBorderPainted(false);
		btnthem_sp.setBorder(null);
		btnthem_sp.setContentAreaFilled(false);
		btnthem_sp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnthem_sp.setIconTextGap(7);
		btnthem_sp.setIcon(new ImageIcon("Imager\\themsp-25.png"));
		btnthem_sp.setFont(new Font("Arial", Font.PLAIN, 22));
		panel_bar.add(btnthem_sp);

		btnxoa_sp = new JButton("Xoá");
		btnxoa_sp.setEnabled(DangNhap.action_xoa);
		btnxoa_sp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int[] soluong = table_sp.getSelectedRows();
				if (soluong.length > 0) {
					int xacnhan = JOptionPane.showConfirmDialog(null, "xác nhận xóa " + soluong.length + " sản phẩm");
					if (xacnhan == JOptionPane.YES_OPTION)
						xoaSP(soluong);
				}
			}
		});
		btnxoa_sp.setBorder(null);
		btnxoa_sp.setContentAreaFilled(false);
		btnxoa_sp.setBorderPainted(false);
		btnxoa_sp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnxoa_sp.setIconTextGap(7);
		btnxoa_sp.setIcon(new ImageIcon("Imager\\xoa-25.png"));
		btnxoa_sp.setFont(new Font("Arial", Font.PLAIN, 22));
		panel_bar.add(btnxoa_sp);

		JButton btnnhapfile = new JButton("Nhập File");
		btnnhapfile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nhapFile();
			}
		});
		btnnhapfile.setEnabled(DangNhap.action_nhapfile);
		btnnhapfile.setBorder(null);
		btnnhapfile.setContentAreaFilled(false);
		btnnhapfile.setBorderPainted(false);
		btnnhapfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnnhapfile.setIconTextGap(5);
		btnnhapfile.setIcon(new ImageIcon("Imager\\nhapfile-30.png"));
		btnnhapfile.setFont(new Font("Arial", Font.PLAIN, 22));
		panel_bar.add(btnnhapfile);

		JButton btnxuatfile = new JButton("Xuất File");
		btnxuatfile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				xuatfile();
			}
		});
		btnxuatfile.setEnabled(DangNhap.action_xuatfile);
		btnxuatfile.setBorder(null);
		btnxuatfile.setBorderPainted(false);
		btnxuatfile.setContentAreaFilled(false);
		btnxuatfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnxuatfile.setIconTextGap(5);
		btnxuatfile.setIcon(new ImageIcon("Imager\\xuatfile2-30.png"));
		btnxuatfile.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnxuatfile.setFont(new Font("Arial", Font.PLAIN, 22));
		panel_bar.add(btnxuatfile);

	}

	// danh sach san pham cần xóa đã đảm bảo có dữ liệu
	private void xoaSP(int[] soluong) {
		int size = soluong.length;
		Statement st = null;
		String sql = "(";
		try {
			st = DangNhap.con.createStatement();
			for (int i = 0; i < size; i++) {
				sql += "N'" + (String) table_sp.getValueAt(soluong[i], 1) + "',";
			}
			// xoa dau , cuoi cung
			sql = sql.substring(0, sql.length() - 1);
			sql += ")";

			ResultSet rs = st.executeQuery("select count(*) from chitiethoadon where mahang in " + sql);
			if (rs.next()) {
				// hóa đơn không chứa các mã hàng thì cho phép xóa các mặt hàng đó
				if (rs.getInt(1) == 0) {
					st.executeUpdate("delete from mathang where mahang in " + sql);
				} else {
					JOptionPane.showMessageDialog(null,
							"các mặt hàng đang tồn tại trong hóa đơn,bạn phải xóa các đơn hàng chứa các mặt hàng đó trước.");
				}
			}
			refresh();
		} catch (Exception e) {
			System.out.println("cardpanel_sanpham -xoaSP: " + e.getMessage());
		}
	}

	private void refresh() {
		DefaultTableModel datarow = (DefaultTableModel) table_sp.getModel();
		datarow.setRowCount(0);
		showSanpham();
	}

	private void showSanpham() {
		DefaultTableModel datarow = (DefaultTableModel) table_sp.getModel();
		String sql = "select * from mathang ";
		try {
			Statement st = DangNhap.con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			for (int i = 1; rs.next(); i++) {
				String temp[] = { i + "", rs.getString("mahang"), rs.getString("tenhang"), rs.getString("donvitinh"),
						rs.getString("gianhap"),rs.getString("giabanle"),rs.getString("soluong") };
				datarow.addRow(temp);
			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "cardpanel_sanpham - showdata: " + e.getMessage());
		}

	}

	private void nhapFile() {
		JOptionPane.showMessageDialog(null, "Tính năng đang phát triễn ");
	}

	private void xuatfile() {
		JOptionPane.showMessageDialog(null, "Tính năng đang phát triễn ");
	}


}
