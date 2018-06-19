package action;

import base.GameObject;

import java.util.Arrays;
import java.util.List;

public class GroupAction implements Action {


    private List<Action> actions;
//    private int count = 0;

    public GroupAction(Action... actions) {
        this.actions = Arrays.asList(actions);
    }

    @Override
    public boolean run(GameObject owner) {
//        this.actions.forEach(action -> {
//            if (action.run(owner)) {
//                count += 1;
//            }
//        });
//        return this.count == this.actions.size();
        this.actions.removeIf(action -> action.run(owner));
        return this.actions.isEmpty();
    }

    @Override
    public void reset() {

    }
}
