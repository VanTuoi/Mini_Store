package View.Sale;

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
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import View.Login.DangNhap;

public class cardpanel_banhang extends JPanel {
	private static final long serialVersionUID = 1L;
	static int index;
	private JTable table_BH;
	static JPanel pn_center;
	private JTextField textField_thanhtimkiem;
	private JComboBox comboBox;
	private JButton Search;
	private JButton btn_add;
	
	
	private JPopupMenu popupMenu;
	
	private JLabel lbrefresh;
	private JButton btnxoa_donhang;
	

	public cardpanel_banhang() {
		GUI();
	}

	private void GUI() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 20));

		lbrefresh = new JLabel("Clear");
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
				xoaDH();
			}
		});
		btnxoa_donhang.setBorder(null);
		btnxoa_donhang.setBorderPainted(false);
		btnxoa_donhang.setContentAreaFilled(false);
		btnxoa_donhang.setIconTextGap(10);
		btnxoa_donhang.setFont(new Font("Arial", Font.PLAIN, 22));
		panel.add(btnxoa_donhang);


//		add(tabbedPane, BorderLayout.CENTER);

		pn_center = new JPanel(new BorderLayout());
		add(pn_center, BorderLayout.CENTER);
		
		JPanel pn_thanhtimkiem = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 10));
		pn_center.add(pn_thanhtimkiem, BorderLayout.NORTH);
		pn_thanhtimkiem.setBackground(Color.WHITE);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Imager\\timkiem-25.png"));
		pn_thanhtimkiem.add(lblNewLabel);
		

		JTextField searchField = new JTextField();
		
		popupMenu = new JPopupMenu();

		textField_thanhtimkiem = new JTextField();
		textField_thanhtimkiem.setFont(new Font("Arial", Font.PLAIN, 20));
		textField_thanhtimkiem.setColumns(30);
		textField_thanhtimkiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					search(textField_thanhtimkiem.getText(),(String)comboBox.getSelectedItem());
					 popupMenu.show(textField_thanhtimkiem,0,30);
				}
			}
		});
		
		
		
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "MaHang", "TenHang" }));
		comboBox.setFont(new Font("Arial", Font.PLAIN, 17));
		
		
		btn_add = new JButton("Lưu đơn");
		btn_add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_add.setIcon(new ImageIcon("Imager\\them-25.png"));
		btn_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});
		btn_add.setBorder(null);
		btn_add.setBorderPainted(false);
		btn_add.setContentAreaFilled(false);
		btn_add.setIconTextGap(10);
		btn_add.setFont(new Font("Arial", Font.PLAIN, 22));
		
		
		pn_thanhtimkiem.add(textField_thanhtimkiem);
		pn_thanhtimkiem.add(comboBox);
		pn_thanhtimkiem.add(btn_add);
	
		
		table_BH = new JTable();
//		table_BH.setBackground(Color.red);
		
		JScrollPane scrollPane = new JScrollPane(table_BH);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 19));
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.setViewportView(table_BH);
		
		pn_center.add(scrollPane, BorderLayout.CENTER);
		
		
			
		table_BH.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		DefaultTableModel setEdit = new DefaultTableModel(
			new String[] { "STT", "Mã sản phẩm","Tên sản phẩm", "Số lượng", "Đơn giá","Thành tiền" }, 0) {

			// ghi de phuong thuc iscelleditable
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column !=3 && column !=5) {
					return false;
				}
				return true;
			}
		};
		table_BH.setModel(setEdit);
		table_BH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					// lay ROW MÀ MOUSE ĐANG TRỎ
					int row = table_BH.rowAtPoint(e.getPoint());
					int column = table_BH.columnAtPoint(e.getPoint());
					if (column == 3) {
					}
					if (row != -1) {
						table_BH.setRowSelectionInterval(row, row);
					}
				}
			}
		});
		
		table_BH.setFont(new Font("Arial", Font.PLAIN, 22));
		table_BH.setFillsViewportHeight(true);
		table_BH.setRowHeight(45);
		
		table_BH.getColumn("STT").setMaxWidth(70);
		table_BH.getColumn("STT").setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public void setHorizontalAlignment(int alignment) {
				super.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			}
		});
				
		JTableHeader header = table_BH.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 20));
		header.setBackground(new Color(240, 240, 240));
		header.setOpaque(false);
		header.setPreferredSize(new Dimension(100, 50));
		
	}

	private void refresh() {
		DefaultTableModel datarow = (DefaultTableModel) table_BH.getModel();
		datarow.setRowCount(0);
	}

	private void search(String text_Search,String dieukhien) {
		DefaultTableModel datarow = (DefaultTableModel) table_BH.getModel();
		String sql = "SELECT * FROM MatHang WHERE "+dieukhien+" LIKE N'%" + text_Search + "%' ";
		try {
			Statement st = DangNhap.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			popupMenu.removeAll();
			List<JMenuItem> menuItems = new ArrayList<>();

			try {
			    boolean foundData = false;
			    int count = 0;
			    while (rs.next() && count < 5) {
			        foundData = true;
			        int i = rs.getRow();
			        String maHang = rs.getString("MaHang");
			        String tenHang = rs.getString("TenHang");
			        JMenuItem menuItem = new JMenuItem(""+i + "     | Mã sản phẩm: " + maHang + "    |   " + tenHang);
			        menuItem.setFont(new Font("Arial", Font.BOLD, 18));
			        menuItem.setPreferredSize(new Dimension(menuItem.getPreferredSize().width,50));
			        menuItem.addActionListener(new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent e) {
			                onClick(maHang);
			            }
			        });
			        popupMenu.add(menuItem);
			        count++;
			    }
			    if (!foundData) {
			        JMenuItem menuItem = new JMenuItem("Không tìm thấy dữ liệu");
			        menuItem.setFont(new Font("Arial", Font.BOLD, 18));
			        menuItem.setPreferredSize(new Dimension(menuItem.getPreferredSize().width,50));
			        popupMenu.add(menuItem);
			    }
			} catch (SQLException e) {
			    e.printStackTrace();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi lấy dữ liệu tìm kiếm từ db" + e.getMessage());
		}
	}

	private String getMaHD() {
	    String selectQuery = "SELECT TOP 1 MaHD FROM HoaDonBan ORDER BY MaHD DESC;";
	    String insertQuery = "INSERT INTO HoaDonBan (MaHD, NgayTao) VALUES (?, ?);";
	    String oldMaHD = null;
	    String newMaHD = "";

	    try {
	        // Lấy mã HD hiện tại
	        Statement selectStatement = DangNhap.con.createStatement();
	        ResultSet rs = selectStatement.executeQuery(selectQuery);

	        if (rs.next()) {
	        	oldMaHD = rs.getString("MaHD");
	        }

	        // Tạo mã HD mới
	        int newMaHDInt = Integer.parseInt(oldMaHD) + 1;
	        newMaHD = String.valueOf(newMaHDInt);

	        // Lấy ngày hiện tại của hệ thống
	        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

	        // Chèn mã HD mới và ngày hiện tại vào cơ sở dữ liệu
	        PreparedStatement insertStatement = DangNhap.con.prepareStatement(insertQuery);
	        insertStatement.setString(1, newMaHD);
	        insertStatement.setDate(2, currentDate);
	        insertStatement.executeUpdate();

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Lỗi lấy mã HD" + e.getMessage());
	    }
	    return newMaHD;
	}
	
	private void save() {
				
		DefaultTableModel datarow = (DefaultTableModel) table_BH.getModel();
		int len_row = datarow.getRowCount();
		PreparedStatement pStatement = null;
		String sql = "INSERT INTO ChiTietHoaDon(MaHD , MaHang ,Soluong, DonGia ,ThanhTien) VALUES(? ,? ,? , ?, ?)";
		
		if (len_row != 0) {
			try {
				DangNhap.con.setAutoCommit(false);
				pStatement = DangNhap.con.prepareStatement(sql);
				Statement st = DangNhap.con.createStatement();			
				
				String mahd =  getMaHD();
				
				for (int i = 0; i< len_row; i++) {
					pStatement = DangNhap.con.prepareStatement(sql);
					pStatement.setString(1,mahd);
					pStatement.setString(2, datarow.getValueAt(i,1).toString());
					pStatement.setInt(3, Integer.valueOf(datarow.getValueAt(i, 3).toString()));
					pStatement.setBigDecimal(4, new BigDecimal(datarow.getValueAt(i, 4).toString()));
					pStatement.setBigDecimal(5, new BigDecimal(datarow.getValueAt(i, 5).toString()));
					
					pStatement.addBatch();
					pStatement.executeBatch();
				}
				JOptionPane.showMessageDialog(null, "cập nhật thành công: " );
				DangNhap.con.commit();
			}
			catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi dữ liệu: " + e.getMessage());
				try {
					DangNhap.con.rollback();
				} catch (SQLException e1) {
					System.out.println("themsanpham.rollback: " + e.getMessage());
				}
			}
		}else {
			JOptionPane.showMessageDialog(null, "Danh sách sản phẩm trống");
		}
}
		
	private void onClick(String MaHang) {
		DefaultTableModel datarow = (DefaultTableModel) table_BH.getModel();
		String sql = "SELECT * from MatHang where MatHang.MaHang = '"+MaHang+"'";
		try {
			Statement st = DangNhap.con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			for (int i = 1; rs.next(); i++) {
				String temp[] = { table_BH.getRowCount()+1 + "", rs.getString("mahang"), rs.getString("tenhang"),"0",rs.getString("giabanle"),"0"};
				datarow.addRow(temp);
			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Lỗi lấy 1 sp" + e.getMessage());
		}
	}
	
	private void xoaDH() {
		JOptionPane.showMessageDialog(null, "tính năng đang phát triển");
	}
}
