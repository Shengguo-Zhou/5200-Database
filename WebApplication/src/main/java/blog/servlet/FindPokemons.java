package blog.servlet;

import blog.dal.*;
import blog.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/findPokemon")
public class FindPokemons extends HttpServlet {
	
	protected PokemonsDao pokemonsDao;
	
	@Override
	public void init() throws ServletException {
		pokemonsDao = PokemonsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        Integer pokemonId = Integer.valueOf(req.getParameter("id"));
        Pokemons pokemons = null;
        if (pokemonId == null) {
            messages.put("success", "Please enter a valid id.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		pokemons = pokemonsDao.getPokemonFromPokemonId(pokemonId);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + pokemonId);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        }
        req.setAttribute("Pokemons", pokemons);
        
        req.getRequestDispatcher("/FindPokemon.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Pokemons pokemons = null;
        Integer pokemonId = Integer.valueOf(req.getParameter("id"));
        if (pokemonId == null) {
            messages.put("success", "Please enter a valid id.");
        } else {
        	try {
        		pokemons = pokemonsDao.getPokemonFromPokemonId(pokemonId);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + pokemonId);
        }
        req.setAttribute("Pokemons", pokemons);
        
        req.getRequestDispatcher("/FindPokemon.jsp").forward(req, resp);
    }
}