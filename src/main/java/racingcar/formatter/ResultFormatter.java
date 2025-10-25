package racingcar.formatter;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;

public class ResultFormatter {
    private static final String WINNER_DELIMITER = ", ";

    public String formatWinners(List<Car> winners) {
        return winners.stream().map(Car::getName).collect(Collectors.joining(WINNER_DELIMITER));
    }
}
