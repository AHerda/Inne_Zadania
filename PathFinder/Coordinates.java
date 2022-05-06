import java.util.ArrayList;
import java.util.List;

public class Coordinates implements Comparable<Coordinates>{
    public int x, y;
    public int czas;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //zwraca liste sasiednich modułów
    public List<Coordinates> SasiedniePola (Path path) {
        List<Coordinates> pola = new ArrayList<Coordinates>();
        if(x != 0 && Grid.Pole(x-1, y) != Grid.POLA.O && !Grid.grid_pom[x-1][y]) {
            pola.add(new Coordinates(x-1, y));
        }
        if(x+1 != Grid.x && Grid.Pole(x+1, y) != Grid.POLA.O && !Grid.grid_pom[x+1][y]) {
            pola.add(new Coordinates(x+1, y));
        }
        if(y != 0 && Grid.Pole(x, y-1) != Grid.POLA.O && !Grid.grid_pom[x][y-1]) {
            pola.add(new Coordinates(x, y-1));
        }
        if(y+1 != Grid.y && Grid.Pole(x, y+1) != Grid.POLA.O && !Grid.grid_pom[x][y+1]) {
            pola.add(new Coordinates(x, y+1));
        }
        return pola;
    }

    public List<Coordinates> SasiedniePola2 (Path path) {
        List<Coordinates> pola = new ArrayList<Coordinates>();
        if(y != 0 && Grid.Pole(x, y-1) != Grid.POLA.O && !Grid.grid_pom[x][y-1]) {
            pola.add(new Coordinates(x, y-1));
        }
        if(y+1 != Grid.y && Grid.Pole(x, y+1) != Grid.POLA.O && !Grid.grid_pom[x][y+1]) {
            pola.add(new Coordinates(x, y+1));
        }
        if(x != 0 && Grid.Pole(x-1, y) != Grid.POLA.O && !Grid.grid_pom[x-1][y]) {
            pola.add(new Coordinates(x-1, y));
        }
        if(x+1 != Grid.x && Grid.Pole(x+1, y) != Grid.POLA.O && !Grid.grid_pom[x+1][y]) {
            pola.add(new Coordinates(x+1, y));
        }
        return pola;
    }

    @Override
    public int compareTo(Coordinates o) {
        return czas - o.czas;
    }
}