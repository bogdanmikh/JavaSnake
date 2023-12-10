package ru.itis.game.components;

import org.joml.Vector2f;
import org.joml.Vector4f;
import ru.itis.gengine.base.Direction;
import ru.itis.gengine.events.Events;
import ru.itis.gengine.events.Key;
import ru.itis.gengine.gamelogic.Component;
import ru.itis.gengine.gamelogic.Entity;
import ru.itis.gengine.gamelogic.World;
import ru.itis.gengine.gamelogic.components.Mesh;
import ru.itis.gengine.gamelogic.primitives.MeshData;
import ru.itis.gengine.gamelogic.primitives.Primitives;
import ru.itis.gengine.renderer.Shader;
import ru.itis.gengine.renderer.Texture;

import java.util.ArrayList;
import java.util.List;

public class Snake extends Component {
    static float speed = 10.f;

    Events events;
    World world;
    Texture texture;
    Texture textureHead;
    Shader shader;

    List<BodyPart> body = new ArrayList<>();
    public static BodyPart head;
    BodyPart tail;
    Vector4f direction;

    double angle = 0;
    double speedRotation = 10;

    public void setTextureHead(Texture textureHead) {
        this.textureHead = textureHead;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setShader(Shader shader) {
        this.shader = shader;
    }

    @Override
    public void initialize() {
        world = getEntity().getWorld();
        events = getEntity().getEvents();
        direction = Direction.unitUp;
        tail = createHead(new Vector2f(0.f, 0.f));
        head = tail;
        body.add(tail);
    }

    @Override
    public void update(float deltaTime) {
        Vector4f headPos = head.transform.getPosition();
        head.transform.setPosition(headPos.x + direction.x * speed * deltaTime, headPos.y + direction.y * speed * deltaTime, headPos.z);
        for (BodyPart part: body) {
            part.moveTowards(deltaTime);
        }
        if(events.isKeyJustPressed(Key.P)) {
            tail = createPart();
            body.add(tail);
        }
        if (events.isKeyPressed(Key.A)) {
            angle += speedRotation * deltaTime;
        } else if (events.isKeyPressed(Key.D)) {
            angle -= speedRotation * deltaTime;
        }
        direction.x = (float) Math.cos(angle);
        direction.y = (float) Math.sin(angle);
    }

    private BodyPart createPart() {
        MeshData square = Primitives.createSquare(1.f);
        Mesh mesh = new Mesh(square, false, texture, shader);
        Entity bodyPartEntity = world.instantiateEntity();
        bodyPartEntity.getTransform().setPosition(tail.transform.getPosition().x, tail.transform.getPosition().y, 0.f);
        bodyPartEntity.addComponent(mesh);
        getEntity().addChildEntity(bodyPartEntity);
        return new BodyPart(bodyPartEntity, mesh, tail);
    }

    private BodyPart createHead(Vector2f position) {
        MeshData square = Primitives.createSquare(1.5f);
        Mesh mesh = new Mesh(square, false, textureHead, shader);
        Entity bodyPartEntity = world.instantiateEntity();
        bodyPartEntity.getTransform().setPosition(position.x, position.y, 0.f);
        bodyPartEntity.addComponent(mesh);
        getEntity().addChildEntity(bodyPartEntity);
        return new BodyPart(bodyPartEntity, mesh, tail);
    }
}