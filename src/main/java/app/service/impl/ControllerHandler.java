package app.service.impl;

import app.service.ControllerService;
import app.controller.MainController;
import app.service.GameService;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
        String result = "result";
        if (Objects.equals(gameService.analyseGuess(guess), "You win!")) {
            result = "win";
        }
        return result;
    }


}
