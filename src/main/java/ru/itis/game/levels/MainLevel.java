package ru.itis.game.levels;

import ru.itis.game.components.CameraMove;
import ru.itis.game.components.Snake;
import ru.itis.gengine.gamelogic.Entity;
import ru.itis.gengine.gamelogic.LevelBase;
import ru.itis.gengine.gamelogic.World;
import ru.itis.gengine.gamelogic.components.Camera;
import ru.itis.gengine.renderer.Shader;
import ru.itis.gengine.renderer.Texture;

public class MainLevel extends LevelBase {
    Shader baseShader;

    @Override
    public void start(World world) {
        world.getRenderer().setClearColor(0.07f, 0.13f, 0.17f, 1.0f);
        baseShader = new Shader(
                "resources/shaders/vertex_shader.glsl",
                "resources/shaders/fragment_shader.glsl"
        );
        Texture texture = new Texture("resources/textures/BlackCircle.png");
        Texture textureHead = new Texture("resources/textures/Stethem.png");

        Entity cameraEntity = world.instantiateEntity();
        Camera camera = new Camera();
        camera.setFieldOfView(60.f);
        cameraEntity.addComponent(camera);
        camera.setShader(baseShader);
        cameraEntity.addComponent(new CameraMove());
        cameraEntity.getTransform().setPosition(0.f, 0.f, 20.f);


        Entity snakeEntity = world.instantiateEntity();
        Snake snake = new Snake();
        snake.setShader(baseShader);
        snake.setTexture(texture);
        snake.setTextureHead(textureHead);
        snakeEntity.addComponent(snake);
    }

    @Override
    public void terminate() {
        baseShader.delete();
    }
}

