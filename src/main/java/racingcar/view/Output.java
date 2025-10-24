package racingcar.view;

public class Output {
  private static final String WINNER_OUTPUT_MESSAGE = "최종 우승자 : ";

  public void printWinners(String winners) {
    System.out.println(WINNER_OUTPUT_MESSAGE + winners);
  }
}
