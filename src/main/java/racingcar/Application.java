package racingcar;

import racingcar.controller.RaceController;
import racingcar.domain.RaceService;
import racingcar.formatter.ResultFormatter;
import racingcar.view.Input;
import racingcar.view.Output;
import racingcar.view.RaceView;

public class Application {
    public static void main(String[] args) {
        RaceService raceService = new RaceService();
        Input input = new Input();
        Output output = new Output();
        ResultFormatter formatter = new ResultFormatter();

        RaceController controller = new RaceController(input, output, raceService, formatter);
        RaceView view = new RaceView(controller);

        view.start();
    }
}
