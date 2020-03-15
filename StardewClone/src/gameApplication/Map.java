package gameApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {

    private String mapLayoutSourceDir = ".\\map_layouts\\";

    private Drawable[][] mapTiles;
    private Drawable[][] mapSprites;
    private ArrayList<Integer> idsOfSpritesToLoad = new ArrayList<>();

    public static int xMax;
    public static int yMax;

    public Map() {
        createNewMap("map1.txt", 1280, 1280);
    }

    private void createNewMap(String name, int mapWidth, int mapHeight) {
        fillMapTiles(name);
    }

    private void createNewMapFile(String name, int mapWidth, int mapHeight) {
        try {
            PrintWriter writer = new PrintWriter(mapLayoutSourceDir + name, "UTF-8");
            writer.println(mapWidth + " " + mapHeight);
            for (int i = 0; i < mapHeight / TileEntity.TILE_SCALE; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < mapWidth / TileEntity.TILE_SCALE; j++) {
                    line.append(TileEntityGrass.id + " ");
                }
                writer.println(line);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillMapTiles(String name) {
        String filePath = mapLayoutSourceDir + name;
        int xCounter = 0;
        int yCounter = 0;

        try {
            Scanner myReader = new Scanner(new File(filePath));

            xMax = Integer.parseInt(myReader.next());
            yMax = Integer.parseInt(myReader.next());

            mapTiles = new Drawable[yMax / TileEntity.TILE_SCALE][xMax / TileEntity.TILE_SCALE];

            while (myReader.hasNext()) {
                String data = myReader.next();
                putInIdOfSpriteToFill(Integer.parseInt(data));
                switch (data) {
                    case "0":
                        mapTiles[yCounter][xCounter] = new TileEntityGrass(xCounter * TileEntity.TILE_SCALE,
                                yCounter * TileEntity.TILE_SCALE);
                        break;
                    case "1":
                        mapTiles[yCounter][xCounter] = new TileEntityWall(xCounter * TileEntity.TILE_SCALE,
                                yCounter * TileEntity.TILE_SCALE);
                        break;
                    default:
                        mapTiles[yCounter][xCounter] = new TileEntity(xCounter * TileEntity.TILE_SCALE,
                                yCounter * TileEntity.TILE_SCALE, true);
                }
                xCounter++;
                if (xMax <= xCounter * TileEntity.TILE_SCALE) {
                    yCounter++;
                    xCounter = 0;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void putInIdOfSpriteToFill(int id){
        for (Integer integer : idsOfSpritesToLoad) {
            if(integer == id) return;
        }
        idsOfSpritesToLoad.add(id);
    }

    public Drawable[][] getMapTiles() {
        return mapTiles;
    }

    public Drawable getMapTile(int indexX, int indexY) {
        int x = indexX;
        int y = indexY;
        if (x >= mapTiles.length) x = mapTiles.length - 1;
        if (y >= mapTiles[0].length) y = mapTiles[0].length - 1;
        return mapTiles[y][x];
    }

    public int getxMax() {
        return xMax;
    }

    public int getyMax() {
        return yMax;
    }

    public ArrayList<Integer> getIdsOfSpritesToLoad() {
        return idsOfSpritesToLoad;
    }
}
