package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("", "2"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(EMPTY_CAR_NAME_LIST)
        );
    }

    @Test
    void application_shouldThrowException_whenBlankCarName() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("jin,   ", "2"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(BLANK_CAR_NAME)
        );
    }

    @Test
    void application_shouldThrowException_whenTryCountIsInvalid() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi, jieun ", "0"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(LESS_THAN_ONE_TRY_COUNT)
        );
    }

    @Test
    void application_shouldThrowException_whenCarNameTooLong() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,pobiiiii", "4"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(TOO_LONG_CAR_NAME)
        );
    }

    @Test
    void application_shouldThrowException_whenDuplicateCarName() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,pobi", "4"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(DUPLICATE_CAR_NAME)
        );
    }

    @Test
    void application_shouldThrowException_whenEmptyTryCount() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,jin", "  "))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(EMPTY_TRY_COUNT)
        );
    }

    @Test
    void application_shouldThrowException_whenNotNumberTryCount() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi, jin", "*"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(NOT_NUMBER_TRY_COUNT)
        );
    }

    @Test
    void application_shouldThrowException_whenLessThanOneTryCount() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi, jin", "0"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(LESS_THAN_ONE_TRY_COUNT)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
