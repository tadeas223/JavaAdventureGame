package world;

import tools.FlipImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MapLoader {
    public static Map loadMap(String mapPath, String worldPath) {
        String tilePath = "";
        Tile[][] tiles;
        String[][] tileMap = new String[0][0];
        HashMap<Integer, Tile> basicTileSet = new HashMap<>();
        HashMap<String, Tile> extendedTileSet = new HashMap<>();

        ArrayList<String> lines = new ArrayList<>();

        // loading tiles into a lines ArrayList
//        System.out.println("loading tiles into a lines ArrayList");
        try {
            Scanner sc = new Scanner(new File(mapPath));

            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // loading the path to tiles that this map uses
//        System.out.println("loading the path to tiles that this map uses");
        for (String s : lines) {
            if (s.contains("TILE_PATH")) {
                String[] split1 = s.split(" ");
                String[] split2 = split1[1].split("[+]");

                for (int i = 0; i < split2.length; i++) {
                    if (split2[i].equals("WORLD_PATH")) {
                        tilePath += worldPath;
                    } else {
                        tilePath += split2[i].split("\"")[1];
                    }
                }
                break;
            }
        }

        // loading the map grid into a String[][]
//        System.out.println("loading the map grid into a String[][]");
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains("MAP")) {
                String[] split1 = lines.get(i).split(" ");
                String[] split2 = split1[1].split("x");

                tileMap = new String[Integer.parseInt(split2[0])][Integer.parseInt(split2[1])];

                for (int y = 0; y < tileMap.length; y++) {
                    String[] splitX = lines.get(y + i + 1).split(",");
                    for (int x = 0; x < tileMap[0].length; x++) {
                        tileMap[x][y] = splitX[x];
                    }
                }
            }
        }

        // loading BASIC_TILES into basicTileSet HashMap
//        System.out.println("loading BASIC_TILES into basicTileSet HashMap");
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains("BASIC_TILES")) {
                int j = i + 1;
                while (!lines.get(j).contains("END")) {
                    if (lines.get(j).contains("TILE")) {
                        String[] split1 = lines.get(j).split(" ");
                        Tile tile = new Tile();

                        int tileId = Integer.parseInt(split1[1]);

                        int k = j++;

                        try {
                            tile.setTexture(ImageIO.read(new File(tilePath + "/" + tileId + ".png")));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        while (!lines.get(k).contains("END")) {
                            if (lines.get(k).contains("FLIP")) {
                                String orientation = lines.get(k).split(" ")[1];
                                if (orientation.equals("X")) {
                                    tile.setTexture(FlipImage.flipX(tile.getTexture()));
                                }
                                if (orientation.equals("Y")) {
                                    tile.setTexture(FlipImage.flipY(tile.getTexture()));
                                }
                            }
                            if (lines.get(k).contains("COLLISION")) {
                                int l = k + 1;
                                Rectangle collider = new Rectangle();
                                while (!lines.get(l).contains("END")) {
                                    if (lines.get(l).contains("X")) {
                                        collider.x = Integer.parseInt(lines.get(l).split(" ")[1]);
                                    }
                                    if (lines.get(l).contains("Y")) {
                                        collider.y = Integer.parseInt(lines.get(l).split(" ")[1]);
                                    }
                                    if (lines.get(l).contains("WIDTH")) {
                                        collider.width = Integer.parseInt(lines.get(l).split(" ")[1]);
                                    }
                                    if (lines.get(l).contains("HEIGHT")) {
                                        collider.height = Integer.parseInt(lines.get(l).split(" ")[1]);
                                    }
                                    l++;
                                }
                                tile.setCollider(collider);
                            }
                            k++;
                        }

                        basicTileSet.put(tileId, tile);
                    }

                    j++;
                }
                break;
            }
        }

        // loading EXTENDED_TILES into extendedTileSet HashMap
//        System.out.println("loading EXTENDED_TILES into extendedTileSet HashMap");
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains("EXTENDED_TILES")) {
                int j = i + 1;

                while (!lines.get(j).contains("END")) {

                    if (lines.get(j).contains("TILE")) {
                        String tileName = lines.get(j).split(" ")[1];

                        int k = j++;
                        Tile tile = null;

                        while (!lines.get(k).contains("END")) {
                            if (lines.get(k).contains("BASIC_TILE")) {
                                int basicTileId = Integer.parseInt(lines.get(k).split(" ")[1]);

                                tile = new Tile(basicTileSet.get(basicTileId));
                            }
                            k++;
                        }

                        k = 0;

                        Rectangle collider = null;

                        while (!lines.get(k).contains("END")) {
                            if (lines.get(k).contains("FLIP")) {
                                String orientation = lines.get(k).split(" ")[1];
                                if (orientation.equals("X")) {
                                    tile.setTexture(FlipImage.flipX(tile.getTexture()));
                                }
                                if (orientation.equals("Y")) {
                                    tile.setTexture(FlipImage.flipY(tile.getTexture()));
                                }
                            }
                            if (lines.get(k).contains("COLLISION")) {
                                int l = k + 1;
                                collider = new Rectangle();
                                while (!lines.get(l).contains("END")) {
                                    if (lines.get(l).contains("X")) {
                                        collider.x = Integer.parseInt(lines.get(l).split(" ")[1]);
                                    }
                                    if (lines.get(l).contains("Y")) {
                                        collider.y = Integer.parseInt(lines.get(l).split(" ")[1]);
                                    }
                                    if (lines.get(l).contains("WIDTH")) {
                                        collider.width = Integer.parseInt(lines.get(l).split(" ")[1]);
                                    }
                                    if (lines.get(l).contains("HEIGHT")) {
                                        collider.height = Integer.parseInt(lines.get(l).split(" ")[1]);
                                    }
                                    l++;
                                }
                            }
                            k++;
                        }

                        extendedTileSet.put(tileName, tile);
                    }
                    j++;
                }
            }
        }

        //building the map based on the basic and extended tile sets
//        System.out.println("building the map based on the basic and extended tile sets");
        tiles = new Tile[tileMap.length][tileMap[0].length];

        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[0].length; x++) {
                try {
                    tiles[x][y] = basicTileSet.get(Integer.parseInt(tileMap[x][y]));
                } catch (NumberFormatException e) {
                    tiles[x][y] = extendedTileSet.get(tileMap[x][y]);
                }
            }
        }

        // construction map object and returning it
//        System.out.println("construction map object and returning it");
        return new Map(tiles);
    }
}
