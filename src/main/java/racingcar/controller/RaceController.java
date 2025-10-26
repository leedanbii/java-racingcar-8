package racingcar.controller;

import java.util.List;
import racingcar.converter.CarFactory;
import racingcar.domain.Car;
import racingcar.domain.RaceService;
import racingcar.dto.UserInput;
import racingcar.formatter.ResultFormatter;
import racingcar.validator.UserInputValidator;
import racingcar.view.Input;
import racingcar.view.Output;

public class RaceController {
    private final Input inputView;
    private final Output outputView;
    private final RaceService raceService;
    private final ResultFormatter resultFormatter;

    public RaceController(Input inputView, Output outputView,
                          RaceService raceService, ResultFormatter resultFormatter) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.raceService = raceService;
        this.resultFormatter = resultFormatter;
    }

    public void startRace() {
        UserInput userInput = inputView.readUserInput();

        List<String> carNames = UserInputValidator.validateAndParseCarNames(userInput.getCarNames());
        int tryCount = UserInputValidator.validateAndParseTryCount(userInput.getTryCount());

        List<Car> cars = CarFactory.createCars(carNames);

        List<Car> winners = raceService.runRace(cars, tryCount);
        String formattedResult = resultFormatter.formatWinners(winners);

        outputView.printWinners(formattedResult);
    }
}
