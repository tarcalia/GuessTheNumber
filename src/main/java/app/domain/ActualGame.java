package app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Domain class for {@link ActualGame}.
 */
@Entity
public class ActualGame {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer numberId;
    private Integer number;
    private String result;

    public ActualGame() {
    }

    public ActualGame(Integer number, String result) {
        this.number = number;
        this.result = result;
    }

    public Integer getNumberId() {
        return numberId;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
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
        return Objects.equals(numberId, that.numberId) && Objects.equals(number, that.number) && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberId, number, result);
    }

    @Override
    public String toString() {
        return "ActualGame{" +
                "numberId=" + numberId +
                ", number=" + number +
                ", result='" + result + '\'' +
                '}';
    }
}
