package racingcar.formatter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;

public class ResultFormatterTest {
    private final ResultFormatter formatter = new ResultFormatter();

    private Car carAt(String name, int position) {
        Car car = new Car(name);
        for (int i = 0; i < position; i++) {
            car.move(4);
        }
        return car;
    }

    @Test
    void formatWinners_MultipleWinners() {
        // given
        List<Car> winners = List.of(
                carAt("pobi", 3),
                carAt("jin", 3),
                carAt("jun", 3)
        );

        //when
        String result = formatter.formatWinners(winners);

        //then
        assertThat(result).isEqualTo("pobi, jin, jun");
    }

    @Test
    void formatWinners_SingleWinner() {
        // given
        List<Car> winners = List.of(carAt("pobi", 3));

        //when
        String result = formatter.formatWinners(winners);

        //then
        assertThat(result).isEqualTo("pobi");
    }

    @Test
    void formatWinners_shouldReturnEmptyString() {
        // given
        List<Car> winners = List.of();

        //when
        String result = formatter.formatWinners(winners);

        //then
        assertThat(result).isEmpty();
    }
}
