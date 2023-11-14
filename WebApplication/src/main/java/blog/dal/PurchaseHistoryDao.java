package blog.dal;

import blog.model.PurchaseHistory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseHistoryDao {
    private ConnectionManager connectionManager;

    public PurchaseHistoryDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    // CREATE
    public boolean insertPurchaseHistory(PurchaseHistory purchaseHistory) throws SQLException {
        String sql = "INSERT INTO PurchaseHistory (UserName, PokemonId, Cost) VALUES (?, ?, ?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, purchaseHistory.getUserName());
            statement.setInt(2, purchaseHistory.getPokemonId());
            statement.setDouble(3, purchaseHistory.getCost());

            return statement.executeUpdate() > 0;
        }
    }

    // READ
    public List<PurchaseHistory> getAllPurchaseHistories() throws SQLException {
        List<PurchaseHistory> purchaseHistories = new ArrayList<>();
        String sql = "SELECT * FROM PurchaseHistory";
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int purchaseId = resultSet.getInt("PurchaseId");
                Timestamp created = resultSet.getTimestamp("Created");
                String userName = resultSet.getString("UserName");
                int pokemonId = resultSet.getInt("PokemonId");
                double cost = resultSet.getDouble("Cost");

                PurchaseHistory purchaseHistory = new PurchaseHistory(purchaseId, created, userName, pokemonId, cost);
                purchaseHistories.add(purchaseHistory);
            }
        }
        return purchaseHistories;
    }

    public PurchaseHistory getPurchaseHistoryById(int purchaseId) throws SQLException {
        PurchaseHistory purchaseHistory = null;
        String sql = "SELECT * FROM PurchaseHistory WHERE PurchaseId = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, purchaseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Timestamp created = resultSet.getTimestamp("Created");
                    String userName = resultSet.getString("UserName");
                    int pokemonId = resultSet.getInt("PokemonId");
                    double cost = resultSet.getDouble("Cost");

                    purchaseHistory = new PurchaseHistory(purchaseId, created, userName, pokemonId, cost);
                }
            }
        }
        return purchaseHistory;
    }

    // UPDATE
    public boolean updatePurchaseHistory(PurchaseHistory purchaseHistory) throws SQLException {
        String sql = "UPDATE PurchaseHistory SET UserName = ?, PokemonId = ?, Cost = ? WHERE PurchaseId = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, purchaseHistory.getUserName());
            statement.setInt(2, purchaseHistory.getPokemonId());
            statement.setDouble(3, purchaseHistory.getCost());
            statement.setInt(4, purchaseHistory.getPurchaseId());

            return statement.executeUpdate() > 0;
        }
    }

    // DELETE
    public boolean deletePurchaseHistory(int purchaseId) throws SQLException {
        String sql = "DELETE FROM PurchaseHistory WHERE PurchaseId = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, purchaseId);
            return statement.executeUpdate() > 0;
        }
    }
}
