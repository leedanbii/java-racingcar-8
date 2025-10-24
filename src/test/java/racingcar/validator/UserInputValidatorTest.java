package racingcar.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static racingcar.validator.UserInputValidator.*;

import java.util.List;
import org.junit.jupiter.api.Test;

public class UserInputValidatorTest {

    @Test
    void validateCarName_success() {
        String input = "pobi, jun, wojin";
        List<String> carNames = UserInputValidator.validateAndParseCarNames(input);

        assertThat(carNames).containsExactly("pobi", "jun", "wojin");
    }

    @Test
    void validateCarNames_emptyList_shouldThrow() {
        String input = "";

        assertThatThrownBy(() -> UserInputValidator.validateAndParseCarNames(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(EMPTY_CAR_NAME_LIST);
    }

    @Test
    void validateCarNames_blankName_shouldThrow() {
        String input = "pobi,  , jun";

        assertThatThrownBy(() -> UserInputValidator.validateAndParseCarNames(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BLANK_CAR_NAME);
    }

    @Test
    void validateCarNames_tooLongName_shouldThrow() {
        String input = "pobiiii, jun";

        assertThatThrownBy(() -> UserInputValidator.validateAndParseCarNames(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(TOO_LONG_CAR_NAME);
    }

    @Test
    void validateCarNames_duplicateName_shouldThrow() {
        String input = "pobi, jun, pobi";

        assertThatThrownBy(() -> UserInputValidator.validateAndParseCarNames(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DUPLICATE_CAR_NAME);
    }


    @Test
    void validateTryCount_success() {
        String input = "5";
        int tryCount = UserInputValidator.validateAndParseTryCount(input);
        assertThat(tryCount).isEqualTo(5);
    }

    @Test
    void validateTryCount_nullOrEmpty_shouldThrow() {
        assertThatThrownBy(() -> UserInputValidator.validateAndParseTryCount(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(EMPTY_TRY_COUNT);

        assertThatThrownBy(() -> UserInputValidator.validateAndParseTryCount("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(EMPTY_TRY_COUNT);
    }

    @Test
    void validateTryCount_notNumber_shouldThrow() {
        assertThatThrownBy(() -> UserInputValidator.validateAndParseTryCount("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_NUMBER_TRY_COUNT);
    }

    @Test
    void validateTryCount_lessThanOne_shouldThrow() {
        assertThatThrownBy(() -> UserInputValidator.validateAndParseTryCount("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LESS_THAN_ONE_TRY_COUNT);

        assertThatThrownBy(() -> UserInputValidator.validateAndParseTryCount("-5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LESS_THAN_ONE_TRY_COUNT);
    }
}
