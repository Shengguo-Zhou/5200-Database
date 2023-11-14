package blog.model;


public class NormalPokemon extends Pokemons {
    protected double againstGround;

    public NormalPokemon(int pokemonId, String name, int attack, int defense, Integer houseId, double againstGround) {
        super(pokemonId, name, attack, defense, houseId);
        this.againstGround = againstGround;
    }

    public NormalPokemon(int pokemonId) {
        super(pokemonId);
    }

    // Getter and setter for the againstGround attribute.
    public double getAgainstGround() {
        return againstGround;
    }

    public void setAgainstGround(double againstGround) {
        this.againstGround = againstGround;
    }
}

