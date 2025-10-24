package racingcar.converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InputParser {
    private static final String DELIMITER = ",";

    public static List<String> parseCarNames(String carNamesInput) {
        if (carNamesInput == null || carNamesInput.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(carNamesInput.split(DELIMITER)).map(String::trim).toList();
    }

    public static Integer parseTryCount(String tryCountInput) {
        return Integer.parseInt(tryCountInput.trim());
    }
}
