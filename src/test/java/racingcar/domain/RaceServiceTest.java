package racingcar.domain;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.converter.CarFactory;

public class RaceServiceTest {

    private RaceService raceService;

    @BeforeEach
    void setUp() {
        raceService = new RaceService();
    }

    @Test
    void findWinners_shouldReturnMaxPositionCars() throws Exception {
        // given
        Car car1 = new Car("pobi");
        Car car2 = new Car("jin");
        Car car3 = new Car("jun");

        car1.move(5);
        car2.move(3);
        car3.move(5);

        var findWinners = RaceService.class.getDeclaredMethod("findWinners", List.class);
        findWinners.setAccessible(true);

        // when
        List<Car> winners = invokePrivateFindWinners(List.of(car1, car2, car3));

        // then
        assertThat(winners).extracting(Car::getName).containsExactlyInAnyOrder("pobi", "jun");
    }

    @Test
    void findWinners_emptyList_returnsEmpty() throws Exception {
        //given
        var findWinners = RaceService.class.getDeclaredMethod("findWinners", List.class);
        findWinners.setAccessible(true);

        //when
        List<Car> winners = invokePrivateFindWinners(List.of());

        //then
        assertThat(winners).isEmpty();
    }

    @Test
    void generateRaceOutput_shouldReturnCorrectFormat() {
        // given
        Car car1 = new Car("pobi");
        Car car2 = new Car("woni");

        car1.move(5);
        car2.move(3);

        // when
        String output = raceService.generateRaceOutPut(List.of(car1, car2));

        // then
        assertThat(output).contains("pobi : -", "woni : ");
    }

    @Test
    void moveCars_shouldMoveCars_accordingToRandomNumbers() {
        Car car1 = new Car("pobi");
        Car car2 = new Car("jun");
        List<Car> cars = List.of(car1, car2);

        // assertRandomNumberInRangeTest를 사용하면 내부에서 랜덤을 통제할 수 있음
        assertRandomNumberInRangeTest(
                () -> {
                    invokePrivateMoveCars(cars);

                    assertThat(car1.getPosition()).isBetween(0, 1);
                    assertThat(car2.getPosition()).isBetween(0, 1);
                },
                4, 3
        );
    }

    @Test
    void runRace_shouldReturnWinners() {
        // given
        List<Car> cars = CarFactory.createCars(List.of("pobi", "jin"));

        // when
        List<Car> winners = raceService.runRace(cars, 1);

        // then
        assertThat(winners).isNotEmpty();
        assertThat(winners).allSatisfy(car -> assertThat(car.getPosition()).isGreaterThanOrEqualTo(0));
    }


    @SuppressWarnings("unchecked")
    private List<Car> invokePrivateFindWinners(List<Car> cars) throws Exception {
        Method findWinners = RaceService.class.getDeclaredMethod("findWinners", List.class);
        findWinners.setAccessible(true);
        return (List<Car>) findWinners.invoke(raceService, cars);
    }

    private void invokePrivateMoveCars(List<Car> cars) {
        try {
            Method moveCars = RaceService.class.getDeclaredMethod("moveCars", List.class);
            moveCars.setAccessible(true);
            moveCars.invoke(raceService, cars);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
