package blog.model;

public class Recommendations {
    private int recommendationId;
    private String userName;
    private int pokemonId;

    // Constructor
    public Recommendations(int recommendationId, String userName, int pokemonId) {
        this.recommendationId = recommendationId;
        this.userName = userName;
        this.pokemonId = pokemonId;
    }

    // Getters
    public int getRecommendationId() { return recommendationId; }
    public String getUserName() { return userName; }
    public int getPokemonId() { return pokemonId; }

    // Setters
    public void setRecommendationId(int recommendationId) { this.recommendationId = recommendationId; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setPokemonId(int pokemonId) { this.pokemonId = pokemonId; }
}
