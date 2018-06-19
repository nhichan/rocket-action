package game.enemy;

import action.ActionAdapter;
import action.LimitAction;
import action.SequenceAction;
import action.WaitAction;
import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;

import java.util.Random;

public class EnemySpawner extends GameObject {

    private Random random;

    public EnemySpawner() {
        this.random = new Random();
        this.createAction();
    }

    private void createAction() {
        this.addAction(
                new LimitAction(
                        new SequenceAction(
                                new WaitAction(5 * 58), // frames per sec
                                new ActionAdapter() {
                                    @Override
                                    public boolean run(GameObject owner) {
                                        Enemy enemy = GameObjectManager.instance.recycle(Enemy.class);
                                        enemy.position.set(random.nextInt(1024), random.nextInt(600));
                                        return true;
                                    }
                                }
                        ),
                        70
                )
        );

        this.addAction(
                new SequenceAction(
                        new WaitAction(20 * 58), // frames per sec
                        new LimitAction(
                                new SequenceAction(
                                    new ActionAdapter() {
                                        @Override
                                        public boolean run(GameObject owner) {
                                            return !GameObjectManager.instance.isShootingEnemyExist();
                                        }
                                    },
                                    new ActionAdapter() {
                                        @Override
                                        public boolean run(GameObject owner) {
                                            Enemy enemy = GameObjectManager.instance.recycle(Enemy.class);
                                            enemy.position.set(random.nextInt(1024), random.nextInt(600));
                                            enemy.isEnemyShoot = true;
                                            return true;
                                        }
                                    }
                                )
                        ,
                        5
                        )
                )
        );
    }

    @Override
    public void run() {
        super.run();
    }
}
