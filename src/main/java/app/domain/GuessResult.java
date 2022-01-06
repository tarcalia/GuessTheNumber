package app.domain;

/**
 * Enum class for {@link GuessResult} class.
 */
public enum GuessResult {
    SMALLER("Number is smaller"),
    BIGGER("Number is bigger"),
    WIN("You win");

    public final String label;

    GuessResult(String label) {
        this.label = label;
    }
}
