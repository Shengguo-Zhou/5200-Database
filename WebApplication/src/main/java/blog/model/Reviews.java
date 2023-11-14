package blog.model;

import java.sql.Timestamp;
import java.math.BigDecimal;

public class Reviews {
    private int reviewsId;
    private Timestamp created;
    private String content;
    private BigDecimal rating;
    private String userName;
    private int pokemonId;

    // Constructor
    public Reviews(int reviewsId, Timestamp created, String content, BigDecimal rating, String userName, int pokemonId) {
        this.reviewsId = reviewsId;
        this.created = created;
        this.content = content;
        this.rating = rating;
        this.userName = userName;
        this.pokemonId = pokemonId;
    }

    // Getters
    public int getReviewsId() { return reviewsId; }
    public Timestamp getCreated() { return created; }
    public String getContent() { return content; }
    public BigDecimal getRating() { return rating; }
    public String getUserName() { return userName; }
    public int getPokemonId() { return pokemonId; }

    // Setters
    public void setReviewsId(int reviewsId) { this.reviewsId = reviewsId; }
    public void setCreated(Timestamp created) { this.created = created; }
    public void setContent(String content) { this.content = content; }
    public void setRating(BigDecimal rating) { this.rating = rating; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setPokemonId(int pokemonId) { this.pokemonId = pokemonId; }
}
