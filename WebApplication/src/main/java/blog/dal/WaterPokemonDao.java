package blog.dal;

import blog.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data access object (DAO) class to interact with the underlying WaterPokemon table in your
 * MySQL instance. This is used to store {@link WaterPokemon} into your MySQL instance and
 * retrieve {@link WaterPokemon} from MySQL instance.
 */
public class WaterPokemonDao extends PokemonsDao {
    // Single pattern: instantiation is limited to one object.
    private static WaterPokemonDao instance = null;

    protected WaterPokemonDao() {
        super();
    }

    public static WaterPokemonDao getInstance() {
        if (instance == null) {
            instance = new WaterPokemonDao();
        }
        return instance;
    }

    public WaterPokemon create(WaterPokemon waterPokemon) throws SQLException {
        // Insert into the superclass table first.
        create(new Pokemons(waterPokemon.getPokemonId(), waterPokemon.getName(),
                waterPokemon.getAttack(), waterPokemon.getDefense(), waterPokemon.getHouseId()));

        String insertWaterPokemon = "INSERT INTO WaterPokemon(PokemonId,Against_Water) VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertWaterPokemon);
            insertStmt.setInt(1, waterPokemon.getPokemonId());
            insertStmt.setDouble(2, waterPokemon.getAgainstWater());
            insertStmt.executeUpdate();
            return waterPokemon;
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

    public WaterPokemon updateAgainstWater(WaterPokemon waterPokemon, double newAgainstWater) throws SQLException {
        String updateWaterPokemon = "UPDATE WaterPokemon SET Against_Water=? WHERE PokemonId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateWaterPokemon);
            updateStmt.setDouble(1, newAgainstWater);
            updateStmt.setInt(2, waterPokemon.getPokemonId());
            updateStmt.executeUpdate();

            waterPokemon.setAgainstWater(newAgainstWater);
            return waterPokemon;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(updateStmt != null) {
                updateStmt.close();
            }
            if(connection != null) {
                connection.close();
            }
        }
    }

    public WaterPokemon delete(WaterPokemon waterPokemon) throws SQLException {
        String deleteWaterPokemon = "DELETE FROM WaterPokemon WHERE PokemonId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteWaterPokemon);
            deleteStmt.setInt(1, waterPokemon.getPokemonId());
            deleteStmt.executeUpdate();

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(deleteStmt != null) {
                deleteStmt.close();
            }
            if(connection != null) {
                connection.close();
            }
        }
    }

    public WaterPokemon getWaterPokemonByPokemonId(int pokemonId) throws SQLException {
        String selectWaterPokemon =
                "SELECT WaterPokemon.PokemonId, Name, Attack, Defense, HouseId, Against_Water " +
                        "FROM WaterPokemon " +
                        "INNER JOIN Pokemons ON WaterPokemon.PokemonId = Pokemons.PokemonId " +
                        "WHERE WaterPokemon.PokemonId=?;";

        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectWaterPokemon);
            selectStmt.setInt(1, pokemonId);
            results = selectStmt.executeQuery();
            if(results.next()) {
                int resultPokemonId = results.getInt("PokemonId");
                String name = results.getString("Name");
                int attack = results.getInt("Attack");
                int defense = results.getInt("Defense");
                Integer houseId = (Integer) results.getObject("HouseId");
                double againstWater = results.getDouble("Against_Water");
                WaterPokemon waterPokemon = new WaterPokemon(resultPokemonId, name, attack, defense, houseId, againstWater);
                return waterPokemon;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(results != null) {
                results.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(connection != null) {
                connection.close();
            }
        }
        return null;
    }
}