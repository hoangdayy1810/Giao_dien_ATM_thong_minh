 package Bank;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	private Connection cn;
	private PreparedStatement ps;
	public Database() {
		String fileName = "E:\\Java\\Bank_Account\\src\\Bank\\Link_SQL.txt";
        String content = "";

        try {
            content = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
		try {
			cn = DriverManager.getConnection(content);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//hàm thêm tài khoản
	public int insertAccount(String accountnumber, String accountname, String phone, byte active, String acc_password)
	{
		try {
			ps = cn.prepareCall("{call insertAccount(?,?,?,?,?)}");
			ps.setString(1, accountnumber);
			ps.setString(2, accountname);
			ps.setString(3, phone);
			ps.setByte(4, active);
			ps.setString(5, acc_password);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{	//đúng trả về 1 sai trả về -1
				return rs.getInt("result"); 
			}
			return -11;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return -111;
		}
	}
	
	//hàm đăng nhập
	public int loginAcc(String accountnumber, String acc_password)
	{
		try {
			ps = cn.prepareCall("{call loginAcc(?,?)}");
			ps.setString(1, accountnumber);
			ps.setString(2, acc_password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt("result");
			}
			return -11;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return -111;
		}
	}
	
	//hàm thay đổi mật khẩu
	public int changePassword(String accountnumber, String acc_password)
	{
		try {
			ps = cn.prepareCall("{call changepassword(?,?)}");
			ps.setString(1, accountnumber);
			ps.setString(2, acc_password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt("result");
			}
			return -11;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return -111;
		}
	}
	
	//hàm thực hiện giao dịch
	public int trade(String accountnumber, int amount, String trade_type)
		{
			try {
				ps = cn.prepareCall("{call trade(?,?,?)}");
				ps.setString(1, accountnumber);
				ps.setInt(2, amount);
				ps.setString(3, trade_type);
				ResultSet rs = ps.executeQuery(); //chay cau lenh
				while(rs.next()) {
					return rs.getInt("result");
				}
				return -11;
			}
			catch (SQLException e) {
				e.printStackTrace();
				return -111;
			}
			
		}
		
	//hàm tính số dư tài khoản
	public int getBalance(String accountnumber)

		{
			try {
				ps = cn.prepareCall("{call getBalance(?)}");
				ps.setString(1,accountnumber);
				ResultSet rs = ps.executeQuery(); //chay cau lenh
				while(rs.next()) {
					return rs.getInt("balance");
				}
				return -11;
			}
			catch (SQLException e) {
				e.printStackTrace();
				return -111;
			}
			
		}
		
	//hàm xem bảng dữ liệu giao dịch
	public ResultSet historyTrade(String accountnumber)
		{
			try {
				ps = cn.prepareCall("{call historyTrade(?)}");
				ps.setString(1,accountnumber);
				ResultSet rs = ps.executeQuery(); //chay cau lenh
				while(rs.next()) {
					return rs;
				}
				return null;
			}
			catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	
	//hàm lấy tên tài khoản
	public String getNameAccount(String accountnumber)
	{
		try {
			ps = cn.prepareCall("{call getNameAccount(?)}");
			ps.setString(1, accountnumber);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getString("AccountName");
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//hàm lấy số tài khoản
		public int getAccountNumber(String accountnumber)
		{
			try {
				ps = cn.prepareCall("{call getAccountNumber(?)}");
				ps.setString(1, accountnumber);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					return rs.getInt("result");
				}
				return -11;
			} catch (SQLException e) {
				e.printStackTrace();
				return -111;
			}
		}
		
		//hàm lấy mật khẩu
				public String getPassword(String accountnumber)
				{
					try {
						ps = cn.prepareCall("{call getPassword(?)}");
						ps.setString(1, accountnumber);
						ResultSet rs = ps.executeQuery();
						while(rs.next()) {
							return rs.getString("Acc_Password");
						}
						return null;
					} catch (SQLException e) {
						e.printStackTrace();
						return null;
					}
				}
	
	//hàm khóa tài khoản
		public int lockAccount(String accountnumber)
		{
		try {
			ps = cn.prepareCall("{call lockAccount(?)}");
			ps.setString(1,accountnumber);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				return rs.getInt("result");
			}
			return -11;
		}
		catch (Exception e) {
			e.printStackTrace();
			return -111;
		}
		}
}
