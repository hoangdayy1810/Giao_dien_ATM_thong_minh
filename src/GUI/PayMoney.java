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

public class PayMoney extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayMoney frame = new PayMoney(null);
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
	private JTextField txtpaymoney;
	
	public PayMoney(String accountnumber) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(216, 191, 216));
		setTitle("Nạp tiền");
		contentPane.setLayout(null);
		ImageIcon icon = new ImageIcon("C:\\Users\\fpt\\Downloads\\logo_bank.png");
        setIconImage(icon.getImage());
        this.accountnumber = accountnumber;
        Database db = new Database();
        int amount = db.getBalance(accountnumber); //lấy số dư tài khoản
        setResizable(false);
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
		
		JLabel lblpaymoney = new JLabel("Số tiền nạp: ");
		lblpaymoney.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblpaymoney.setBounds(49, 185, 124, 26);
		contentPane.add(lblpaymoney);
		
		txtpaymoney = new JTextField();
		txtpaymoney.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpaymoney.setBackground(new Color(216, 191, 216));
		txtpaymoney.setBounds(183, 185, 243, 29);
		Border txtpay = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
		txtpaymoney.setBorder(txtpay);
		contentPane.add(txtpaymoney);
		
		JButton btnconfirm = new JButton("Xác nhận");
		btnconfirm.setBackground(new Color(176, 196, 222));
		btnconfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtpaymoney.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền");
					return;
				}
				try {
					int money = Integer.parseInt(txtpaymoney.getText());
					if(money <= 0)
					{
						JOptionPane.showMessageDialog(null, "Số tiền gửi không hợp lệ");
					}
					else if(money > 100000000)
					{
						JOptionPane.showMessageDialog(null, "Số tiền gửi vượt quá 100.000.000");
					}
					else if(money % 10000 != 0)
					{
						JOptionPane.showMessageDialog(null, "Tiền gửi phải là bội của 10.000");
					}
					else {
						String[] options = {"Xác nhận", "Hủy"};
						int choice = JOptionPane.showOptionDialog(
							    null, // Đối tượng mẹ
							    "Kiểm tra lại giao dịch trước khi xác nhận !!", // Nội dung thông báo
							    "Cảnh báo", // Tiêu đề của thông báo
							    JOptionPane.YES_NO_OPTION, // Loại lựa chọn
							    JOptionPane.INFORMATION_MESSAGE, // Loại thông báo
							    null, //icon
							    options, //Lựa chọn
							    null //Mặc định
							);
						if(choice == JOptionPane.YES_OPTION)
						{
						int kq = db.trade(accountnumber, money, "N");
						if(kq == 1) {
							JOptionPane.showMessageDialog(null, "Vui lòng gửi tiền vào bên dưới!!");
							JOptionPane.showMessageDialog(null, "Bạn đã nạp " + txtpaymoney.getText() + "đ thành công."
														+ "\nLƯU Ý!! ĐĂNG XUẤT KHI KHÔNG SỬ DỤNG NỮA!!");
							PayMoney pm = new PayMoney(accountnumber);
							dispose();
							pm.setLocationRelativeTo(null);
							pm.setVisible(true);
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
