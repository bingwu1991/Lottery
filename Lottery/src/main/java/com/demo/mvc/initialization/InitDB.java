package com.demo.mvc.initialization;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author wubing
 *
 */
@Service
public class InitDB {

	private static final Logger logger = LoggerFactory.getLogger(InitDB.class);

	@Value("${url}")
	private String dbUrl;

	@PostConstruct
	public void initDB() {
		checkDBFile();
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(dbUrl);
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.executeUpdate(
					"create table if not exists history_data (period varchar(64) primary key,first int,second int,last int)");
			statement.execute("alter table history_data add column four int");
			statement.execute("alter table history_data add column five int");
			statement.execute("alter table history_data add column type int");
			statement.execute("update history_data set type = 0");

			statement.execute("alter table history_data rename to history_data_old_20161230");

			statement.execute("create table history_data(period varchar(64),type int not null,"
					+ "first int,second int,last int,four int,five int,primary key (period asc, type))");

			statement.execute("insert into history_data(period, type, first, second, last, four, five)"
					+ " select period, type, first, second, last, four, five from history_data_old_20161230");

		} catch (ClassNotFoundException e) {
			logger.error("can't load jdbc driver", e);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}

	private void checkDBFile() {
		File db = new File("D:/lottery/lottery.db");
		if (!db.exists()) {
			logger.info("db is not exist,create new db");
			try {
				File parent = db.getParentFile();
				if (!parent.exists()) {
					parent.mkdirs();
				}
				db.createNewFile();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

}
