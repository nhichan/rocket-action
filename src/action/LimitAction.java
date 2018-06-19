package action;

import base.GameObject;

public class LimitAction implements Action {

    private Action action;
    private int count;

    public LimitAction(Action action, int count) {
        this.action = action;
        this.count = count;
    }


    @Override
    public boolean run(GameObject owner) {
        if (this.action.run(owner)) {
            this.action.reset();
            this.count -= 1;
        }
        return this.count == 0;
    }

    @Override
    public void reset() {

    }
}
