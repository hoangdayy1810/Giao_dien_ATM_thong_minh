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


public class TransferMoney extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransferMoney frame = new TransferMoney(null);
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
	private JTextField txttransmoney;
	private JTextField txtacc_receive;
	
	public TransferMoney(String accountnumber) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(216, 191, 216));
		setTitle("Chuyển khoản");
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
		
		JLabel lbltransmoney = new JLabel("Số tiền : ");
		lbltransmoney.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbltransmoney.setBounds(49, 241, 90, 26);
		contentPane.add(lbltransmoney);
		
		txttransmoney = new JTextField();
		txttransmoney.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txttransmoney.setBackground(new Color(216, 191, 216));
		txttransmoney.setBounds(136, 241, 290, 29);
		Border txttrans = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
		txttransmoney.setBorder(txttrans);
		contentPane.add(txttransmoney);
		
		txtacc_receive = new JTextField();
		txtacc_receive.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtacc_receive.setBounds(136, 185, 290, 29);
		txtacc_receive.setBackground(new Color(216, 191, 216));
		Border txtacc_rc = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
		txtacc_receive.setBorder(txtacc_rc);
		contentPane.add(txtacc_receive);
		
		JButton btnconfirm = new JButton("Xác nhận");
		btnconfirm.setBackground(new Color(176, 196, 222));
		btnconfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtacc_receive.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số tài khoản người nhận");
					return;
				}
				if(txttransmoney.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền");
					return;
				}
				try {
					int kq = db.getAccountNumber(txtacc_receive.getText());
					String acc_receive = db.getNameAccount(txtacc_receive.getText());
					if(kq == 1)
					{
						int money = Integer.parseInt(txttransmoney.getText());
						if(money <= 0)
						{
							JOptionPane.showMessageDialog(null, "Số tiền chuyển không hợp lệ");
						}
						else if(money > 100000000)
						{
							JOptionPane.showMessageDialog(null, "Số tiền chuyển vượt quá 100.000.000");
						}
						else if(money > amount)
						{
							JOptionPane.showMessageDialog(null, "Số tiền rút chuyển hơn số dư hiện có");
						}
						else if(money % 10000 != 0)
						{
							JOptionPane.showMessageDialog(null, "Tiền chuyển phải là bội của 10.000");
						}
						else {
							String[] options = {"Xác nhận", "Hủy"};
							int choice = JOptionPane.showOptionDialog(
								    null, // Đối tượng mẹ
								    "Bạn muốn chuyển cho " + acc_receive + " đúng không ?", // Nội dung thông báo
								    "Cảnh báo", // Tiêu đề của thông báo
								    JOptionPane.YES_NO_OPTION, // Loại lựa chọn
								    JOptionPane.INFORMATION_MESSAGE, // Loại thông báo
								    null, //icon
								    options, //Lựa chọn
								    null //Mặc định
								);
							if(choice == JOptionPane.YES_OPTION)
							{
							int gdng = db.trade(accountnumber, money, "C");
							int gdnn = db.trade(txtacc_receive.getText(), money, "T");
							if(gdng == 1 && gdnn == 1) {
								JOptionPane.showMessageDialog(null, "Bạn đã chuyển " + txttransmoney.getText() + "đ thành công cho "
															+ acc_receive + "\nLƯU Ý!! ĐĂNG XUẤT KHI KHÔNG SỬ DỤNG NỮA!!");
								TransferMoney tm = new TransferMoney(accountnumber);
								dispose();
								tm.setLocationRelativeTo(null);
								tm.setVisible(true);
							}
							}
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Số tài khoản nguời nhận không tồn tại");
						return;
					}	
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			});
		btnconfirm.setBounds(102, 296, 103, 29);
		contentPane.add(btnconfirm);
		
		JButton btnexit = new JButton("Hủy");
		btnexit.setBounds(262, 296, 103, 29);
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
		
		JLabel lblaccnumber_receive = new JLabel("Số tài khoản:");
		lblaccnumber_receive.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblaccnumber_receive.setBounds(10, 185, 126, 29);
		contentPane.add(lblaccnumber_receive);
		
	}
}
