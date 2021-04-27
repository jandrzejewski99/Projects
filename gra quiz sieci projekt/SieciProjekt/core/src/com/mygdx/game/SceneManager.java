package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;

// Scene Manager zarządza 'scenami' w grze. Każda scena to osobna przestrzeń dla obiektów w aplikacji.
// Osobnymi scenami mogą być np. menu główne gry i sam ekran gry
public class SceneManager {
    // SceneManager korzysta ze wzorca Singleton -> jest tylko jeden na całą aplikację
    // i można z niego korzystać z każdego miejsca w aplikacji
    public static SceneManager instance;

    // HashMap zawierający wszystkie dostępne sceny
    private HashMap<Class<?>, BaseScene> scenes = new HashMap<>();

    // Obecnie aktywna scena
    private BaseScene activeScene;

    public SceneManager() {
        instance = this;
        // Stwórz i dodaj sceny do menedżera scen
        scenes.put(MenuScene.class, new MenuScene());
        scenes.put(GameScene.class, new GameScene());
    }

    // Załaduj scenę o danym typie
    public void loadScene(Class<?> type) {
        if (!scenes.containsKey(type)) {
            System.out.println("Could not load scene of type: " + type);
        }
        activeScene = scenes.get(type);
        activeScene.initialize();
    }

    public void update(float deltaTime) {
        // Aktualizuj obecnie załadowaną scenę
        activeScene.update(deltaTime);
    }

    public void draw(SpriteBatch batch, float deltaTime) {
        // Rysuj obecnie załadowaną scenę
        activeScene.draw(batch, deltaTime);
    }
}
