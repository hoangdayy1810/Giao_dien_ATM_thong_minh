package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Bank.Database;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OutMoney extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OutMoney frame = new OutMoney(null);
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
	private JTextField txtoutmoney;
	
	public OutMoney(String accountnumber) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(216, 191, 216));
		setTitle("Rút tiền");
		contentPane.setLayout(null);
		ImageIcon icon = new ImageIcon("C:\\Users\\fpt\\Downloads\\logo_bank.png");
        setIconImage(icon.getImage());
        this.accountnumber = accountnumber;
        Database db = new Database();
        setResizable(false);
        int amount = db.getBalance(accountnumber); //lấy số dư tài khoản
		setContentPane(contentPane);
		
		JLabel lblname = new JLabel("HHBank");
		lblname.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblname.setHorizontalAlignment(SwingConstants.CENTER);
		lblname.setBounds(155, 20, 136, 29);
		contentPane.add(lblname);
		
		JLabel lblaccnumber = new JLabel("Số tài khoản: " + accountnumber);
		lblaccnumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblaccnumber.setBounds(73, 70, 353, 34);
		contentPane.add(lblaccnumber);
		
		JLabel lbloutmoney = new JLabel("Số tiền rút: ");
		lbloutmoney.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbloutmoney.setBounds(49, 185, 124, 26);
		contentPane.add(lbloutmoney);
		
		txtoutmoney = new JTextField();
		txtoutmoney.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtoutmoney.setBackground(new Color(216, 191, 216));
		txtoutmoney.setBounds(183, 185, 243, 29);
		Border txtout = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
		txtoutmoney.setBorder(txtout);
		contentPane.add(txtoutmoney);
		
		JButton btnconfirm = new JButton("Xác nhận");
		btnconfirm.setBackground(new Color(176, 196, 222));
		btnconfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtoutmoney.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền");
					return;
				}
				try {
					int money = Integer.parseInt(txtoutmoney.getText());
					if(money <= 0)
					{
						JOptionPane.showMessageDialog(null, "Số tiền rút không hợp lệ");
					}
					else if(money > 100000000)
					{
						JOptionPane.showMessageDialog(null, "Số tiền rút vượt quá 100.000.000");
					}
					else if(money > amount)
					{
						JOptionPane.showMessageDialog(null, "Số tiền rút lớn hơn số dư hiện có");
					}
					else if(money % 10000 != 0)
					{
						JOptionPane.showMessageDialog(null, "Tiền rút phải là bội của 10.000");
					}
					else {
						String[] options = {"Xác nhận", "Hủy"};
						int choice = JOptionPane.showOptionDialog(
							    null, // Đối tượng mẹ
							    "Vui lòng kiểm tra lại trước khi xác nhận giao dịch", // Nội dung thông báo
							    "Cảnh báo", // Tiêu đề của thông báo
							    JOptionPane.YES_NO_OPTION, // Loại lựa chọn
							    JOptionPane.INFORMATION_MESSAGE, // Loại thông báo
							    null, //icon
							    options, //Lựa chọn
							    null //Mặc định
							);
						if(choice == JOptionPane.YES_OPTION)
						{
						int kq = db.trade(accountnumber, money, "R");
						if(kq == 1) {
							JOptionPane.showMessageDialog(null, "Bạn đã rút " + txtoutmoney.getText() + "đ thành công. Vui lòng chờ một chút."
														+ "\nLƯU Ý!! ĐĂNG XUẤT KHI KHÔNG SỬ DỤNG NỮA!!");
							OutMoney om = new OutMoney(accountnumber);
							dispose();
							om.setLocationRelativeTo(null);
							om.setVisible(true);
						}
						}
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Lắm lỗi thế bạn");
				}
			}
		});
		btnconfirm.setBounds(102, 242, 103, 29);
		contentPane.add(btnconfirm);
		
		JButton btnexit = new JButton("Hủy");
		btnexit.setBounds(262, 242, 103, 29);
		btnexit.setBackground(new Color(176, 196, 222));
		btnexit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				main m = new main(accountnumber);
				dispose();
				m.setLocationRelativeTo(null);
				m.setVisible(true);
			}
		});
		contentPane.add(btnexit);
		
		JLabel lblMoney = new JLabel("Số dư tài khoản: " + amount);
		lblMoney.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMoney.setBounds(73, 124, 353, 26);
		contentPane.add(lblMoney);
	}
}
