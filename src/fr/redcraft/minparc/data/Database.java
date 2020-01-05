package fr.redcraft.minparc.data;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	private final String url;
	final String driver;
	String user = "";
	String database = "";
	String password = "";
	String port = "";
	String host = "";
	Connection c = null;

	public Database(String filePath) {
		url = "jdbc:sqlite:" + new File(filePath).getAbsolutePath();
		driver = ("org.sqlite.JDBC");
	}

	public Connection open() {
		try {
			Class.forName(driver);
			this.c = DriverManager.getConnection(url);
			createUserTable();
			createNickTable();
			createTempBanTable();
			createTempMuteTable();

			return c;
		} catch (SQLException e) {
			System.out.println("Impossible de ce connecté à la BDD raison : " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("BDD Introuvable");
		}
		return this.c;
	}

	public boolean checkConnection() {
		if (this.c != null) {
			return true;
		} else {
			return false;
		}
	}

	public Connection getConnection() {
		return this.c;
	}

	public void closeConnection(Connection c) {
		c = null;
	}

	private void createUserTable() {
		String sql = "CREATE TABLE IF NOT EXISTS user_table (id integer PRIMARY KEY,username text,uuid text,socialspy boolan,muted boolean,banned boolean);";

		try {
			Statement stmt = this.c.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createNickTable() {
		String sql = "CREATE TABLE IF NOT EXISTS nick_table (id integer PRIMARY KEY,username text,nickname text);";

		try {
			Statement stmt = this.c.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createTempMuteTable() {
		String sql = "CREATE TABLE IF NOT EXISTS mute_table (id integer PRIMARY KEY,username text,expire text);";

		try {
			Statement stmt = this.c.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createTempBanTable() {
		String sql = "CREATE TABLE IF NOT EXISTS ban_table (id integer PRIMARY KEY,username text,expire text);";

		try {
			Statement stmt = this.c.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createuser(String name, String UUID, boolean socialspy, boolean banned, boolean mute) {
		name = name.toLowerCase();

		String sql = "INSERT INTO user_table(username,uuid,socialspy,banned,muted) VALUES(?,?,?,?,?)";

		try {
			PreparedStatement pstmt = this.getConnection().prepareStatement(sql);

			pstmt.setString(1, name);
			pstmt.setString(2, UUID);
			pstmt.setBoolean(3, socialspy);
			pstmt.setBoolean(4, banned);
			pstmt.setBoolean(5, mute);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertNick(String name, String nick) {
		name = name.toLowerCase();

		String sql = "INSERT INTO nick_table(username,nickname) VALUES(?,?)";

		try {
			PreparedStatement pstmt = this.getConnection().prepareStatement(sql);

			pstmt.setString(1, name);
			pstmt.setString(2, nick);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setBanExpire(String user, String expire) {
		user = user.toLowerCase();

		String sql = "SELECT expire FROM ban_table where username = '" + user + "'";

		try {

			Statement stmt = this.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				sql = "UPDATE ban_table SET expire = ? WHERE username = ?";

				try {
					PreparedStatement pstmt = this.getConnection().prepareStatement(sql);

					pstmt.setString(1, expire);
					pstmt.setString(2, user);

					pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				sql = "INSERT INTO ban_table(username,expire) VALUES(?,?)";

				try {
					PreparedStatement pstmt = this.getConnection().prepareStatement(sql);

					pstmt.setString(1, user);
					pstmt.setString(2, expire);
					pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isTempMuted(String user) {
		user = user.toLowerCase();

		String sql = "SELECT id FROM mute_table where username = '" + user + "';";

		try {

			Statement stmt = this.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean isTempBanned(String user) {
		user = user.toLowerCase();

		String sql = "SELECT id FROM ban_table where username = '" + user + "';";

		try {

			Statement stmt = this.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public String getMuteExpire(String user) {

		user = user.toLowerCase();

		String sql = "SELECT expire FROM mute_table username = '" + user + "';";

		try {

			Statement stmt = this.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			return rs.getString("expire");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getBanExpire(String user) {

		user = user.toLowerCase();

		String sql = "SELECT expire FROM ban_table WHERE username = '" + user + "';";

		try {

			Statement stmt = this.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			return rs.getString("expire");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean hasNick(String user) {
		user = user.toLowerCase();

		String sql = "SELECT id FROM nick_table where username = '" + user + "';";

		try {

			Statement stmt = this.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	public boolean exists(String user) {
		user = user.toLowerCase();

		String sql = "SELECT id FROM user_table where username = '" + user + "';";

		try {

			Statement stmt = this.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public String getNick(String user) {

		user = user.toLowerCase();

		String sql = "SELECT nickname FROM nick_table WHERE username = '" + user + "';";

		try {

			Statement stmt = this.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			return rs.getString("nickname");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getReal(String user) {

		user = user.toLowerCase();

		String sql = "SELECT username FROM nick_table WHERE nickname = '" + user + "';";

		try {

			Statement stmt = this.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			return rs.getString("username");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isUsed(String nick) {

		String sql = "SELECT username FROM nick_table where nickname = '" + nick + "';";

		try {

			Statement stmt = this.getConnection().createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean getMuted(String user) {

		user = user.toLowerCase();

		String sql = "SELECT muted FROM user_table where username = '" + user + "';";

		try {

			Statement stmt = this.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			return rs.getBoolean("muted");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean getBanned(String user) {

		user = user.toLowerCase();

		String sql = "SELECT banned FROM user_table WHERE username = '" + user + "';";

		try {

			Statement stmt = this.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			return rs.getBoolean("banned");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void setNick(String user, String nick) {

		user = user.toLowerCase();

		String sql = "UPDATE nick_table SET nickname = ? WHERE username = ?";

		try {
			PreparedStatement pstmt = this.getConnection().prepareStatement(sql);

			pstmt.setString(1, nick);
			pstmt.setString(2, user);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeNick(String name) {
		name = name.toLowerCase();
		String sql = "DELETE FROM nick_table WHERE username = ?";
		try {
			PreparedStatement pstmt = this.getConnection().prepareStatement(sql);

			pstmt.setString(1, name);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeTempMute(String name) {
		name = name.toLowerCase();

		if (!this.isTempMuted(name))
			return;

		String sql = "DELETE FROM mute_table WHERE username = ?";
		try {
			PreparedStatement pstmt = this.getConnection().prepareStatement(sql);

			pstmt.setString(1, name);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeTempBan(String name) {
		name = name.toLowerCase();

		if (!this.isTempBanned(name))
			return;

		String sql = "DELETE FROM ban_table WHERE username = ?";
		try {
			PreparedStatement pstmt = this.getConnection().prepareStatement(sql);

			pstmt.setString(1, name);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setMuted(String user, boolean mute) {

		user = user.toLowerCase();

		String sql = "UPDATE user_table SET muted = ? WHERE username = ?";

		try {
			PreparedStatement pstmt = this.getConnection().prepareStatement(sql);

			pstmt.setBoolean(1, mute);
			pstmt.setString(2, user);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setBanned(String user, boolean ban) {

		user = user.toLowerCase();

		String sql = "UPDATE user_table SET banned = ? WHERE username = ?";

		try {
			PreparedStatement pstmt = this.getConnection().prepareStatement(sql);

			pstmt.setBoolean(1, ban);
			pstmt.setString(2, user);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}