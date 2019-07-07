package com.practice.blueTeam.DataBase;

import com.practice.blueTeam.UI.LevelWindow;
import com.practice.blueTeam.UI.SecretLevel;
import com.practice.blueTeam.UI.Tile;
//import com.sun.tools.javac.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

// Используется синглтон, чтобы на протижении всего процесса везде использовалась одна база данных
public class DataBase {
    private static DataBase ourInstance = new DataBase();
    public static DataBase getInstance() {
        return ourInstance;
    }
    // количество уровней
    private static int numberOfLevels;
    public static int getNumberOfLevels() {
        return numberOfLevels;
    }
    public static void setNumberOfLevels(int numberOfLevels) {
        DataBase.numberOfLevels = numberOfLevels;
    }
    // Текущий уровень
    private static int currentLevel;
    public static int getCurrentLevel() {
        return currentLevel;
    }
    public static void setCurrentLevel(int currentLevel) {
        DataBase.currentLevel = currentLevel;
    }
    // Иконки уровней
    private static ImageIcon[] levelIcons;
    public static ImageIcon[] getLevelIcons() {
        return levelIcons;
    }
    public static void setLevelIcons(ImageIcon[] levelIcons) {
        DataBase.levelIcons = levelIcons;
    }
    // Рисунки Тайлов
    private static ImageIcon[][] tilesIcons;
    public static ImageIcon[][] getTiles() {
        return tilesIcons;
    }
    public static ImageIcon[] getLevelTiles(int levelNumber) {
        return tilesIcons[levelNumber];
    }
    // порядок Тайлов
    private static Tile[][] tilesOrderOnTheScreen = new Tile[numberOfLevels][16];
    public static Tile[] getTilesOrderOnTheScreen() {
        return tilesOrderOnTheScreen[currentLevel];
    }
    public static void setTilesOrderOnTheScreen(int[] tilesOrderOnTheScreen) {
        for (int i = 0; i < 16; i++) {
            DataBase.tilesOrderOnTheScreen[currentLevel][i] = new Tile(tilesOrderOnTheScreen[i]);
            DataBase.tilesOrderOnTheScreen[currentLevel][i].setImageIcon(DataBase.tilesIcons[currentLevel][i]);
        }
    }
    // Уровни
    private static LevelWindow[] levelWindows;
    public static LevelWindow getLevelWindows(int i) {
        return levelWindows[i];
    }
    // Секретный уровень
    private static SecretLevel secretLevel;
    public static SecretLevel getSecretLevel() {
        return secretLevel;
    }
    // Рисунки тайлов секретного уровня
    private static ImageIcon[] secretTilesIcons;
    public static ImageIcon[] getsSecretTiles() {
        return secretTilesIcons;
    }

    // Рисунок секретного уровня
    private static  ImageIcon secretLevelImage;
    public static ImageIcon getSecretLevelImage() {
        return secretLevelImage;
    }
    // конструктор
    private DataBase() {
        numberOfLevels = 3;
        //
        // waiting for functional part to get tiles
        //
        levelIcons = new ImageIcon[numberOfLevels];
        tilesIcons = new ImageIcon[numberOfLevels][16];
        levelWindows = new LevelWindow[numberOfLevels];

        secretTilesIcons = new ImageIcon[16];
        for (int i = 0; i < numberOfLevels+1; i++){
            if (i == numberOfLevels) {

                String path = new String("/SecretLevel/Image.jpeg");
                URL url = this.getClass().getResource(path);
                secretLevelImage = new ImageIcon(url);
                for (int j = 0; j < 16; j++) {
                    String path2 = new String( "/SecretLevel/images/Image_" + (j+1) +".jpg");
                    url = this.getClass().getResource(path2);
                    secretTilesIcons[j] = new ImageIcon(url);
                }
                secretLevel = new SecretLevel(numberOfLevels+1);
                break;
            }
            String path = new String("/Level"+ (i+1)+ "/Image.jpeg");
            URL url = this.getClass().getResource(path);
            levelIcons[i] = new ImageIcon(url);
            for (int j = 0; j < 16; j++) {
                String path2 = new String( "/Level" + (i+1) + "/images/Image_" + (j+1) +".jpg");
                url = this.getClass().getResource(path2);
                tilesIcons[i][j] = new ImageIcon(url);
            }
        }
        for (int i = 0; i < numberOfLevels; i++) {
            levelWindows[i] = new LevelWindow(i);
        }
    }
}
