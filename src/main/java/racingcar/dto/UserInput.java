package racingcar.dto;

public class UserInput {
  private final String carNames;
  private final String attempts;

  public UserInput(String carNames, String attempts) {
    this.carNames = carNames;
    this.attempts = attempts;
  }

  public String getCarNames() {
    return carNames;
  }

  public String getAttempts() {
    return attempts;
  }
}
