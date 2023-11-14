package blog.dal;

import blog.model.Reviews;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewsDao {
    private ConnectionManager connectionManager;

    // Constructor
    public ReviewsDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    // CREATE operation
    public boolean insertReview(Reviews review) throws SQLException {
        String sql = "INSERT INTO Reviews (Created, Content, Rating, UserName, PokemonId) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setTimestamp(1, review.getCreated());
            statement.setString(2, review.getContent());
            statement.setBigDecimal(3, review.getRating());
            statement.setString(4, review.getUserName());
            statement.setInt(5, review.getPokemonId());

            return statement.executeUpdate() > 0;
        }
    }

    // READ operations
    public List<Reviews> getAllReviews() throws SQLException {
        List<Reviews> listReview = new ArrayList<>();
        String sql = "SELECT * FROM Reviews";
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("ReviewsId");
                Timestamp created = resultSet.getTimestamp("Created");
                String content = resultSet.getString("Content");
                BigDecimal rating = resultSet.getBigDecimal("Rating");
                String userName = resultSet.getString("UserName");
                int pokemonId = resultSet.getInt("PokemonId");

                Reviews review = new Reviews(id, created, content, rating, userName, pokemonId);
                listReview.add(review);
            }
        }
        return listReview;
    }

    public Reviews getReviewById(int id) throws SQLException {
        Reviews review = null;
        String sql = "SELECT * FROM Reviews WHERE ReviewsId = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Timestamp created = resultSet.getTimestamp("Created");
                    String content = resultSet.getString("Content");
                    BigDecimal rating = resultSet.getBigDecimal("Rating");
                    String userName = resultSet.getString("UserName");
                    int pokemonId = resultSet.getInt("PokemonId");

                    review = new Reviews(id, created, content, rating, userName, pokemonId);
                }
            }
        }
        return review;
    }

    // UPDATE operation
    public boolean updateReview(Reviews review) throws SQLException {
        String sql = "UPDATE Reviews SET Created = ?, Content = ?, Rating = ?, UserName = ?, PokemonId = ? WHERE ReviewsId = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setTimestamp(1, review.getCreated());
            statement.setString(2, review.getContent());
            statement.setBigDecimal(3, review.getRating());
            statement.setString(4, review.getUserName());
            statement.setInt(5, review.getPokemonId());
            statement.setInt(6, review.getReviewsId());

            return statement.executeUpdate() > 0;
        }
    }

    // DELETE operation
    public boolean deleteReview(int id) throws SQLException {
        String sql = "DELETE FROM Reviews WHERE ReviewsId = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }
}
