package blog.model;

import java.util.Date;

public class BattleHistory {
  protected int battleId;
  protected Date created;
  protected Pokemons pokemon1;
  protected Pokemons pokemon2;
  protected Pokemons winner;

  public BattleHistory(int battleId) {
    this.battleId = battleId;
  }

  public BattleHistory(int battleId, Date created, Pokemons pokemon1, Pokemons pokemon2,
      Pokemons winner) {
    this.battleId = battleId;
    this.created = created;
    this.pokemon1 = pokemon1;
    this.pokemon2 = pokemon2;
    this.winner = winner;
  }

  public int getBattleId() {
    return battleId;
  }

  public void setBattleId(int battleId) {
    this.battleId = battleId;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Pokemons getPokemon1() {
    return pokemon1;
  }

  public void setPokemon1(Pokemons pokemon1) {
    this.pokemon1 = pokemon1;
  }

  public Pokemons getPokemon2() {
    return pokemon2;
  }

  public void setPokemon2(Pokemons pokemon2) {
    this.pokemon2 = pokemon2;
  }

  public Pokemons getWinner() {
    return winner;
  }

  public void setWinner(Pokemons winner) {
    this.winner = winner;
  }
}
