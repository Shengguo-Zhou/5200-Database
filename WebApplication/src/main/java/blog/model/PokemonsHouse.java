package blog.model;

public class PokemonsHouse {
  protected int houseId;
  protected String description;

  public PokemonsHouse(int houseId, String description) {
    this.houseId = houseId;
    this.description = description;
  }

  public PokemonsHouse(int houseId) {
    this.houseId = houseId;
  }

  public int getHouseId() {
    return houseId;
  }

  public void setHouseId(int houseId) {
    this.houseId = houseId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
