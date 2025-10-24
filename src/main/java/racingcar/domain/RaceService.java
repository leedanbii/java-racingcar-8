package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RaceService {
    private static final String RACE_PROGRESS_SEPARATOR = " : ";
    private static final String RACE_STEP_MARK = "-";

    public void runRace(List<Car> cars, int tryCount) {
        for (int i = 0; i < tryCount; i++) {
            moveCars(cars);
            printRace(cars);
        }
    }

    public void moveCars(List<Car> cars) {
        for (Car car : cars) {
            int randomNumber = Randoms.pickNumberInRange(0, 9);
            car.move(randomNumber);
        }
    }

    public void printRace(List<Car> cars) {
        for (Car car : cars) {
            System.out.print(car.getName() + RACE_PROGRESS_SEPARATOR);
            for (int i = 0; i < car.getPosition(); i++) {
                System.out.print(RACE_STEP_MARK);
            }
            System.out.println();
        }
    }
}
