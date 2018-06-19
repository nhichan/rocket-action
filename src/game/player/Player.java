package game.player;

import action.ActionAdapter;
import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.bullet.Bullet;
import game.effect.ShieldEffect;
import game.effect.Smoke;
import physic.RunHitObject;
import renderer.ImageRenderer;
import renderer.PolygonRenderer;

import java.awt.*;

public class Player extends GameObject {
    public PlayerMove playerMove;
    public PlayerShoot playerShoot;
    private RunHitObject runHitObject;
    private FrameCounter frameCounter = new FrameCounter(10);

    public Player() {
        this.position = new Vector2D();
        this.renderer = new PolygonRenderer(
                Color.RED,
                new Vector2D(),
                new Vector2D(0, 16),
                new Vector2D(20, 8)
        );
        this.playerMove = new PlayerMove();
        this.playerShoot = new PlayerShoot();
//        this.runHitObject = new RunHitObject(
//                ShieldEffect.class
//        );
        ActionAdapter actionAdapter = new ActionAdapter() {
            @Override
            public boolean run(GameObject owner) {
                return false;
            }
        };
    }

    @Override
    public void run() {
        super.run();
        this.playerMove.run(this);
        this.playerShoot.run(this);
        ((PolygonRenderer) this.renderer).angle = this.playerMove.angle;
        this.createSmoke();
    }

    private void createSmoke() {
        if (this.frameCounter.run()) {
            Smoke smoke = GameObjectManager.instance.recycle(Smoke.class);
            smoke.renderer = new ImageRenderer("resources/images/circle.png", 15, 15, Color.CYAN);
            smoke.position.set(position);

            Vector2D rotate = this.playerMove.velocity.add(
                    (new Vector2D(1.5f, 0)).rotate(this.playerMove.angle)
            );

            smoke.velocity.set(rotate);
            this.frameCounter.reset();
        }
    }
}
