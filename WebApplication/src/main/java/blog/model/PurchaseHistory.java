package blog.model;

import java.sql.Timestamp;

public class PurchaseHistory {
    private int purchaseId;
    private Timestamp created;
    private String userName;
    private int pokemonId;
    private double cost;

    // Constructor
    public PurchaseHistory(int purchaseId, Timestamp created, String userName, int pokemonId, double cost) {
        this.purchaseId = purchaseId;
        this.created = created;
        this.userName = userName;
        this.pokemonId = pokemonId;
        this.cost = cost;
    }

    // Getters
    public int getPurchaseId() { return purchaseId; }
    public Timestamp getCreated() { return created; }
    public String getUserName() { return userName; }
    public int getPokemonId() { return pokemonId; }
    public double getCost() { return cost; }

    // Setters
    public void setPurchaseId(int purchaseId) { this.purchaseId = purchaseId; }
    public void setCreated(Timestamp created) { this.created = created; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setPokemonId(int pokemonId) { this.pokemonId = pokemonId; }
    public void setCost(double cost) { this.cost = cost; }
}
