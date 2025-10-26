package racingcar.controller;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.Assertions;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import racingcar.domain.RaceService;
import racingcar.dto.UserInput;
import racingcar.formatter.ResultFormatter;
import racingcar.view.Input;
import racingcar.view.Output;

public class RaceControllerTest {

    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void startRace_shouldPrintWinnersCorrectly() {
        // given
        Input testInput = new Input() {
            @Override
            public UserInput readUserInput() {
                return new UserInput("pobi,woni", "1");
            }
        };

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Output testOutput = new Output() {
            @Override
            public void printWinners(String winners) {
                System.out.print(winners);
            }
        };
        System.setOut(new PrintStream(out));

        RaceController controller = new RaceController(
                testInput,
                testOutput,
                new RaceService(),
                new ResultFormatter()
        );

        // when
        controller.startRace();

        // then
        String output = out.toString();
        assertThat(output).contains("pobi", "woni");
    }

    @Test
    void startRace_shouldRunRaceCorrectly_whenMultipleTryCount() {
        // given
        Input testInput = new Input() {
            @Override
            public UserInput readUserInput() {
                return new UserInput("pobi,jun,  woni", "4");
            }
        };
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Output testOutput = new Output() {
            @Override
            public void printWinners(String winners) {
                System.out.print(winners);
            }
        };
        System.setOut(new PrintStream(out));

        RaceController controller = new RaceController(
                testInput,
                testOutput,
                new RaceService(),
                new ResultFormatter()
        );

        // when
        controller.startRace();

        // then
        String output = out.toString();
        assertThat(output).isNotEmpty();
        assertThat(output).contains("pobi", "jun", "woni");
    }

    @Test
    void startRace_assertRandomNumberInRangeExample() {
        // given
        Input testInput = new Input() {
            @Override
            public UserInput readUserInput() {
                return new UserInput("pobi,woni", "1");
            }
        };

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Output testOutput = new Output() {
            @Override
            public void printWinners(String winners) {
                System.out.print(winners);
            }
        };
        System.setOut(new PrintStream(out));

        RaceController controller = new RaceController(
                testInput,
                testOutput,
                new RaceService(),
                new ResultFormatter()
        );

        // when
        Assertions.assertRandomNumberInRangeTest(
                controller::startRace,
                MOVING_FORWARD, STOP
        );

        // then
        String output = out.toString();
        assertThat(output).contains("pobi", "woni");
    }
}
