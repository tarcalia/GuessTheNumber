package app.service.impl;

import app.domain.GuessResult;
import app.service.ControllerService;
import app.controller.MainController;
import app.service.GameService;
import org.springframework.stereotype.Service;

/**
 * Service class for {@link MainController}.
 */
@Service
public class ControllerHandler implements ControllerService {
    private GameService gameService;

    public ControllerHandler(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public GameService getGameService() {
        return gameService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public String getAnalyseResult(String guess) {
        if (guess == null) {
            throw new IllegalArgumentException("Null value given as parameter at getAnalyseResult method at ControllerHandler class");
        }
        return (gameService.analyseGuess(guess) == GuessResult.WIN)
                ? "win"
                : "result";
    }

}
