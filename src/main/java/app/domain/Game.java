package app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Domain class for {@link Game}.
 */
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer gameId;
    private Integer numberOfTips;

    public Game() {
    }

    public Game(Integer numberOfTips) {
        this.numberOfTips = numberOfTips;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getNumberOfTips() {
        return numberOfTips;
    }

    public void setNumberOfTips(Integer numberOfTips) {
        this.numberOfTips = numberOfTips;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(gameId, game.gameId) && Objects.equals(numberOfTips, game.numberOfTips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, numberOfTips);
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", numberOfTips=" + numberOfTips +
                '}';
    }
}
