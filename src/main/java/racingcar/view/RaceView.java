package racingcar.view;

import racingcar.controller.RaceController;

public class RaceView {
    private final RaceController controller;

    public RaceView(RaceController controller) {
        this.controller = controller;
    }

    public void start() {
        controller.startRace();
    }
}