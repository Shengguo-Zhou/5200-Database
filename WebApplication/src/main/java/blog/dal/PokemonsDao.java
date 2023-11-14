package blog.dal;

import blog.model.Pokemons;

import java.sql.*;

/**
 * Data access object (DAO) class to interact with the underlying Pokemons table in your MySQL
 * instance. This is used to store {@link Pokemons} into your MySQL instance and retrieve
 * {@link Pokemons} from MySQL instance.
 */
public class PokemonsDao {
    protected ConnectionManager connectionManager;

    private static PokemonsDao instance = null;
    protected PokemonsDao() {
        connectionManager = new ConnectionManager();
    }
    public static PokemonsDao getInstance() {
        if(instance == null) {
            instance = new PokemonsDao();
        }
        return instance;
    }

    /**
     * Save the Pokemons instance by storing it in your MySQL instance.
     * This runs a INSERT statement.
     */
    public Pokemons create(Pokemons pokemon) throws SQLException {
        String insertPokemon = "INSERT INTO Pokemons(PokemonId,Name,Attack,Defense,HouseId) VALUES(?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertPokemon);

            insertStmt.setInt(1, pokemon.getPokemonId());
            insertStmt.setString(2, pokemon.getName());
            insertStmt.setInt(3, pokemon.getAttack());
            insertStmt.setInt(4, pokemon.getDefense());
            if(pokemon.getHouseId() == null) {
                insertStmt.setNull(5, Types.INTEGER);
            } else {
                insertStmt.setInt(5, pokemon.getHouseId());
            }
            insertStmt.executeUpdate();
            return pokemon;
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


    public Pokemons delete(Pokemons pokemon) throws SQLException {
        String deletePokemon = "DELETE FROM Pokemons WHERE PokemonId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deletePokemon);
            // Set the parameter for the prepared statement.
            deleteStmt.setInt(1, pokemon.getPokemonId());
            deleteStmt.executeUpdate();

            // Return null so the caller knows the Pokemons instance has been removed.
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (deleteStmt != null) {
                deleteStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public Pokemons getPokemonFromPokemonId(int pokemonId) throws SQLException {
        String selectPokemon = "SELECT PokemonId, Name, Attack, Defense, HouseId FROM Pokemons WHERE PokemonId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPokemon);
            selectStmt.setInt(1, pokemonId);

            results = selectStmt.executeQuery();

            if(results.next()) {
                int resultPokemonId = results.getInt("PokemonId");
                String name = results.getString("Name");
                int attack = results.getInt("Attack");
                int defense = results.getInt("Defense");
                Integer houseId = (Integer) results.getObject("HouseId");
                Pokemons pokemon = new Pokemons(resultPokemonId, name, attack, defense, houseId);
                return pokemon;
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

    public Pokemons updateName(Pokemons pokemon, String newName) throws SQLException {
        String updatePokemon = "UPDATE Pokemons SET Name=? WHERE PokemonId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updatePokemon);
            updateStmt.setString(1, newName);
            updateStmt.setInt(2, pokemon.getPokemonId());
            updateStmt.executeUpdate();

            pokemon.setName(newName);
            return pokemon;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (updateStmt != null) {
                updateStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


}

