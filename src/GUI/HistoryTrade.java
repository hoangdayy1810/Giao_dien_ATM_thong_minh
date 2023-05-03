package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Bank.Database;

import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;

public class HistoryTrade extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistoryTrade frame = new HistoryTrade(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Lỗi rồi bạn êi");
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private String accountnumber;
	private JTable tablehistorytrade;
	private JButton btnBack;
	
	public HistoryTrade(String accountnumber) {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 475);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(216, 191, 216));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Lịch sử giao dịch");
		ImageIcon icon = new ImageIcon("C:\\Users\\fpt\\Downloads\\logo_bank.png");
        setIconImage(icon.getImage());
        setResizable(false);
        this.accountnumber = accountnumber;
        contentPane.setLayout(null);
        
		setContentPane(contentPane);
		
		tablehistorytrade = new JTable();
//		tablehistorytrade.setBounds(0, 0, 635, 268);
		contentPane.add(tablehistorytrade);
		
		JScrollPane scrollPane = new JScrollPane(tablehistorytrade);
		scrollPane.setSize(639, 376);
		scrollPane.setLocation(10, 52);
		contentPane.add(scrollPane);
		
		btnBack = new JButton("Quay lại"); 
		btnBack.setBackground(new Color(176, 196, 222));
		btnBack.setBounds(10, 10, 121, 32);
		DefaultTableModel model = new DefaultTableModel();
		Vector row, rows;
		row = new Vector();
		row.add("Ngày giao dịch");
		row.add("Loại giao dịch");
		row.add("Số tiền");
		model.setColumnIdentifiers(row);
		tablehistorytrade.setModel(model);
		btnBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				main m = new main(accountnumber);
				dispose();
				m.setLocationRelativeTo(null);
				m.setVisible(true);
			}
		});
		contentPane.add(btnBack);
			Database db = new Database();
			ResultSet rs = db.historyTrade(accountnumber);
			try {
				rows = new Vector();
				rows.add(rs.getTimestamp(1).toString());
				rows.add(rs.getString(2));
				rows.add(rs.getInt(3)+"");
				model.addRow(rows);
				while(rs.next())
				{
					rows = new Vector();
					rows.add(rs.getTimestamp(1).toString());
					rows.add(rs.getString(2));
					rows.add(rs.getInt(3)+"");
					model.addRow(rows);
//					JOptionPane.showMessageDialog(null, row);
				}
				tablehistorytrade.setModel(model);
				
			} catch (Exception e) {	
				if(rs != null) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Lỗi rồi bạn êi");
				}
			}
	}
}
