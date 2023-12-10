package ru.itis.game.components;

import org.joml.Vector4f;
import ru.itis.gengine.base.Direction;
import ru.itis.gengine.events.Events;
import ru.itis.gengine.events.Key;
import ru.itis.gengine.gamelogic.Component;
import ru.itis.gengine.gamelogic.components.Transform;

public class CameraMove extends Component {
    public float mouseSpeed = 0.005f;
    public float moveSpeed = 8.0f;

    private Transform transform;
    private Events events;

    @Override
    public void initialize() {
        transform = getEntity().getTransform();
        events = getEntity().getEvents();
    }

    @Override
    public void update(float deltaTime) {
        float speed = moveSpeed * deltaTime;
        Vector4f head = Snake.head.transform.getPosition();
        transform.setPosition(head.x, head.y, transform.getPosition().z);
    }
}