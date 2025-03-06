package com.shop.cafe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.shop.cafe.dto.Member;

@Repository // @Component 와 같음
public class MemberDao {
	
	@Value("${spring.datasource.driver-class-name}")
	private String DB_DRIVER;
	
	@Value("${spring.datasource.url}")
	private String DB_URL;
	
	@Value("${spring.datasource.username}")
	private String DB_USER;
	
	@Value("${spring.datasource.password}")
	private String DB_PW;

	public Member login(Member m) throws Exception {
		Class.forName(DB_DRIVER);
		String sql = "select * from member where email='" + m.getEmail() + "' and pw='" + m.getPw() +"' "; //문자열이기 때문에 ' ' 추가
		try(
			Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql)
				) {
			if(rs.next()) { // login ok
				String nickname = rs.getString("nickname");
				m.setNickname(nickname); // Member 객체에 닉네임을 채워줌
				return m;
			} else {
				return null;
			}
		}
	}
	
	public void insertMember(Member m) throws Exception {
		Class.forName(DB_DRIVER);
		String sql = "insert into member(nickname, pw, email) values(?, ?, ?)";
		try(
			Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
			PreparedStatement stmt = con.prepareStatement(sql);
				
				) {
			stmt.setString(1, m.getNickname());
			stmt.setString(2, m.getPw());;
			stmt.setString(3, m.getEmail());
			int i = stmt.executeUpdate();
			System.out.println(i + "행이 insert되었습니다");
		}
	}
}
