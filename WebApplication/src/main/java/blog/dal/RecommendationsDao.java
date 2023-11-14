package blog.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import blog.model.Recommendations;

public class RecommendationsDao {
    private ConnectionManager connectionManager;

    public RecommendationsDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    // CREATE
    public boolean insertRecommendation(Recommendations recommendation) throws SQLException {
        String sql = "INSERT INTO Recommendations (UserName, PokemonId) VALUES (?, ?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, recommendation.getUserName());
            statement.setInt(2, recommendation.getPokemonId());

            return statement.executeUpdate() > 0;
        }
    }

    // READ
    public List<Recommendations> getAllRecommendations() throws SQLException {
        List<Recommendations> recommendations = new ArrayList<>();
        String sql = "SELECT * FROM Recommendations";
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int recommendationId = resultSet.getInt("RecommendationId");
                String userName = resultSet.getString("UserName");
                int pokemonId = resultSet.getInt("PokemonId");

                Recommendations recommendation = new Recommendations(recommendationId, userName, pokemonId);
                recommendations.add(recommendation);
            }
        }
        return recommendations;
    }

    public Recommendations getRecommendationById(int recommendationId) throws SQLException {
        Recommendations recommendation = null;
        String sql  = "SELECT * FROM Recommendations WHERE RecommendationId = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, recommendationId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String userName = resultSet.getString("UserName");
                    int pokemonId = resultSet.getInt("PokemonId");

                    recommendation = new Recommendations(recommendationId, userName, pokemonId);
                }
            }
        }
        return recommendation;
    }

    // UPDATE
    public boolean updateRecommendation(Recommendations recommendation) throws SQLException {
        String sql = "UPDATE Recommendations SET UserName = ?, PokemonId = ? WHERE RecommendationId = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, recommendation.getUserName());
            statement.setInt(2, recommendation.getPokemonId());
            statement.setInt(3, recommendation.getRecommendationId());

            return statement.executeUpdate() > 0;
        }
    }

    // DELETE
    public boolean deleteRecommendation(int recommendationId) throws SQLException {
        String sql = "DELETE FROM Recommendations WHERE RecommendationId = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, recommendationId);
            return statement.executeUpdate() > 0;
        }
    }
}

