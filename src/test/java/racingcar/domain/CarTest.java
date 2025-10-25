package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CarTest {

    @Test
    void car_isCreated_withName() {
        Car car = new Car("pobi");

        assertThat(car.getName()).isEqualTo("pobi");
        assertThat(car.getPosition()).isZero();
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 8, 9})
    void carMove_whenRandomNumber_isFourOrMore(int randomNumber) {
        Car car = new Car("pobi");

        car.move(randomNumber);

        assertThat(car.getPosition()).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void carDoesNotMove_whenRandomNumber_isLessThanFour(int randomNumber) {
        Car car = new Car("pobi");

        car.move(randomNumber);

        assertThat(car.getPosition()).isZero();
    }

    @Test
    void carPosition_accumulates_whenMovingMultipleTimes() {
        Car car = new Car("pobi");

        car.move(4);
        car.move(3);
        car.move(8);

        assertThat(car.getPosition()).isEqualTo(2);
    }
}