package com.crudmanager.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

@Service
public class BookServiceIm implements BookService {
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private long generatedKey;


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void addBook(String title, String author){
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("INSERT INTO book(title,author)VALUES(?,?)",title,author );
		
	}
	
	public void deleteBook(String title, Long id){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("DELETE FROM book WHERE title='"+title+"' AND id="+id);
	}
	
	public List<String> atriskBooks(String author){
		String sql = "SELECT * FROM book WHERE author = "+ author;
		List<String> bookList = new ArrayList<String>();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.query(sql, new ResultSetExtractor<List>() {
			public List extractData(ResultSet rs) throws SQLException {
				
				while (rs.next()) {
					String title = rs.getString("title");
					bookList.add(title);
				}
				return bookList;
			}
		}
				
		
	);
	System.out.println("doglist");
	return bookList;
	}

	public long getGeneratedKey(String title, String author) {
		String sql ="INSERT INTO book(title,author)VALUES(?,?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
		public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			PreparedStatement statement = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, title );
			statement.setString(2, author);
			return statement;
		}
		}, holder);
		generatedKey = holder.getKey().longValue();
		System.out.println("generated key is " + generatedKey);
		return generatedKey;
	}


}
