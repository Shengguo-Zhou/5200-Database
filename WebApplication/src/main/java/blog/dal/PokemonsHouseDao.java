package blog.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import blog.model.PokemonsHouse;

public class PokemonsHouseDao {
  protected ConnectionManager connectionManager;

  private static PokemonsHouseDao instance = null;

  protected PokemonsHouseDao() {
    connectionManager = new ConnectionManager();
  }

  public static PokemonsHouseDao getInstance() {
    if (instance == null) {
      instance = new PokemonsHouseDao();
    }
    return instance;
  }

  public PokemonsHouse create(PokemonsHouse pokemonsHouse) throws SQLException {
    String insertPokemonsHouse = "INSERT INTO PokemonsHouse(HouseId,Description) VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;

    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertPokemonsHouse);

      insertStmt.setInt(1, pokemonsHouse.getHouseId());
      insertStmt.setString(2, pokemonsHouse.getDescription());

      insertStmt.executeUpdate();

      return pokemonsHouse;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(insertStmt != null) {
        insertStmt.close();
      }
    }
  }

  public PokemonsHouse delete(PokemonsHouse pokemonsHouse) throws SQLException {
    String deletePokemonsHouse = "DELETE FROM PokemonsHouse WHERE HouseId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deletePokemonsHouse);
      deleteStmt.setInt(1, pokemonsHouse.getHouseId());
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

  public PokemonsHouse getPokemonHouseByHouseId(int houseId) throws SQLException {
    String selectPokemonsHouse = "SELECT * FROM PokemonsHouse WHERE HouseId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectPokemonsHouse);
      selectStmt.setInt(1, houseId);
      results = selectStmt.executeQuery();
      if (results.next()) {
        int resultPokemonHouse = results.getInt("HouseId");
        String description = results.getString("Description");
        PokemonsHouse pokemonsHouse = new PokemonsHouse(resultPokemonHouse, description);
        return pokemonsHouse;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return null;
  }
}
