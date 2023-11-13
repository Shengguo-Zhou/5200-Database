package blog.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import blog.model.CreditCards;
import blog.model.Users;

public class CreditCardsDao {

	protected ConnectionManager connectionManager;
	private static CreditCardsDao instance = null;

	protected CreditCardsDao() {
		connectionManager = new ConnectionManager();
	}

	public static CreditCardsDao getInstance() {
		if (instance == null) {
			instance = new CreditCardsDao();
		}
		return instance;
	}

	public CreditCards create(CreditCards creditCard) throws SQLException {

		String insertCreditCard = "INSERT INTO CreditCards(CardNumber, Expiration, UserName) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCreditCard);
			insertStmt.setLong(1, creditCard.getCardNumber());
			insertStmt.setTimestamp(2, new Timestamp(creditCard.getExpiration().getTime()));
			insertStmt.setString(3, creditCard.getUser().getUserName());
			insertStmt.executeUpdate();
			return creditCard;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	public CreditCards getCreditCardByCardNumber(long cardNumber) throws SQLException {
		String selectCreditCard = "SELECT * FROM CreditCards WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCard);
			selectStmt.setLong(1, cardNumber);
			results = selectStmt.executeQuery();
			UsersDao userDao = UsersDao.getInstance();

			if (results.next()) {
				long resultCardNumber = results.getLong("CardNumber");
				Date expirationDate = results.getDate("Expiration");
				String userName = results.getString("UserName");
				Users user = userDao.getUserByUserName(userName);
				CreditCards creditCard = new CreditCards(resultCardNumber, expirationDate, user);
				return creditCard;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return null;
	}

	public CreditCards updateExpiration(CreditCards creditCard, java.util.Date date) throws SQLException {

		String updateExpirationDate = "UPDATE CreditCards SET Expiration=? WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateExpirationDate);
			updateStmt.setTimestamp(1, new Timestamp(date.getTime()));
			updateStmt.setLong(2, creditCard.getCardNumber());
			updateStmt.executeUpdate();

			creditCard.setExpiration(date);
			return creditCard;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	public CreditCards delete(CreditCards creditCard) throws SQLException {
		String deleteCreditCard = "DELETE FROM CreditCards WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCreditCard);
			deleteStmt.setLong(1, creditCard.getCardNumber());
			deleteStmt.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

}