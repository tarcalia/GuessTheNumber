package app.controller;

import app.service.ControllerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Main controller for the application.
 */
@Controller
public class MainController {
    private ControllerService controllerService;

    public MainController(ControllerService controllerService) {
        this.controllerService = controllerService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        controllerService.getGameService().prepareNewGame();
        model.addAttribute("games", controllerService.getGameService().getGames().findAll());
        return "index";
    }

    @RequestMapping("/guess")
    public String guess(@ModelAttribute(value = "guess") String guess, Model model) {
        String result = controllerService.getGameService().analyseGuess(guess);
        controllerService.getGameService().handleGuess(guess);
        model.addAttribute("result", result);
        model.addAttribute("games", controllerService.getGameService().getGames().findAll());
        model.addAttribute("guesses", controllerService.getGameService().getActualGames().findAll());
        return controllerService.getAnalyseResult(guess);
    }
}
