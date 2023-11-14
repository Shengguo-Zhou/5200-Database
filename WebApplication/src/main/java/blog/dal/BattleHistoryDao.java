package blog.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import blog.model.BattleHistory;
import blog.model.Pokemons;


public class BattleHistoryDao {

  protected ConnectionManager connectionManager;

  private static BattleHistoryDao instance = null;

  protected BattleHistoryDao() {
    connectionManager = new ConnectionManager();
  }

  public static BattleHistoryDao getInstance() {
    if (instance == null) {
      instance = new BattleHistoryDao();
    }
    return instance;
  }

  public BattleHistory create(BattleHistory battleHistory) throws SQLException {
    String insertBattleHistory =
        "INSERT INTO BattleHistory(Created,Pokemon1,Pokemon2,Winner) VALUES(?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertBattleHistory,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setTimestamp(1, new Timestamp(battleHistory.getCreated().getTime()));
      insertStmt.setInt(2, battleHistory.getPokemon1().getPokemonId());
      insertStmt.setInt(3, battleHistory.getPokemon2().getPokemonId());
      insertStmt.setInt(4, battleHistory.getWinner().getPokemonId());

      insertStmt.executeUpdate();

      resultKey = insertStmt.getGeneratedKeys();
      int battleId = -1;
      if (resultKey.next()) {
        battleId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      battleHistory.setBattleId(battleId);
      return battleHistory;
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
      if (resultKey != null) {
        resultKey.close();
      }
    }
  }

  public BattleHistory getBattleHistoryById(int battleId) throws SQLException {
    String selectReservation =
        "SELECT * FROM BattleHistory WHERE BattleId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectReservation);
      selectStmt.setInt(1, battleId);
      results = selectStmt.executeQuery();
      if (results.next()) {
        int resultBattleId = results.getInt("BattleId");
        Date created = new Date(results.getTimestamp("Created").getTime());
        // Pokemons pokemon1 = results.getInt("PokemonId1");
        // int pokemonId2 = results.getInt("PokemonId2");
        // int winnerId = results.getInt("WinnerId");

        BattleHistory battleHistory = new BattleHistory(resultBattleId);
        return battleHistory;
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

  public BattleHistory delete(BattleHistory battleHistory) throws SQLException {
    String deleteBattleHistory = "DELETE FROM BattleHistory WHERE BattleId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteBattleHistory);
      deleteStmt.setInt(1, battleHistory.getBattleId());
      deleteStmt.executeUpdate();

      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }
}
