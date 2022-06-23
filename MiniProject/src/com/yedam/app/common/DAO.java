package com.yedam.app.common;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DAO {
	// Oracle DB정보
	private String driver;
	private String oracleUrl;
	private String connectedId;
	private String connectedPwd;

	// 공통으로 사용할 필드
	protected Connection conn;
	protected Statement stmt;
	protected PreparedStatement pstmt;
	protected ResultSet rs;

	public DAO() {// 한번만 실행시키고자 한다면 생성자에서 실행
		dbConfig();
	}

	// DB에 접속하는 메소드
	public void connect() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(oracleUrl, connectedId, connectedPwd);
		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver loading fail");
		} catch (SQLException e) {
			System.out.println("DB connecting fail");
		}
	}

	// DB 정보를 가져오는 메소드
	private void dbConfig() {
		String resource = "config/db.properties";
		Properties properties = new Properties();

		try {
			String filePath = ClassLoader.getSystemClassLoader().getResource(resource).getPath(); // getResource 파일을
																									// 가져오기, getPath 정확한
																									// 위치 찾기
			properties.load(new FileInputStream(filePath));
		} catch (IOException e) {
			System.out.println("DB Config 실패 : " + e.toString());
		}
		driver = properties.getProperty("driver");
		oracleUrl = properties.getProperty("url");
		connectedId = properties.getProperty("id");
		connectedPwd = properties.getProperty("pwd");
	}

	// DB 접속 해제하는 메소드
	public void disconnect() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();// 비워두지말아야 함 e.prinsStackTrace라도 적기
		}
	}
}
