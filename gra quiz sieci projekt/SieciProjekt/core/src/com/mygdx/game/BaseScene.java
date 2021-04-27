package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public abstract class BaseScene {
    Skin skin;
    TextureAtlas buttonAtlas;
    Stage stage;
    BitmapFont font;
    Texture background;

    protected BaseScene() {
        font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"), Gdx.files.internal("fonts/font.png"), false);
        stage = new Stage();
        background = new Texture(Gdx.files.internal("background.png"));
        buttonAtlas = new TextureAtlas(Gdx.files.local("glassy-ui.atlas"));
        skin = new Skin(Gdx.files.local("glassy-ui.json"), buttonAtlas);
    }

    public abstract void initialize();
    public abstract void draw(SpriteBatch batch, float deltaTime);
    public abstract void update(float deltaTime);
}
