package app.service.impl;

import app.domain.Game;
import app.repository.GameRepository;
import app.service.GameService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Service class for the game.
 */
@Service
public class GameHandler implements GameService {
    private GameRepository gameRepository;
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

    public GameHandler(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public void getNewNumber() {
        resetNumberOfTips();
        currentNumber = ThreadLocalRandom.current().nextInt(1, 100 + 1);
    }

    @Override
    public String analyseGuess(String playerTip) {
        if (playerTip == null) {
            throw new NullPointerException("Given number is null");
        }
        int intValue = Integer.parseInt(playerTip);
        numberOfTips++;
        String guessResult = "The number is bigger.";
        if (currentNumber.equals(intValue)) {
            saveGameToDataBase(intValue);
            guessResult = "You win!";
        }
        if (currentNumber < intValue) {
            guessResult = "The number is smaller.";
        }
        return guessResult;
    }

    private void resetNumberOfTips() {
        numberOfTips = 0;
    }

    private void saveGameToDataBase(Integer numberOfTips) {
        if (numberOfTips == null) {
            throw new NullPointerException("Null value added at saveGameToDataBase method.");
        }
        gameRepository.save(new Game(numberOfTips));
    }

}
