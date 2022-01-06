package app.service.impl;

import app.domain.ActualGame;
import app.domain.Game;
import app.domain.GuessResult;
import app.repository.GameRepository;
import app.service.GameService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static app.domain.GuessResult.*;

/**
 * Service class for {@link GameHandler}.
 */
@Service
public class GameHandler implements GameService {
    private GameRepository gameRepository;
    private List<ActualGame> actualGameList;
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

    public GameHandler(GameRepository gameRepository, List<ActualGame> actualGames) {
        this.gameRepository = gameRepository;
        this.actualGameList = actualGames;
    }

    public List<ActualGame> getActualGameList() {
        return actualGameList;
    }

    public void setActualGameList(List<ActualGame> actualGameList) {
        this.actualGameList = actualGameList;
    }

    @Override
    public GameRepository getGames() {
        return gameRepository;
    }

    @Override
    public List<ActualGame> getActualGames() {
        return getActualGameList();
    }

    @Override
    public void prepareNewGame() {
        resetNumberOfTips();
        actualGameList.clear();
        currentNumber = ThreadLocalRandom.current().nextInt(1, 100 + 1);
    }

    @Override
    public GuessResult analyseGuess(String playerTip) {
        if (playerTip == null) {
            throw new IllegalArgumentException("Given number is null");
        }
        GuessResult guessResult = BIGGER;
        if (currentNumber.equals(Integer.parseInt(playerTip))) {
            guessResult = WIN;
        }
        if (currentNumber < Integer.parseInt(playerTip)) {
            guessResult = SMALLER;
        }
        return guessResult;
    }

    @Override
    public String handleGuess(String result){
        if (analyseGuess(result) == null) {
            throw new IllegalArgumentException("Given number is null");
        }
        numberOfTips++;
        String resultString = "";
        switch(analyseGuess(result)) {
            case SMALLER:
                resultString = SMALLER.label;
                break;
            case BIGGER :
                resultString = BIGGER.label;
                break;
            case WIN:
                resultString = WIN.label;
                saveGameToDataBase(numberOfTips);
                break;
        }
        saveActualGameToDataBase(resultString, Integer.parseInt(result));
        return analyseGuess(result).label;
    }

    private void resetNumberOfTips() {
        numberOfTips = 0;
    }

    private void saveGameToDataBase(Integer numberOfTips) {
        if (numberOfTips == null) {
            throw new IllegalArgumentException("Null value added at saveGameToDataBase method.");
        }
        gameRepository.save(new Game(currentNumber, numberOfTips));
    }

    private void saveActualGameToDataBase(String playerTip, Integer numberOfTips) {
        if (playerTip == null || numberOfTips == null) {
            throw new IllegalArgumentException("Null value added at saveActualGameToDataBase method.");
        }
        actualGameList.add((new ActualGame(numberOfTips, playerTip)));
    }

}
