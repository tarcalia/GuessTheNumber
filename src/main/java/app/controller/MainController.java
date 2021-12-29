package app.controller;

import app.service.ControllerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String index() {
        controllerService.getGameService().getNewNumber();
        return "index";
    }

    @RequestMapping("/guess")
    public String guess(@ModelAttribute(value = "guess") String guess, Model model) {
        String result = controllerService.getGameService().analyseGuess(guess);
        model.addAttribute("result", result);
        return controllerService.getAnalyseResult(guess);
    }
}
