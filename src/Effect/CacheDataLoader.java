package Effect;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class CacheDataLoader {

    private static CacheDataLoader instance;

    private String physicMapFile = "data/phys_map.txt";

    public static int[][] physMap;

    public CacheDataLoader() {

    }

    public static CacheDataLoader getInstance() {
        if (instance == null) {
            instance = new CacheDataLoader();
        }
        return instance;
    }

    public int[][] getPhysicalMap() {
        return instance.physMap;
    }

    public void LoadData() throws IOException {
        loadPhysicalMap();
    }

    public void loadPhysicalMap() throws IOException {
        FileReader fr = new FileReader(physicMapFile); // tra ve 1 luong du lieu
        BufferedReader br = new BufferedReader(fr); // theo luong du lieu de doc

        String line = null;

        line = br.readLine();
        int numberOfRows = Integer.parseInt(line);
        line = br.readLine();
        int numberOfColumns = Integer.parseInt(line);

        instance.physMap = new int[numberOfRows][numberOfColumns];
        System.out.println(physMap.length);
        for (int i = 0; i < numberOfRows; i++) {
            line = br.readLine();
            String[] str = line.split(" ");
            for (int j = 0; j < numberOfColumns; j++) {
                instance.physMap[i][j] = Integer.parseInt(str[j]);
            }
        }
//        in map ra console
//        for (int i = 0; i < numberOfRows; i++) {
//
//            for (int j = 0; j < numberOfColumns; j++) {
//                System.out.print(" " + instance.physMap[i][j]);
//            }
//
//            System.out.println();
//        }
        br.close();
    }

}
