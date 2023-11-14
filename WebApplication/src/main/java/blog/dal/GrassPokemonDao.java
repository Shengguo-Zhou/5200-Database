package blog.dal;

import blog.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data access object (DAO) class to interact with the underlying GrassPokemon table in your
 * MySQL instance. This is used to store {@link GrassPokemon} into your MySQL instance and
 * retrieve {@link GrassPokemon} from MySQL instance.
 */
public class GrassPokemonDao extends PokemonsDao {
    private static GrassPokemonDao instance = null;
    protected GrassPokemonDao() {
        super();
    }
    public static GrassPokemonDao getInstance() {
        if(instance == null) {
            instance = new GrassPokemonDao();
        }
        return instance;
    }

    public GrassPokemon create(GrassPokemon grassPokemon) throws SQLException {
        // Insert into the superclass table first.
        create(new Pokemons(grassPokemon.getPokemonId(), grassPokemon.getName(),
                grassPokemon.getAttack(), grassPokemon.getDefense(), grassPokemon.getHouseId()));

        String insertGrassPokemon = "INSERT INTO GrassPokemon(PokemonId,Against_Flying) VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertGrassPokemon);
            insertStmt.setInt(1, grassPokemon.getPokemonId());
            insertStmt.setDouble(2, grassPokemon.getAgainstFlying());
            insertStmt.executeUpdate();
            return grassPokemon;
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


    /**
     * Update the Against_Flying attribute of the GrassPokemon instance.
     * This runs an UPDATE statement.
     */
    public GrassPokemon updateAgainstFlying(GrassPokemon grassPokemon, double newAgainstFlying) throws SQLException {
        String updateGrassPokemon = "UPDATE GrassPokemon SET Against_Flying=? WHERE PokemonId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateGrassPokemon);
            updateStmt.setDouble(1, newAgainstFlying);
            updateStmt.setInt(2, grassPokemon.getPokemonId());
            updateStmt.executeUpdate();

            // Update the GrassPokemon's againstFlying before returning to the caller.
            grassPokemon.setAgainstFlying(newAgainstFlying);
            return grassPokemon;
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

    /**
     * Delete the GrassPokemon instance.
     * This runs a DELETE statement.
     */
    public GrassPokemon delete(GrassPokemon grassPokemon) throws SQLException {
        String deleteGrassPokemon = "DELETE FROM GrassPokemon WHERE PokemonId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteGrassPokemon);
            deleteStmt.setInt(1, grassPokemon.getPokemonId());
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

    /**
     * Get the GrassPokemon record by fetching it from your MySQL instance.
     * This runs a SELECT statement and returns a single GrassPokemon instance.
     */
    public GrassPokemon getGrassPokemonByPokemonId(int pokemonId) throws SQLException {
        String selectGrassPokemon =
                "SELECT GrassPokemon.PokemonId, Name, Attack, Defense, HouseId, Against_Flying " +
                        "FROM GrassPokemon " +
                        "INNER JOIN Pokemons ON GrassPokemon.PokemonId = Pokemons.PokemonId " +
                        "WHERE GrassPokemon.PokemonId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectGrassPokemon);
            selectStmt.setInt(1, pokemonId);
            results = selectStmt.executeQuery();
            if(results.next()) {
                int resultPokemonId = results.getInt("PokemonId");
                String name = results.getString("Name");
                int attack = results.getInt("Attack");
                int defense = results.getInt("Defense");
                Integer houseId = (Integer) results.getObject("HouseId"); // This can be null, hence getObject is used
                double againstFlying = results.getDouble("Against_Flying");
                GrassPokemon grassPokemon = new GrassPokemon(resultPokemonId, name, attack, defense, houseId, againstFlying);
                return grassPokemon;
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
