package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static racingcar.validator.UserInputValidator.BLANK_CAR_NAME;
import static racingcar.validator.UserInputValidator.DUPLICATE_CAR_NAME;
import static racingcar.validator.UserInputValidator.EMPTY_CAR_NAME_LIST;
import static racingcar.validator.UserInputValidator.EMPTY_TRY_COUNT;
import static racingcar.validator.UserInputValidator.LESS_THAN_ONE_TRY_COUNT;
import static racingcar.validator.UserInputValidator.NOT_NUMBER_TRY_COUNT;
import static racingcar.validator.UserInputValidator.TOO_LONG_CAR_NAME;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void application_runsRaceSuccessfully() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    void application_shouldThrowException_whenEmptyCarNames() {
        assertSimpleTest(() -> {
            runException("", "2");
            assertThat(output()).contains(EMPTY_CAR_NAME_LIST);
        });
    }

    @Test
    void application_shouldThrowException_whenBlankCarName() {
        assertSimpleTest(() -> {
            runException("jin,   ", "2");
            assertThat(output()).contains(BLANK_CAR_NAME);
        });
    }

    @Test
    void application_shouldThrowException_whenTryCountIsInvalid() {
        assertSimpleTest(() -> {
            runException("pobi, jieun ", "0");
            assertThat(output()).contains(LESS_THAN_ONE_TRY_COUNT);
        });
    }

    @Test
    void application_shouldThrowException_whenCarNameTooLong() {
        assertSimpleTest(() -> {
            runException("pobi,pobiiiii", "4");
            assertThat(output()).contains(TOO_LONG_CAR_NAME);
        });
    }

    @Test
    void application_shouldThrowException_whenDuplicateCarName() {
        assertSimpleTest(() -> {
            runException("pobi,pobi", "4");
            assertThat(output()).contains(DUPLICATE_CAR_NAME);
        });
    }

    @Test
    void application_shouldThrowException_whenEmptyTryCount() {
        assertSimpleTest(() -> {
            runException("pobi,jin", "  ");
            assertThat(output()).contains(EMPTY_TRY_COUNT);
        });
    }

    @Test
    void application_shouldThrowException_whenNotNumberTryCount() {
        assertSimpleTest(() -> {
            runException("pobi, jin", "*");
            assertThat(output()).contains(NOT_NUMBER_TRY_COUNT);
        });
    }

    @Test
    void application_shouldThrowException_whenLessThanOneTryCount() {
        assertSimpleTest(() -> {
            runException("pobi, jin", "0");
            assertThat(output()).contains(LESS_THAN_ONE_TRY_COUNT);
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
