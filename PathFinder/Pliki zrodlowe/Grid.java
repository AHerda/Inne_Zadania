import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Grid {
    static public POLA[][] grid;
    static public boolean[][] grid_pom;
    public static int x = 0, y = 0, glebokosc = 0;
    public List<Product> products = new ArrayList<Product>();

    //klasa wyliczeniowa tworzy rodzaje modułów

    enum POLA {
        H, B, S, O, TRASA;
    }

    //konstruktor pobiera i przygotowuje dane
    public Grid(File grid_file) {
        try {
            BufferedReader reader_grid = new BufferedReader(new FileReader(grid_file));

            String linia;
            
            if((linia = reader_grid.readLine()) != null) {
                String x_str = "", y_str = "", n_str = "";
                int i = 0;

                while(linia.charAt(i) != ' ') {
                    x_str += linia.charAt(i);
                    i += 1;
                }
                i += 1;
                while(linia.charAt(i) != ' ') {
                    y_str += linia.charAt(i);
                    i += 1;
                }
                i += 1;
                while(i < linia.length() && linia.charAt(i) != ' ') {
                    n_str += linia.charAt(i);
                    i += 1;
                }

                x = Integer.parseInt(x_str);
                y = Integer.parseInt(y_str);
                glebokosc = Integer.parseInt(n_str);

                grid = new POLA[x][y];
                grid_pom = new boolean[x][y];
            }
            else {
                System.exit(0);
            }

            //tworzenie tablicy z symbolami modułów
            for(int j = 0; j < y; j += 1) {
                linia = reader_grid.readLine();
                for(int i = 0; i < x; i += 1) {
                    switch(linia.charAt(i)) {
                        case 'H': 
                            grid[i][j] = POLA.H;
                            break;
                        case 'B': 
                            grid[i][j] = POLA.B;
                            break;
                        case 'S': 
                            grid[i][j] = POLA.S;
                            break;
                        case 'O': 
                            grid[i][j] = POLA.O;
                            break;
                    }
                }
            }


            while((linia = reader_grid.readLine()) != null) {
                products.add(new Product(linia));
            }

            reader_grid.close();
        }
        catch(IOException | NumberFormatException ioe) {
            System.out.println("Problem IOE or NFE");
            System.exit(0);
        }
        //Wypisz();
    }

    //zwraca symbol modułu
    static POLA Pole(int x, int y) {
        return grid[x][y];
    }

    //zmienia wartosc gridu pomocniczego ktory mowi czy pole zostalo odwiedzone
    static void SetPole(int x, int y) {
        grid_pom[x][y] = true;
    }

    
    //ustawia kazde gridu pomocniczego na nieodwiedzone
    static void ClearGridPom() {
        for(int i = 0; i < x; i += 1) {
            for(int j = 0; j < y; j += 1) {
                grid_pom[i][j] = false;
            }
        }
    }
}