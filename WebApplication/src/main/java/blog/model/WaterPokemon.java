package blog.model;

/**
 * WaterPokemon is a simple, plain old java objects (POJO).
 * It extends {@link Pokemons} and adds an additional attribute to represent the WaterPokemon table.
 */
public class WaterPokemon extends Pokemons {
    protected double againstWater;

    // Constructor that takes all attributes including those from the superclass.
    public WaterPokemon(int pokemonId, String name, int attack, int defense, Integer houseId, double againstWater) {
        super(pokemonId, name, attack, defense, houseId);
        this.againstWater = againstWater;
    }

    public WaterPokemon(int pokemonId) {
        super(pokemonId);
    }

    // Getters and setters for the againstWater attribute.
    public double getAgainstWater() {
        return againstWater;
    }

    public void setAgainstWater(double againstWater) {
        this.againstWater = againstWater;
    }
}
