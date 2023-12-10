package ru.itis.game.components;

import org.joml.Vector2f;
import org.joml.Vector4f;
import ru.itis.gengine.gamelogic.Entity;
import ru.itis.gengine.gamelogic.components.Mesh;
import ru.itis.gengine.gamelogic.components.Transform;

public class Food {
    public Entity entity;
    public Transform transform;
    public Mesh mesh;

    public Food(Entity entity, Mesh mesh) {
        this.entity = entity;
        this.transform = entity.getTransform();
        this.mesh = mesh;
    }

    public boolean isSeen() {
        float dx = Snake.head.transform.getPosition().x - transform.getPosition().x;
        float dy = Snake.head.transform.getPosition().y - transform.getPosition().y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < 15.f;
    }
}
