package app.service;

import app.controller.MainController;

/**
 * Service interface for {@link MainController};
 */
public interface ControllerService {

    GameService getGameService();

    String getAnalyseResult(String guess);
}
