package ru.itis.game.components;

import org.joml.Vector2f;
import org.joml.Vector4f;
import ru.itis.gengine.gamelogic.Entity;
import ru.itis.gengine.gamelogic.components.Mesh;
import ru.itis.gengine.gamelogic.components.Transform;

public class BodyPart {
    public static float minDistance = 0.4f;
    public Entity entity;
    public Transform transform;
    public Mesh mesh;
    public BodyPart front;

    public BodyPart(Entity entity, Mesh mesh, BodyPart front) {
        this.entity = entity;
        this.transform = entity.getTransform();
        this.mesh = mesh;
        this.front = front;
    }

    public void moveTowards(float deltaTime) {
        if(front == null) { return; }
        float dx = front.transform.getPosition().x - transform.getPosition().x;
        float dy = front.transform.getPosition().y - transform.getPosition().y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        if (distance > minDistance) {
            float vx = (dx / distance) * Snake.speed * deltaTime;
            float vy = (dy / distance) * Snake.speed * deltaTime;
            transform.setPosition(transform.getPosition().x + vx, transform.getPosition().y + vy, 0.f);
        }
    }
}
