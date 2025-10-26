package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CarTest {

    @Test
    void car_isCreatedWithName() {
        Car car = new Car("pobi");

        assertThat(car.getName()).isEqualTo("pobi");
        assertThat(car.getPosition()).isZero();
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 8, 9})
    void carMove_whenRandomNumberIsFourOrMore(int randomNumber) {
        Car car = new Car("pobi");

        car.move(randomNumber);

        assertThat(car.getPosition()).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void carDoesNotMove_whenRandomNumberIsLessThanFour(int randomNumber) {
        Car car = new Car("pobi");

        car.move(randomNumber);

        assertThat(car.getPosition()).isZero();
    }

    @Test
    void carPosition_accumulates_whenMovingMultipleTimes() {
        Car car = new Car("pobi");
        int[] moves = {4, 3, 8};

        IntStream.of(moves).forEach(car::move);

        assertThat(car.getPosition()).isEqualTo(2);
    }
}