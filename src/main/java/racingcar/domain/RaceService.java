package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RaceService {
    private static final String RACE_PROGRESS_SEPARATOR = " : ";
    private static final String RACE_STEP_MARK = "-";

    public List<Car> runRace(List<Car> cars, int tryCount) {
        System.out.println("\n실행 결과");
        for (int i = 0; i < tryCount; i++) {
            moveCars(cars);
            printRace(cars);
            System.out.println();
        }
        return findWinners(cars);
    }

    private void moveCars(List<Car> cars) {
        for (Car car : cars) {
            int randomNumber = Randoms.pickNumberInRange(0, 9);
            car.move(randomNumber);
        }
    }

    public String generateRaceOutPut(List<Car> cars) {
        StringBuilder sb = new StringBuilder();
        for (Car car : cars) {
            sb.append(car.getName())
                    .append(RACE_PROGRESS_SEPARATOR)
                    .append(RACE_STEP_MARK.repeat(car.getPosition()))
                    .append("\n");
        }
        return sb.toString();
    }

    private void printRace(List<Car> cars) {
        System.out.print(generateRaceOutPut(cars));
    }

    private List<Car> findWinners(List<Car> cars) {
        if (cars == null || cars.isEmpty()) {
            return List.of();
        }
        int maxPosition = cars
                .stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);

        return cars.stream().filter(car -> car.getPosition() == maxPosition).toList();
    }
}
