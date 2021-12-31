package app.service.impl;

import app.domain.ActualGame;
import app.domain.Game;
import app.repository.ActualGameRepository;
import app.repository.GameRepository;
import app.service.GameService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Service class for {@link GameHandler}.
 */
@Service
public class GameHandler implements GameService {
    private GameRepository gameRepository;
    private ActualGameRepository actualGameRepository;
    private Integer currentNumber;
    private Integer numberOfTips;

    public Integer getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(Integer currentNumber) {
        this.currentNumber = currentNumber;
    }

    public Integer getNumberOfTips() {
        return numberOfTips;
    }

    public void setNumberOfTips(Integer numberOfTips) {
        this.numberOfTips = numberOfTips;
    }

    public GameHandler(GameRepository gameRepository, ActualGameRepository actualGameRepository) {
        this.gameRepository = gameRepository;
        this.actualGameRepository = actualGameRepository;
    }

    @Override
    public GameRepository getGames() {
        return gameRepository;
    }

    @Override
    public ActualGameRepository getActualGames() {
        return actualGameRepository;
    }

    @Override
    public void prepareNewGame() {
        resetNumberOfTips();
        actualGameRepository.deleteAll();
        currentNumber = ThreadLocalRandom.current().nextInt(1, 100 + 1);
    }

    @Override
    public String analyseGuess(String playerTip) {
        if (playerTip == null) {
            throw new NullPointerException("Given number is null");
        }
        String guessResult = "The number is bigger.";
        if (currentNumber.equals(Integer.parseInt(playerTip))) {
            guessResult = "You win!";
        }
        if (currentNumber < Integer.parseInt(playerTip)) {
            guessResult = "The number is smaller.";
        }
        return guessResult;
    }

    @Override
    public String handleGuess(String result){
        if (analyseGuess(result) == null) {
            throw new NullPointerException("Given number is null");
        }
        numberOfTips++;
        if (analyseGuess(result).equals("You win!")) {
            saveGameToDataBase(numberOfTips);
        }
        saveActualGameToDataBase(analyseGuess(result), Integer.parseInt(result));
        return analyseGuess(result);
    }

    private void resetNumberOfTips() {
        numberOfTips = 0;
    }

    private void saveGameToDataBase(Integer numberOfTips) {
        if (numberOfTips == null) {
            throw new NullPointerException("Null value added at saveGameToDataBase method.");
        }
        gameRepository.save(new Game(currentNumber, numberOfTips));
    }

    private void saveActualGameToDataBase(String playerTip, Integer numberOfTips) {
        if (playerTip == null || numberOfTips == null) {
            throw new NullPointerException("Null value added at saveActualGameToDataBase method.");
        }
        actualGameRepository.save(new ActualGame(numberOfTips, playerTip));
    }

}
