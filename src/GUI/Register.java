package GUI;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtNumber;
	private JTextField txtPhone;
	private JPasswordField txtpassconfirm;
	private JPasswordField txtpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 447, 396);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 222, 179));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		ImageIcon icon = new ImageIcon("C:\\Users\\fpt\\Downloads\\logo_bank.png");
		setResizable(false);
        setIconImage(icon.getImage());

		setContentPane(contentPane);
		
		JLabel LbAccNum = new JLabel("Số tài khoản:");
		LbAccNum.setBounds(39, 120, 75, 13);
		contentPane.add(LbAccNum);
		
		JLabel LbPass = new JLabel("Mật khẩu:");
		LbPass.setBounds(39, 164, 75, 13);
		contentPane.add(LbPass);
		
		JLabel LbPhone = new JLabel("Số điện thoại:");
		LbPhone.setBounds(39, 250, 120, 13);
		contentPane.add(LbPhone);
		
		JLabel LbName = new JLabel("Tên chủ tài khoản:");
		LbName.setBounds(39, 81, 106, 13);
		contentPane.add(LbName);
		
		txtName = new JTextField();
		txtName.setBackground(new Color(245, 222, 179));
		txtName.setBounds(169, 78, 200, 19);
		Border txtname = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
		txtName.setBorder(txtname);
		contentPane.add(txtName);
		
		txtNumber = new JTextField();
		txtNumber.setBackground(new Color(245, 222, 179));
		txtNumber.setBounds(169, 117, 200, 19);
		Border txtnumber = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
		txtNumber.setBorder(txtnumber);
		contentPane.add(txtNumber);
		
		txtPhone = new JTextField();
		txtPhone.setBackground(new Color(245, 222, 179));
		txtPhone.setBounds(169, 247, 200, 19);
		Border txtphone = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
		txtPhone.setBorder(txtphone);
		contentPane.add(txtPhone);
		
		JButton btnConfirm = new JButton("Xác nhận");
		btnConfirm.setBackground(new Color(255, 192, 203));
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						if(txtName.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Vui lòng tên chủ tài khoản");
							return;
						}
						if(txtNumber.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Vui lòng nhập số tài khoản");
							return;
						}
						if(txtpass.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu");
							return;
						}
						if(txtpassconfirm.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Vui lòng nhập xác nhận mật khẩu");
							return;
						}
						if(txtPhone.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại");
							return;
						}
						if(!txtpass.getText().equals(txtpassconfirm.getText())){
							JOptionPane.showMessageDialog(null, "Xác nhận mật khẩu không chính xác");
							return;
						}

						String[] options = {"Xác nhận", "Hủy"};
						int choice = JOptionPane.showOptionDialog(
							    null, // Đối tượng mẹ (JFrame, JDialog, ...)
							    "Bạn muốn tạo tài khoản ?", // Nội dung thông báo
							    "Cảnh báo", // Tiêu đề của thông báo
							    JOptionPane.YES_NO_OPTION, // Loại lựa chọn
							    JOptionPane.INFORMATION_MESSAGE, // Loại thông báo
							    null, //icon
							    options, //Lựa chọn
							    null //Mặc định
							);
						if(choice == JOptionPane.YES_OPTION)
						{
							Database db = new Database();
							int kq = db.insertAccount(txtNumber.getText(),txtName.getText(),txtPhone.getText(), (byte)1, txtpass.getText());
							if(kq == 1) {
								JOptionPane.showMessageDialog(null, "Tạo tài khoản thành công");
								main m = new main(txtNumber.getText());
								dispose();
								m.setLocationRelativeTo(null);
								m.setVisible(true);
							}
							else if(kq == -1){
								JOptionPane.showMessageDialog(null, "Tài khoản đã tồn tại");
							}
							else if(kq == -11){
								JOptionPane.showMessageDialog(null, "Không có dữ liệu");
							}
							else {
								JOptionPane.showConfirmDialog(null, "Lỗi gì đó rồi bạn êi");
							}
						}
			}
		});
		btnConfirm.setBounds(98, 304, 100, 31);
		contentPane.add(btnConfirm);
		
		JButton btnBack = new JButton("Quay Lại");
		btnBack.setBackground(new Color(255, 192, 203));
		btnBack.setBounds(239, 304, 100, 31);
		btnBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				LoginAcc log = new LoginAcc();
				dispose();
				log.setLocationRelativeTo(null);
				log.setVisible(true);
			}
		});
		contentPane.add(btnBack);
		
		JLabel LbRegister = new JLabel("Đăng ký tài khoản");
		LbRegister.setBounds(157, 35, 100, 13);
		contentPane.add(LbRegister);
		
		JLabel Lbconfirmpass = new JLabel("Xác nhận mật khẩu:");
		Lbconfirmpass.setBounds(39, 207, 120, 13);
		contentPane.add(Lbconfirmpass);
		
		txtpassconfirm = new JPasswordField();
		txtpassconfirm.setBackground(new Color(245, 222, 179));
		txtpassconfirm.setBounds(169, 207, 200, 19);
		Border txtpasscf = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
		txtpassconfirm.setBorder(txtpasscf);
		contentPane.add(txtpassconfirm);
		
		txtpass = new JPasswordField();
		txtpass.setBackground(new Color(245, 222, 179));
		txtpass.setBounds(169, 164, 200, 19);
		contentPane.add(txtpass);
		Border txtpassword = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
		txtpass.setBorder(txtpassword);
		setTitle("Đăng ký");
	}
}
