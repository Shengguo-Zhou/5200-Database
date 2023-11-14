package blog.dal;

import blog.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NormalPokemonDao extends PokemonsDao{
    private static NormalPokemonDao instance = null;
    protected NormalPokemonDao() {
        super();
    }
    public static NormalPokemonDao getInstance() {
        if(instance == null) {
            instance = new NormalPokemonDao();
        }
        return instance;
    }

    public NormalPokemon create(NormalPokemon normalPokemon) throws SQLException {
        // Insert into the superclass table first.
        create(new Pokemons(normalPokemon.getPokemonId(), normalPokemon.getName(),
                normalPokemon.getAttack(), normalPokemon.getDefense(), normalPokemon.getHouseId()));

        String insertNormalPokemon = "INSERT INTO NormalPokemon(PokemonId,Against_Ground) VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertNormalPokemon);
            insertStmt.setInt(1, normalPokemon.getPokemonId());
            insertStmt.setDouble(2, normalPokemon.getAgainstGround());
            insertStmt.executeUpdate();
            return normalPokemon;
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


    public NormalPokemon updateAgainstGround(NormalPokemon normalPokemon, double newAgainstGround) throws SQLException {
        String updateNormalPokemon = "UPDATE NormalPokemon SET Against_Ground=? WHERE PokemonId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateNormalPokemon);
            updateStmt.setDouble(1, newAgainstGround);
            updateStmt.setInt(2, normalPokemon.getPokemonId());
            updateStmt.executeUpdate();

            normalPokemon.setAgainstGround(newAgainstGround);
            return normalPokemon;
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

    public NormalPokemon delete(NormalPokemon normalPokemon) throws SQLException {
        String deleteNormalPokemon = "DELETE FROM NormalPokemon WHERE PokemonId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteNormalPokemon);
            deleteStmt.setInt(1, normalPokemon.getPokemonId());
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

    public NormalPokemon getNormalPokemonByPokemonId(int pokemonId) throws SQLException {
        String selectNormalPokemon =
                "SELECT NormalPokemon.PokemonId, Name, Attack, Defense, HouseId, Against_Ground " +
                        "FROM NormalPokemon " +
                        "INNER JOIN Pokemons ON NormalPokemon.PokemonId = Pokemons.PokemonId " +
                        "WHERE NormalPokemon.PokemonId=?;";

        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectNormalPokemon);
            selectStmt.setInt(1, pokemonId);
            results = selectStmt.executeQuery();
            if(results.next()) {
                int resultPokemonId = results.getInt("PokemonId");
                String name = results.getString("Name");
                int attack = results.getInt("Attack");
                int defense = results.getInt("Defense");
                Integer houseId = (Integer) results.getObject("HouseId");
                double againstGround = results.getDouble("Against_Ground");
                NormalPokemon normalPokemon = new NormalPokemon(resultPokemonId, name, attack, defense, houseId, againstGround);
                return normalPokemon;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            // Close all resources to avoid potential leaks.
            if (results != null) {
                results.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }


}
