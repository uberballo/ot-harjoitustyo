/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author kasper
 */
public class HighScoreDao {

	private String url;

	public HighScoreDao() {
		this.url = "jdbc::sqlite:highscores.db";
		createNewTable();
	}

	private Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(this.url);
			System.out.println("Connection to sqlite has been established");
		} catch (SQLException e) {
			System.out.println(e);
		}
		return conn;
	}

	private void createNewTable() {
		String sql = "CREATE TABLE IF NOT EXISTS highscore (\n"
			+ "id integer PRIMARY KEY, \n"
			+ "name text NOT NULL, \n"
			+ "score integer \n"
			+ ");";

		try (Connection conn = DriverManager.getConnection(this.url);
			Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void insert(String name, int score) {
		String sql = "INSERT INTO highscore(name,score) VALUES(?,?)";
		try (Connection conn = connect();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setInt(2, score);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<Integer> getScores() {
		ArrayList<Integer> scores = new ArrayList<>();
		String sql = "SELECT score FROM highscore";
		try (Connection conn = connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				int value = rs.getInt("score");
				System.out.println(value);
				scores.add(value);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return scores;
	}

}
