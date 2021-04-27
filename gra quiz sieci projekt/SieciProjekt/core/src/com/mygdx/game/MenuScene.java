package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuScene extends BaseScene {
    String textToDraw = "";
    Color textColor = Color.WHITE;
    float textTimer = -1;
    boolean startTimer = false;
    boolean connecting = false;

    public MenuScene() {
        super();

        Table menuTable = new Table();
        TextButton playButton = new TextButton("Start", skin, "small");
        TextButton helpButton = new TextButton("How to play", skin, "small");
        TextButton exitButton = new TextButton("Exit", skin, "small");

        menuTable.add(playButton).spaceBottom(10);
        menuTable.row();
        menuTable.add(helpButton).spaceBottom(10);
        menuTable.row();
        menuTable.add(exitButton);

        menuTable.setFillParent(true);

        menuTable.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                return true;
                //return super.touchDown(event, x, y, pointer, button);
            }
        });

        playButton.addListener(new InputListener(){
            // W listenerze nadpisz metodę 'touchDown', która jest wywoływana przy naciśnięciu przycisku
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int btn) {
                if (connecting) return true;
                connecting = true;

                NetworkManager.instantiate();
                NetworkManager.instance.statusChanged.addListener((status) -> {
                    switch (status) {
                        case "CONNECTING":
                            textToDraw = "Łączenie z serwerem...";
                            textColor = Color.WHITE;
                            break;
                        case "FAILED":
                            textColor = Color.RED;
                            textToDraw = "Error: Could not connect to the server.";
                            textTimer = 5;
                            startTimer = true;
                            connecting = false;
                            break;
                        case "CONNECTED":
                            SceneManager.instance.loadScene(GameScene.class);
                            break;
                    }
                });
                NetworkManager.instance.start();
                return true;
            }
        });

        exitButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int btn) {
                Gdx.app.exit();
                return true;
            }
        });

        stage.addActor(menuTable);
    }

    @Override
    public void update(float deltaTime) {
        stage.act();

        if (startTimer) {
            textTimer -= deltaTime;

            if (textTimer <= 0) {
                startTimer = false;

                textToDraw = "";
            }
        }
    }

    @Override
    public void initialize() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void draw(SpriteBatch batch, float deltaTime) {
        batch.begin();

        batch.draw(background, 320 - background.getWidth()/2, 0);
        font.setColor(textColor);
        font.draw(batch, textToDraw, 100, 114, 440, 1, true);

        batch.end();
        stage.draw();
    }
}
