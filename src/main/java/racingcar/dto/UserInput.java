package racingcar.dto;

public class UserInput {
    private final String carNames;
    private final String tryCount;

    public UserInput(String carNames, String tryCount) {
        this.carNames = carNames;
        this.tryCount = tryCount;
    }

    public String getCarNames() {
        return carNames;
    }

    public String getTryCount() {
        return tryCount;
    }
}
