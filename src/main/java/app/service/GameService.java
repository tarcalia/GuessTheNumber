package app.service;

import app.domain.Game;
import app.repository.ActualGameRepository;
import app.repository.GameRepository;
import app.domain.ActualGame;

/**
 * Service interface for the game.
 */
public interface GameService {

    /**
     * Prepare a new number for a new game.
     */
    void prepareNewGame();

    /**
     * Analyse the player's guess number.
     * @param playerTip is the number given by the player.
     * @return the result of the analysed number.
     */
    String analyseGuess(String playerTip);

    /**
     * Handles the game main mechanic.
     * @param result given by player, which will be analysed.
     * @return the result of the guess number, which can be "number is smaller/bigger" or player won the game.
     */
    String handleGuess(String result);

    /**
     * Gives access to {@link GameRepository}.
     * @return repository containing {@link Game}s.
     */
    GameRepository getGames();

    /**
     * Gives access to {@link ActualGameRepository}.
     * @return repository containing {@link ActualGame}s.
     */
    ActualGameRepository getActualGames();

}
