package app.controller;

import app.service.ControllerService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller to handle exceptions.
 */
@ControllerAdvice
public class ExceptionGeneral {
    private ControllerService controllerService;

    public ExceptionGeneral(ControllerService controllerService) {
        this.controllerService = controllerService;
    }

    @ExceptionHandler(NumberFormatException.class)
    public String ex(Exception exception, Model model) {
        model.addAttribute("games", controllerService.getGameService().getGames().findAll());
        model.addAttribute("guesses", controllerService.getGameService().getActualGames());
        model.addAttribute("exception", exception);
        return "exception";
    }

}
