package app.service;

import app.domain.Game;

/**
 * Service interface for the game.
 */
public interface GameService {

    /**
     * Generate a new number for a new game.
     */
    void getNewNumber();

    /**
     * Check the {@link Game}'s guess.
     * @param playerTip current guess.
     */
    String analyseGuess(String playerTip);

}
