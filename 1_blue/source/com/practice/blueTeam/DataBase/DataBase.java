package com.practice.blueTeam.DataBase;

import com.practice.blueTeam.UI.Tile;
import javax.swing.*;

// Используется синглтон, чтобы на протижении всего процесса везде использовалась одна база данных
public class DataBase {
    private static DataBase ourInstance = new DataBase();
    public static DataBase getInstance() {
        return ourInstance;
    }
    // количество уровней
    private static int numberOfLevels = 3;
    public static int getNumberOfLevels() {
        return numberOfLevels;
    }
    public static void setNumberOfLevels(int numberOfLevels) {
        DataBase.numberOfLevels = numberOfLevels;
    }
    // текущий уровень
    private static int currentLevel;
    public static int getCurrentLevel() {
        return currentLevel;
    }
    public static void setCurrentLevel(int currentLevel) {
        DataBase.currentLevel = currentLevel;
    }
    // Иконки уровней
    private static ImageIcon[] levelIcons = new ImageIcon[numberOfLevels];
    public static ImageIcon[] getLevelIcons() {
        return levelIcons;
    }
    public static void setLevelIcons(ImageIcon[] levelIcons) {
        DataBase.levelIcons = levelIcons;
    }

    // Рисунки Тайлов
    private static ImageIcon[][] tilesIcons = new ImageIcon[numberOfLevels][15];
    public static ImageIcon[][] getTiles() {
        return tilesIcons;
    }
    public static ImageIcon[] getLevelTiles(int levelNumber) {
        return tilesIcons[levelNumber];
    }

    // порядок Тайлов
    private static Tile[][] tilesOrderOnTheScreen = new Tile[numberOfLevels][15];
    public static Tile[] getTilesOrderOnTheScreen() {
        return tilesOrderOnTheScreen[currentLevel];
    }
    public static void setTilesOrderOnTheScreen(int[] tilesOrderOnTheScreen) {
        for (int i = 0; i < 15; i++) {
            DataBase.tilesOrderOnTheScreen[currentLevel][i] = new Tile(tilesOrderOnTheScreen[i]);
            DataBase.tilesOrderOnTheScreen[currentLevel][i].setImageIcon(DataBase.tilesIcons[currentLevel][i]);
        }
    }
    private DataBase() {
        for (int i = 0; i < numberOfLevels; i++) {
            levelIcons[i] = new ImageIcon("com/practice/blueTeam/resources/Level" + (i+1) + "/image");
            for (int j = 0; j < 15; j++) {
                tilesIcons[i][j] = new ImageIcon("com/practice/blueTeam/resources/Level"+i+"/image"+j);
            }
        }
    }
}
