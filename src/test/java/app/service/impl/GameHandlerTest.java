package app.service.impl;

import app.repository.GameRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for {@link GameHandler} class.
 */
public class GameHandlerTest {
    private static final String FAKE_GUESS = "Fake guess";
    private static final String NUMBER_IS_BIGGER = "The number is bigger.";
    private static final String NUMBER_IS_SMALLER = "The number is smaller.";
    private static final String NUMBER_IS_EQUAL = "You win!";
    private static final String TIP_ONE = "11";
    private static final String TIP_TWO = "65";
    private static final String TIP_THREE = "15";
    private static final Integer NUMBER_TO_GUESS = 15;
    private static final Integer NUMBER_OF_TIPS = 0;

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameHandler underTest;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test(dataProvider = "sampleGuesses")
    public void testAnalyseGuess(String guess, String expected) {
        //given
        underTest.setCurrentNumber(NUMBER_TO_GUESS);
        underTest.setNumberOfTips(NUMBER_OF_TIPS);
        //when
        String result = underTest.analyseGuess(guess);
        //then
        assertThat(result, equalTo(expected));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testAnalyseGuessWithNPE() {
        //given
        //when
        underTest.analyseGuess(null);
        //then
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testAnalyseGuessWithNumberFormatException() {
        //given
        //when
        underTest.analyseGuess(FAKE_GUESS);
        //then
    }

    @DataProvider(name = "sampleGuesses")
    public Object[][] sampleGuesses() {
        return new Object [][] {
                {TIP_ONE, NUMBER_IS_BIGGER},
                {TIP_TWO, NUMBER_IS_SMALLER},
                {TIP_THREE, NUMBER_IS_EQUAL},
        };
    }
}