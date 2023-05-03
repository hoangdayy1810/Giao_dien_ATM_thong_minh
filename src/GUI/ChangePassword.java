package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Bank.Database;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangePassword extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword frame = new ChangePassword(null);
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
	private JTextField txtoldpass;
	private JTextField txtnewpass;
	private JTextField txtconfirmpass;
	
	public ChangePassword(String accountnumber) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(216, 191, 216));
		setTitle("Đổi mật khẩu");
		contentPane.setLayout(null);
		ImageIcon icon = new ImageIcon("C:\\Users\\fpt\\Downloads\\logo_bank.png");
        setIconImage(icon.getImage());
        this.accountnumber = accountnumber;
        Database db = new Database();
        setResizable(false);
		setContentPane(contentPane);
		
		JLabel lbloldpass = new JLabel("Mật khẩu cũ:");
		lbloldpass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbloldpass.setBounds(34, 49, 96, 27);
		contentPane.add(lbloldpass);
		
		txtoldpass = new JPasswordField();
		txtoldpass.setBackground(new Color(216, 191, 216));
		txtoldpass.setBounds(216, 51, 240, 27);
		Border txtopass = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
		txtoldpass.setBorder(txtopass);
		contentPane.add(txtoldpass);
		
		JLabel lblnewpass = new JLabel("Mật khẩu mới:");
		lblnewpass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblnewpass.setBounds(34, 107, 103, 27);
		contentPane.add(lblnewpass);
		
		txtnewpass = new JPasswordField();
		txtnewpass.setBackground(new Color(216, 191, 216));
		txtnewpass.setBounds(216, 109, 240, 27);
		Border txtnpass = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
		txtnewpass.setBorder(txtnpass);
		contentPane.add(txtnewpass);
		
		JLabel lblconfirmpass = new JLabel("Xác nhận mật khẩu mới:");
		lblconfirmpass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblconfirmpass.setBounds(34, 166, 171, 27);
		contentPane.add(lblconfirmpass);
		
		txtconfirmpass = new JPasswordField();
		txtconfirmpass.setBackground(new Color(216, 191, 216));
		txtconfirmpass.setBounds(216, 168, 240, 27);
		Border txtcpass = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
		txtconfirmpass.setBorder(txtcpass);
		contentPane.add(txtconfirmpass);
		
		JButton btnconfirm = new JButton("Xác nhận");
		btnconfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtoldpass.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu cũ");
					return;
				}
				if(txtnewpass.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu mới");
					return;
				}
				if(txtconfirmpass.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập xác nhận mật khẩu");
					return;
				}
				if(!txtnewpass.getText().equals(txtconfirmpass.getText())) {
					JOptionPane.showMessageDialog(null, "Xác nhận mật khẩu không chính xác");
					return;
				}
				try {
					String oldpass = db.getPassword(accountnumber);
					if(!txtoldpass.getText().equals(oldpass))
					{
						JOptionPane.showMessageDialog(null, "Mật khẩu cũ không chính xác");
						return;
					}
					int kq = db.changePassword(accountnumber, txtnewpass.getText());
					String[] options = {"Xác nhận", "Hủy"};
					int choice = JOptionPane.showOptionDialog(
						    null, // Đối tượng mẹ
						    "Bạn muốn đổi mật khẩu ?", // Nội dung thông báo
						    "Cảnh báo", // Tiêu đề của thông báo
						    JOptionPane.YES_NO_OPTION, // Loại lựa chọn
						    JOptionPane.INFORMATION_MESSAGE, // Loại thông báo
						    null, //icon
						    options, //Lựa chọn
						    null //Mặc định
						);
					if(choice == JOptionPane.YES_OPTION)
					{
						if(kq == 1)
						{
							JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công!! Vui lòng đăng nhập lại để kiểm tra");
							LoginAcc la = new LoginAcc();
							dispose();
							la.setLocationRelativeTo(null);
							la.setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(null, "Đổi mật khẩu thất bại");
						}
					}
				} catch (Exception e2) {
					if(db.getPassword(accountnumber) != null) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Hứa nốt lần này không lỗi nữa");
					}
				}
			}
		});
		btnconfirm.setBounds(94, 227, 111, 36);
		btnconfirm.setBackground(new Color(176, 196, 222));
		contentPane.add(btnconfirm);
		
		JButton btnexit = new JButton("Hủy");
		btnexit.setBounds(273, 227, 111, 36);
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
	}
}
