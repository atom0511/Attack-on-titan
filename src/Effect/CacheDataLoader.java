package Effect;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CacheDataLoader {

    private static CacheDataLoader instance = null;

    private String physmapfile = "data/phys_map.txt";
    
    private int[][] phys_map;

    private CacheDataLoader() {}

    public static CacheDataLoader getInstance() {
        if (instance == null) {
            instance = new CacheDataLoader();
        }
        return instance;
    }

    public int[][] getPhysicalMap() {
        return instance.phys_map;
    }

    public void LoadData() throws IOException {

        LoadPhysMap();

    }

    public void LoadPhysMap() throws IOException {

        FileReader fr = new FileReader(physmapfile);
        BufferedReader br = new BufferedReader(fr);

        String line = null;

        line = br.readLine();
        int numberOfRows = Integer.parseInt(line);
        line = br.readLine();
        int numberOfColumns = Integer.parseInt(line);

        instance.phys_map = new int[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {
            line = br.readLine();
            String[] str = line.split(" ");
            for (int j = 0; j < numberOfColumns; j++) {
                instance.phys_map[i][j] = Integer.parseInt(str[j]);
            }
        }
        System.out.println(phys_map.length);
        System.out.println(phys_map[0].length);
        for (int i = 0; i < numberOfRows; i++) {

            for (int j = 0; j < numberOfColumns; j++) {
                System.out.print(" " + instance.phys_map[i][j]);
            }

            System.out.println();
        }

        br.close();

    }

}
