package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.lang.reflect.InvocationTargetException;

// Główna klasa gry. Rozszerza ApplicationAdapter z biblioteki LibGDX, dzięki czemu implementuje metody
// potrzebne do działania gry
public class QuizGame extends ApplicationAdapter {
	// Karty graficzne słabo radzą sobie z dużą ilością zapytań o rysowanie czegoś na ekranie.
	// Żeby nie wysyłać nowego zapytania do GPU za każdym razem,
	// gdy chcemy coś narysować (postać w grze, drzewo, jakąś teksturę czy cokolwiek -> czyli suma sumarum w dużych grach
	// byłoby to bardzo wiele zapytań)
	// wymyślono coś takiego jak SpriteBatch, który łączy wiele "rysowań" w jedno i dopiero później wysyła zbiorowe
	// zapytanie o narysowanie wszystkiego na raz.
	// Rysowanie wszystkich elementów w grze odbywa się pomiędzy batch.begin(), a batch.end() (patrz, funkcja render())
	SpriteBatch batch;

	// Scene Manager zarządza 'scenami' w grze. Każda scena to osobna przestrzeń dla obiektów w aplikacji.
	// Osobnymi scenami mogą być np. menu główne gry i sam ekran gry
	SceneManager sceneManager;

	NetworkManager networkManager;

	// Funkcja wywołuje się przy rozpoczęciu gry
	// Tutaj powinno się dokonać całego setupu gry, stworzenia wszystkich
	// potrzebnych zmiennych itp.
	@Override
	public void create () {
		// Stwórz network manager
		NetworkManager.instantiate();
		// Stwórz SpriteBatch
		batch = new SpriteBatch();
		// Stwórz scene manager
		sceneManager = new SceneManager();
		// Załaduj menu główne
		sceneManager.loadScene(MenuScene.class);
	}

	// Funkcja wywoływana wiele razy na sekundę (np. 60 razy gdy mamy 60 fps).
	// Tutaj przetwarza się logikę gry (sprawdzanie kolizji, sprawdzanie czy użytkownik coś kliknął,
	// liczenie fizyki, ruchu postaci itp. itd.) oraz rysuje się wszystkie grafiki z gry.
	@Override
	public void render () {
		float deltaTime = Gdx.graphics.getDeltaTime();

		// Ustaw kolor tła gry
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Aktualizuj menedżer scen
		sceneManager.update(deltaTime);

		//batch.begin(); // Rozpocznij "zbieranie" rzeczy do rysowania przez SpriteBatch
		sceneManager.draw(batch, deltaTime);
		//batch.end(); // Zakończ "zbieranie" i wyślij zapytanie o narysowanie do GPU
	}

	// Funkcja wywoływana, gdy wychodzi się z gry.
	// Oczyszcza ona pamięć poprzez usunięcie wszystkich obiektów, których z jakichś powodów
	// nie może usunąć Garbage Collector.
	@Override
	public void dispose () {
		batch.dispose();
	}
}
