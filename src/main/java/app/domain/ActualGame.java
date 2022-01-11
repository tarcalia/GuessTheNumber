package app.domain;
import java.util.Objects;

/**
 * Domain class for {@link ActualGame}.
 */

public class ActualGame {

    private Integer number;
    private String result;

    public ActualGame() {
    }

    public ActualGame(Integer number, String result) {
        this.number = number;
        this.result = result;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActualGame that = (ActualGame) o;
        return Objects.equals(number, that.number) && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, result);
    }

    @Override
    public String toString() {
        return "ActualGame{" +
                "number=" + number +
                ", result='" + result + '\'' +
                '}';
    }
}
