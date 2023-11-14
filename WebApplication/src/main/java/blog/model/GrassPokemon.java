package blog.model;

/**
 * GrassPokemon is a simple, plain old java objects (POJO).
 * It extends {@link Pokemons} and adds an additional attribute to represent the GrassPokemon table.
 */
public class GrassPokemon extends Pokemons {
    protected double againstFlying;

    public GrassPokemon(int pokemonId, String name, int attack, int defense, Integer houseId, double againstFlying) {
        super(pokemonId, name, attack, defense, houseId);
        this.againstFlying = againstFlying;
    }

    public GrassPokemon(int pokemonId) {
        super(pokemonId);
    }

    // Getter and setter for the againstFlying attribute
    public double getAgainstFlying() {
        return againstFlying;
    }

    public void setAgainstFlying(double againstFlying) {
        this.againstFlying = againstFlying;
    }
}
