package app.service.impl;

import app.domain.GuessResult;
import app.service.GameService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for {@link ControllerHandler} class.
 */
public class ControllerHandlerTest {
    private static final String SAMPLE_STRING_FROM_USER = "15";
    private static final GuessResult GAME_SERVICE_WIN = GuessResult.WIN;
    private static final GuessResult GAME_SERVICE_TRY_AGAIN = GuessResult.SMALLER;
    private static final String WIN = "win";
    private static final String RESULT = "result";

    @Mock
    private GameService gameService;

    @InjectMocks
    private ControllerHandler underTest;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test(dataProvider = "sampleResults")
    public void testGetAnalyseResult(String guess, String gameServiceResult, String expected) {
        //given
        when(gameService.analyseGuess(guess)).thenReturn(GuessResult.valueOf(gameServiceResult));
        //when
        String result = underTest.getAnalyseResult(guess);
        //then
        assertThat(result, equalTo(expected));
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetAnalyseResultWithNPE() {
        //given
        //when
        underTest.getAnalyseResult(null);
        //then
    }

    @DataProvider(name = "sampleResults")
    public Object[][] sampleResults() {
        return new Object [][] {
                {SAMPLE_STRING_FROM_USER, GAME_SERVICE_WIN, WIN},
                {SAMPLE_STRING_FROM_USER, GAME_SERVICE_TRY_AGAIN, RESULT},
        };
    }

}
