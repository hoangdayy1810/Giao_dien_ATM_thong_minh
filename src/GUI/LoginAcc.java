package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Bank.Database;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class LoginAcc extends JFrame {

	private JPanel contentPane;
	private JTextField textfaccount;
	private JPasswordField passfield;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginAcc frame = new LoginAcc();
					frame.setLocationRelativeTo(null);
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
	public LoginAcc() {
		setBackground(new Color(192, 192, 192));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 222, 179));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Đăng nhập");
		ImageIcon icon = new ImageIcon("C:\\Users\\fpt\\Downloads\\logo_bank.png");
        setIconImage(icon.getImage());
        setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LabelBankName = new JLabel("HHBank");
		LabelBankName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LabelBankName.setBounds(133, 30, 100, 31);
		LabelBankName.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(LabelBankName);
		
		JLabel LabelAcc = new JLabel("Số Tài Khoản");
		LabelAcc.setSize(100, 13);
		LabelAcc.setLocation(33, 89);
		contentPane.add(LabelAcc);
		
		textfaccount = new JTextField();
		textfaccount.setBackground(new Color(245, 222, 179));
		Border txtacc = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
		textfaccount.setBorder(txtacc);
		textfaccount.setBounds(120, 86, 218, 19);
		contentPane.add(textfaccount);
		
		JLabel LabelPass = new JLabel("Mật Khẩu");
		LabelPass.setBounds(33, 130, 90, 13);
		contentPane.add(LabelPass);
		
		passfield = new JPasswordField();
		passfield.setBounds(120, 127, 218, 19);
		passfield.setBackground(new Color(245, 222, 179));
		Border txtpass = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
		passfield.setBorder(txtpass);
		contentPane.add(passfield);
		
		JButton btnLogin = new JButton("Đăng nhập");
		btnLogin.setBackground(new Color(255, 192, 203));
		btnLogin.setBounds(79, 184, 100, 31);
		btnLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(textfaccount.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số tài khoản");
					return;
				}
				if(passfield.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu");
					return;
				}

			Database db = new Database();
			int kq = db.loginAcc(textfaccount.getText(), passfield.getText());
			if(kq == 1) {
				JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
				main m = new main(textfaccount.getText());
				dispose();
				m.setLocationRelativeTo(null);
				m.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "Số tài khoản hoặc mật khẩu không đúng!!");
			}
		}
		});
		contentPane.add(btnLogin);
		
		JButton btnRegister = new JButton("Đăng ký");
		btnRegister.setBackground(new Color(255, 192, 203));
		btnRegister.setBounds(211, 184, 100, 31);
		btnRegister.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				Register re = new Register();
				dispose();
				re.setLocationRelativeTo(null);
				re.setVisible(true);
			}
		});
		contentPane.add(btnRegister);
	}

}
