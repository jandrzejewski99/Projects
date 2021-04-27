package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class GameScene extends BaseScene {
    QuestionsManager questionsManager;

    TextButton answerAButton;
    TextButton answerBButton;
    TextButton answerCButton;
    TextButton answerDButton;
    TextButton startGameButton;
    Table menuTable;
    String textToDraw;
    NetworkThread networkThread;

    public GameScene() {
        super();

        questionsManager = new QuestionsManager();

        menuTable = new Table();
        menuTable.setPosition(320, 200);
        answerAButton = new TextButton("Odpowiedz A", skin, "small");
        answerBButton = new TextButton("Odpowiedz B", skin, "small");
        answerCButton = new TextButton("Odpowiedz C", skin, "small");
        answerDButton = new TextButton("Odpowiedz D", skin, "small");

        menuTable.add(answerAButton).spaceBottom(10).spaceRight(10).width(300);
        menuTable.add(answerBButton).spaceBottom(10).spaceLeft(10).width(300);
        menuTable.row();
        menuTable.add(answerCButton).spaceBottom(10).spaceRight(10).width(300);
        menuTable.add(answerDButton).spaceBottom(10).spaceLeft(10).width(300);
        menuTable.row();
        stage.addActor(menuTable);

        answerAButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                NetworkManager.instance.sendText("a");
                return true;
            }
        });
        answerBButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                NetworkManager.instance.sendText("b");
                return true;
            }
        });
        answerCButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                NetworkManager.instance.sendText("c");
                return true;
            }
        });
        answerDButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                NetworkManager.instance.sendText("d");
                return true;
            }
        });

        startGameButton = new TextButton("Zacznij gre", skin, "small");
        menuTable.row();
        menuTable.add(startGameButton);

        startGameButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                NetworkManager.instance.sendText("START_GAME_HOST");
                return true;
            }
        });

        startGameButton.setVisible(false);
        answerAButton.setVisible(false);
        answerBButton.setVisible(false);
        answerCButton.setVisible(false);
        answerDButton.setVisible(false);
    }

    @Override
    public void initialize() {
        Gdx.input.setInputProcessor(stage);
        networkThread = new NetworkThread();
        networkThread.start();

        if (NetworkManager.instance.isHost) {
            startGameButton.setVisible(true);
        }
    }

    @Override
    public void update(float deltaTime) {
        stage.act();

        if (networkThread != null) {
            switch (networkThread.gameStatus) {
                case "NEW_HOST":
                    startGameButton.setVisible(true);
                    NetworkManager.instance.isHost = true;
                    networkThread.gameStatus = "LOBBY";
                    break;
                case "LOBBY":
                    if (NetworkManager.instance.isHost) {
                        textToDraw = "Kliknij 'Zacznij gre' aby rozpocząć.";
                    } else {
                        textToDraw = "Poczekaj aż host rozpocznie grę.";
                    }
                    break;
                case "STARTED":
                    startGameButton.setVisible(false);
                    answerAButton.setVisible(true);
                    answerBButton.setVisible(true);
                    answerCButton.setVisible(true);
                    answerDButton.setVisible(true);
                    break;
                case "START_FAILED":
                    textToDraw = "Niewystarczająca liczba graczy.";
                    break;
                case "READING_QUESTION":
                    String question = networkThread.question;
                    String answerA = networkThread.answerA;
                    String answerB = networkThread.answerB;
                    String answerC = networkThread.answerC;
                    String answerD = networkThread.answerD;

                    textToDraw = question;
                    answerAButton.setText(answerA);
                    answerBButton.setText(answerB);
                    answerCButton.setText(answerC);
                    answerDButton.setText(answerD);

                    answerAButton.setDisabled(false);
                    answerBButton.setDisabled(false);
                    answerCButton.setDisabled(false);
                    answerDButton.setDisabled(false);
                    break;
                case "PAUSE":
                    answerAButton.setDisabled(true);
                    answerBButton.setDisabled(true);
                    answerCButton.setDisabled(true);
                    answerDButton.setDisabled(true);

                    textToDraw = networkThread.getPauseText();
                    break;
                case "FINISHED":
                    textToDraw = "Koniec gry. Wygrał gracz " + networkThread.winner + " z wynikiem " + networkThread.winnerScore + ".\nTwój wynik to: " + networkThread.score;
                    break;
            }
        }
    }

    @Override
    public void draw(SpriteBatch batch, float deltaTime) {
        batch.begin();
        batch.draw(background, 320 - background.getWidth()/2, 0);
        font.draw(batch, textToDraw, 120, 360, 400, 1, true);
        batch.end();
        stage.draw();
    }
}
