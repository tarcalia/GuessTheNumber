package app.service;

import app.controller.MainController;
import app.domain.GuessResult;
import app.repository.GameRepository;
import app.domain.Game;

/**
 * Service interface for {@link MainController};
 */
public interface ControllerService {

    /**
     * Give access to {@link GameRepository}.
     * @return the repository containing {@link Game} objects.
     */
    GameService getGameService();

    /**
     * Gives information from {@link GameService} about the result of the player's guess numbers.
     * @param guess is the player's number.
     * @return the result of the guess, which can be number smaller/bigger or customer won the game.
     */
    String getAnalyseResult(String guess);
}
