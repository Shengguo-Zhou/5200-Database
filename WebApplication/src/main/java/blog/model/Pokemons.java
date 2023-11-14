package blog.model;

public class Pokemons {
    protected int pokemonId;
    protected String name;
    protected int attack;
    protected int defense;
    protected Integer houseId;

    public Pokemons(int pokemonId, String name, int attack, int defense, Integer houseId) {
        this.pokemonId = pokemonId;
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.houseId = houseId;
    }

    public Pokemons(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    // Getters and setters for each field
    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }
}