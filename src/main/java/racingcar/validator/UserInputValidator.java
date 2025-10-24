package racingcar.validator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserInputValidator {

    private static final int MAX_NAME_LENGTH = 5;

    public static final String EMPTY_CAR_NAME_LIST = "자동차 이름 목록이 비어있습니다.";
    public static final String BLANK_CAR_NAME = "자동차 이름은 비어있거나 공백일 수 없습니다.";
    public static final String TOO_LONG_CAR_NAME = "자동차 이름은 5자 이하여야 합니다.";
    public static final String DUPLICATE_CAR_NAME = "자동차 이름은 중복될 수 없습니다.";

    public static final String EMPTY_TRY_COUNT = "시도 횟수를 입력해야 합니다.";
    public static final String NOT_NUMBER_TRY_COUNT = "시도 횟수는 숫자여야 합니다.";
    public static final String LESS_THAN_ONE_TRY_COUNT = "시도 횟수는 1 이상이어야 합니다.";

    private UserInputValidator() {
    }

    public static void validateCarNames(List<String> carNames) {
        validateCarNamesNotEmpty(carNames);
        validateCarNameNotBlank(carNames);
        validateCarNameLength(carNames);
        validateNoduplicateCarNames(carNames);
    }

    private static void validateCarNamesNotEmpty(List<String> carNames) {
        if (carNames == null || carNames.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_CAR_NAME_LIST);
        }
    }

    private static void validateCarNameNotBlank(List<String> carNames) {
        if (carNames.stream().anyMatch(name -> name == null || name.trim().isEmpty())) {
            throw new IllegalArgumentException(BLANK_CAR_NAME);
        }
    }

    private static void validateCarNameLength(List<String> carNames) {
        if (carNames.stream().anyMatch(name -> name.length() > MAX_NAME_LENGTH)) {
            throw new IllegalArgumentException(TOO_LONG_CAR_NAME);
        }
    }

    private static void validateNoduplicateCarNames(List<String> carNames) {
        Set<String> uniqueNames = carNames.stream()
                .map(String::trim)
                .collect(Collectors.toSet());

        if (uniqueNames.size() != carNames.size()) {
            throw new IllegalArgumentException(DUPLICATE_CAR_NAME);
        }
    }

    public static void validateTryCount(String tryCountStr) {
        validateTryCountNotEmpty(tryCountStr);

        int tryCountInt = parserInt(tryCountStr);
        validateTryCountPositive(tryCountInt);
    }

    private static void validateTryCountNotEmpty(String tryCountStr) {
        if (tryCountStr == null || tryCountStr.trim().isEmpty()) {
            throw new IllegalArgumentException(EMPTY_TRY_COUNT);
        }
    }

    private static int parserInt(String tryCountStr) {
        try {
            return Integer.parseInt(tryCountStr.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_TRY_COUNT);
        }
    }

    private static void validateTryCountPositive(int tryCountInt) {
        if (tryCountInt < 1) {
            throw new IllegalArgumentException(LESS_THAN_ONE_TRY_COUNT);
        }
    }
}
