import ru.itis.game.levels.MainLevel;
import ru.itis.gengine.application.Application;
import ru.itis.gengine.application.ApplicationStartupSettings;
import ru.itis.gengine.base.GSize;

public class Main {
    public static void main(String[] args) {
        Application.shared.run(
                ApplicationStartupSettings.builder()
                        .name("GLGame")
                        .windowTitle("Snake")
                        .startupLevel(new MainLevel())
                        .windowSize(new GSize(800, 600))
                        .isFullScreen(false)
                        .build()
        );
    }
}