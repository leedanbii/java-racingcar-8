package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import racingcar.dto.UserInput;

public class Input {
  private static final String CAR_NAMES_INPUT_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
  private static final String ATTEMPTS_INPUT_MESSAGE = "시도할 횟수는 몇 회인가요?";

  public UserInput readUserInput() {
    System.out.println(CAR_NAMES_INPUT_MESSAGE);
    String carName = Console.readLine();

    System.out.println(ATTEMPTS_INPUT_MESSAGE);
    String attempts = Console.readLine();

    return new UserInput(carName, attempts);
  }
}
