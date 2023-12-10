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
import ru.itis.gengine.gamelogic.Component;

import java.util.List;

public class SpawnerFood extends Component {
    Events events;
    World world;
    Texture texture;
    Shader shader;

    int valueOfFood = 15;
    List<Food> food;

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
    }

    Vector2f getRandom() {
        double x = Math.random();
        float y;
        return new Vector2f(0, 0);
    }

    @Override
    public void update(float deltaTime) {
        if (food.size() < valueOfFood) {
            for (int i = food.size(); i < valueOfFood; i++) {

                food.add(createPart(getRandom()));
            }
        }
    }

    private Food createPart(Vector2f position) {
        MeshData square = Primitives.createSquare(1.f);
        Mesh mesh = new Mesh(square, false, texture, shader);
        Entity foodEntity = world.instantiateEntity();
        foodEntity.getTransform().setPosition(position.x, position.y, 0.f);
        foodEntity.addComponent(mesh);
        getEntity().addChildEntity(foodEntity);
        return new Food(foodEntity, mesh);
    }
}
