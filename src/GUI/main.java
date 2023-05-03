package GUI;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Bank.Database;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main(null);
//					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	private String accountnumber;
	
	public main(String accountnumber) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 522);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("HHBank");
		contentPane.setLayout(null);
		ImageIcon icon = new ImageIcon("C:\\Users\\fpt\\Downloads\\logo_bank.png");
        setIconImage(icon.getImage());
        this.accountnumber = accountnumber;
        setResizable(false);
        Database db = new Database();
		setContentPane(contentPane);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(230, 230, 250));
		setJMenuBar(menuBar);
		
		JMenu mnsetting = new JMenu("Cài đặt");
		mnsetting.setBackground(new Color(175, 238, 238));
		mnsetting.setHorizontalAlignment(SwingConstants.RIGHT);
		menuBar.add(mnsetting);
		
		JMenuItem mntmchangePass = new JMenuItem("Đổi mật khẩu");
		mntmchangePass.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				ChangePassword cp = new ChangePassword(accountnumber);
				dispose();
				cp.setLocationRelativeTo(null);
				cp.setVisible(true);
			}
		});
		mnsetting.add(mntmchangePass);
		
		JMenuItem mntmlogOut = new JMenuItem("Đăng xuất");
		mntmlogOut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String[] options = {"Xác nhận", "Hủy"};
				int choice = JOptionPane.showOptionDialog(
					    null, // Đối tượng mẹ
					    "Bạn có muốn đăng xuất không ?", // Nội dung thông báo
					    "Cảnh báo", // Tiêu đề của thông báo
					    JOptionPane.YES_NO_OPTION, // Loại lựa chọn
					    JOptionPane.INFORMATION_MESSAGE, // Loại thông báo
					    null, //icon
					    options, //Lựa chọn
					    null //Mặc định
					);
				if(choice == JOptionPane.YES_OPTION)
				{
				LoginAcc la = new LoginAcc();
				dispose();
				la.setLocationRelativeTo(null);
				la.setVisible(true);
				}
			}
		});
		
		
		JMenuItem mntmlockacc = new JMenuItem("Khóa tài khoản");
		mntmlockacc.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String[] options = {"Xác nhận", "Hủy"};
				int choice = JOptionPane.showOptionDialog(
					    null, // Đối tượng mẹ
					    "Bạn có chắc chắn khóa tài khoản không ?", // Nội dung thông báo
					    "Cảnh báo", // Tiêu đề của thông báo
					    JOptionPane.YES_NO_OPTION, // Loại lựa chọn
					    JOptionPane.INFORMATION_MESSAGE, // Loại thông báo
					    null, //icon
					    options, //Lựa chọn
					    null //Mặc định
					);
				if(choice == JOptionPane.YES_OPTION)
				{
				int kq = db.lockAccount(accountnumber);
				if(kq == 1) {
					JOptionPane.showMessageDialog(null, "Bạn đã khóa tài khoản thành công");
					LoginAcc la = new LoginAcc();
					dispose();
					la.setLocationRelativeTo(null);
					la.setVisible(true);
				}
				}
			}
		});
		mnsetting.add(mntmlockacc);
		mnsetting.add(mntmlogOut);
		
		
		
		JButton btnNap = new JButton("Nạp tiền");
		btnNap.setBackground(new Color(127, 255, 212));
		btnNap.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNap.setBounds(82, 263, 217, 74);
		btnNap.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				PayMoney pm = new PayMoney(accountnumber);
				dispose();
				pm.setLocationRelativeTo(null);
				pm.setVisible(true);
			}
		});
		contentPane.add(btnNap);
		
		JButton btnRut = new JButton("Rút tiền");
		btnRut.setBackground(new Color(127, 255, 212));
		btnRut.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnRut.setBounds(369, 263, 217, 74);
		btnRut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				OutMoney om = new OutMoney(accountnumber);
				dispose();
				om.setLocationRelativeTo(null);
				om.setVisible(true);
			}
		});
		contentPane.add(btnRut);
		
		JButton btnLsugiaodich = new JButton("Lịch sử giao dịch");
		btnLsugiaodich.setBackground(new Color(127, 255, 212));
		btnLsugiaodich.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLsugiaodich.setBounds(369, 369, 217, 74);
		btnLsugiaodich.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				HistoryTrade ht = new HistoryTrade(accountnumber);
				dispose();
				ht.setLocationRelativeTo(null);
				ht.setVisible(true);
			}
		});
		contentPane.add(btnLsugiaodich);
		
		JButton btnChuyenKhoan = new JButton("Chuyển khoản");
		btnChuyenKhoan.setBackground(new Color(127, 255, 212));
		btnChuyenKhoan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnChuyenKhoan.setBounds(82, 369, 217, 74);
		btnChuyenKhoan.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				TransferMoney tm = new TransferMoney(accountnumber);
				dispose();
				tm.setLocationRelativeTo(null);
				tm.setVisible(true);
			}
		});
		contentPane.add(btnChuyenKhoan);
		
		int amount = db.getBalance(accountnumber);
		JLabel lblSoDu = new JLabel("Số dư tài khoản: " + amount);
		lblSoDu.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblSoDu.setBounds(156, 193, 409, 38);
		contentPane.add(lblSoDu);
		
		JLabel txtBrand = new JLabel("HHBank");
		txtBrand.setHorizontalAlignment(SwingConstants.CENTER);
		txtBrand.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtBrand.setBounds(233, 26, 187, 32);
		contentPane.add(txtBrand);
		
		String nameacc = db.getNameAccount(accountnumber);
		JLabel lblNewLabel_2 = new JLabel("Chào mừng " + nameacc);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(37, 83, 580, 38);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblaccnumber = new JLabel("Số tài khoản: " + accountnumber);
		lblaccnumber.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblaccnumber.setBounds(156, 141, 352, 31);
		contentPane.add(lblaccnumber);
		this.accountnumber = accountnumber;
	}
}
