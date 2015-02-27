package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class FourButtons extends Game {
    Stage stage;
    SpriteBatch sb;
    BitmapFont font;
    TextButton tbUp;
    TextButton tbDown;
    TextButton tbLeft;
    TextButton tbRight;
    TextButton.TextButtonStyle tbsUp;
    TextButton.TextButtonStyle tbsDown;
    TextButton.TextButtonStyle tbsLeft;
    TextButton.TextButtonStyle tbsRight;
    Skin skUp;
    Skin skDown;
    Skin skLeft;
    Skin skRight;
    TextureAtlas taBtns;
    Sprite sDude;
    float fDudeX=100, fDudeY, fCharacterHeight=50;
    int nSHeight, nSWidth;
    Image dude;
    Texture tDude;
    TextureRegion rgn;
    @Override
    public void create() {
        nSHeight = Gdx.graphics.getHeight();//Get the stage Height
        nSWidth = Gdx.graphics.getWidth();//Get the stage Width
        stage = new Stage();
        sb = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        skUp = new Skin();
        skDown = new Skin();
        skLeft = new Skin();
        skRight = new Skin();
        taBtns = new TextureAtlas(Gdx.files.internal("dudearrows.pack"));//Importing the .pack into a texture atlas that holds multiple images and can be referenced within a TextButtonStyle
        skUp.addRegions(taBtns);//Applying a texture atlas into a skin
        skDown.addRegions(taBtns);
        skLeft.addRegions(taBtns);
        skRight.addRegions(taBtns);
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("dudearrows.pack"));
        rgn = atlas.findRegion("dude");
        dude = new Image(rgn);
        stage.addActor(dude);
        //Sprite sDude = atlas.createSprite("dude");
        tbsUp = new TextButton.TextButtonStyle();//TextButtonStyle Holds all the images that will be applied to the TextButton
        tbsDown = new TextButton.TextButtonStyle();
        tbsLeft = new TextButton.TextButtonStyle();
        tbsRight = new TextButton.TextButtonStyle();
        tbsUp.font = font;
        tbsDown.font = font;
        tbsLeft.font = font;
        tbsRight.font = font;
        tbsUp.up = skUp.getDrawable("up");//Setting positions and the image to use when the button is in those positions
        tbsUp.down = skUp.getDrawable("uppressed");
        tbsDown.up = skDown.getDrawable("down");
        tbsDown.down = skDown.getDrawable("downpressed");
        tbsLeft.up = skLeft.getDrawable("left");
        tbsLeft.down = skLeft.getDrawable("leftpressed");
        tbsRight.up = skLeft.getDrawable("right");
        tbsRight.down = skLeft.getDrawable("rightpressed");
        tbUp = new TextButton("", tbsUp);//Applying the TextButtonStyle to the TextButton giving it all of its positions and images as well as any text but I didn't use
        tbUp.setSize(nSWidth * 200 / 1794, nSHeight * 200 / 1080);
        tbUp.setPosition(100, (nSHeight / 4) - 50);
        tbUp.addListener(new InputListener() {//http://gamedev.stackexchange.com/questions/60123/registering-inputlistener-in-libgdx
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("UpPress");
                fDudeY+=100;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("UpRelease");
            }
        });
        tbDown = new TextButton("", tbsDown);
        tbDown.setSize(nSWidth * 200 / 1794, nSHeight * 200 / 1080);
        tbDown.setPosition(100, (nSHeight / 4) - 250);
        tbDown.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("DownPress");
                fDudeY-=100;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("DownRelease");
            }
        });
        tbLeft = new TextButton("", tbsLeft);
        tbLeft.setSize(nSWidth * 200 / 1794, nSHeight * 200 / 1080);
        tbLeft.setPosition(-25, (nSHeight / 4) - 150);
        tbLeft.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("LeftPress");
                fDudeX--;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("LeftRelease");
            }
        });
        tbRight = new TextButton("", tbsRight);
        tbRight.setSize(nSWidth * 200 / 1794, nSHeight * 200 / 1080);
        tbRight.setPosition(225, (nSHeight / 4) - 150);
        tbRight.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("RightPress");
               // dude.setX(fDudeX+100);
                fDudeX+=100;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("RightRelease");
            }
        });
        stage.addActor(tbUp);
        stage.addActor(tbDown);
        stage.addActor(tbLeft);
        stage.addActor(tbRight);
        dude.setX(fDudeX);
        dude.setY(fDudeY);
    }
    public void dispose(){
        sb.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        dude.setPosition(fDudeX, fDudeY + fCharacterHeight / 2);
        stage.draw();
        dude.setPosition(fDudeX, fDudeY + fCharacterHeight / 2);
        super.render();
        dude.setPosition(fDudeX, fDudeY + fCharacterHeight / 2);
    }
}

