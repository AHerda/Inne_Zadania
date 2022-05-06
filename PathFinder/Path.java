//import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
//import java.util.HashSet;
import java.util.List;

public class Path implements Comparable<Path>{
    List<Coordinates > steps = new ArrayList<Coordinates>();
    Coordinates last_step = null;
    public int czas = 0;

    public Path(Path other, Coordinates step) {
        if(other != null) {
            for(Coordinates other_step : other.steps) {
                this.czas += Koszt(last_step, other_step);
                last_step = other_step;
                other_step.czas = this.czas;
                steps.add(other_step);
            }
        }
        this.czas += Koszt(last_step, step);

        step.czas = this.czas;
        steps.add(step);

        last_step = step;
    }

    float Koszt(Coordinates previous, Coordinates next) {
        if(previous == null) {
            return 0;
        }
        if(Grid.Pole(previous.x, previous.y) == Grid.POLA.S || Grid.Pole(next.x, next.y) == Grid.POLA.S) {
            return 4;
        }
        else if(Grid.Pole(previous.x, previous.y) == Grid.POLA.B || Grid.Pole(next.x, next.y) == Grid.POLA.B) {
            return 2;
        }
        return 1;
    }

    public boolean CzyZawiera (Coordinates coord) {
        for(Coordinates step : steps) {
            if(step.x == coord.x && step.y == coord.y) {
                return true;
            }
        }
        return false;
    }

    @Override
	public int compareTo(Path o) {
		return czas - o.czas;
	}
}



class PathFinder {
    Coordinates start, koniec;
    Grid grid;

    public PathFinder(Coordinates start, Coordinates koniec, Grid grid) {
        this.start = start;
        this.koniec = koniec;
        this.grid = grid;
    }

    //algorytm dijkstry preferujacy poziome trasy
    public Path FindPath() {
        List<Path> mozliwe_sciezki = new ArrayList<Path>();

        if(start.x == koniec.x && start.y == koniec.y) {
            return new Path(null, start);
        }
        mozliwe_sciezki.add(new Path(null, start));
        
        Grid.grid_pom[start.x][start.y] = true;
        

        while(!mozliwe_sciezki.isEmpty()) {
            Collections.sort(mozliwe_sciezki);
            //podwojnie sprawdzonie sortowanie listy
            for(int i = 0; i < mozliwe_sciezki.size(); i += 1) {
                if(mozliwe_sciezki.get(i).czas < mozliwe_sciezki.get(0).czas) {
                    Path chwilowa = mozliwe_sciezki.get(0);
                    mozliwe_sciezki.set(0, mozliwe_sciezki.get(i));
                    mozliwe_sciezki.set(i, chwilowa);
                }
            }
            Path mozliwa_sciezka = mozliwe_sciezki.remove(0);
            for(Coordinates step : mozliwa_sciezka.last_step.SasiedniePola(mozliwa_sciezka)) {
                Path nowa_sciezka = new Path(mozliwa_sciezka, step);

                if(step.x == koniec.x && step.y == koniec.y) {
                    return nowa_sciezka;
                }

                mozliwe_sciezki.add(nowa_sciezka);
                Collections.sort(mozliwe_sciezki);
                Grid.grid_pom[nowa_sciezka.last_step.x][nowa_sciezka.last_step.y] = true;
            }
        }
        return null;
    }

    //drugi funkvja dijkstry preferujaca pionowe drogi
    public Path FindPath2() {
        List<Path> mozliwe_sciezki = new ArrayList<Path>();

        if(start.x == koniec.x && start.y == koniec.y) {
            return new Path(null, start);
        }
        mozliwe_sciezki.add(new Path(null, start));
        
        Grid.grid_pom[start.x][start.y] = true;
        

        while(!mozliwe_sciezki.isEmpty()) {
            Collections.sort(mozliwe_sciezki);
            //podwojnie sprawdza posortowanie elementow
            for(int i = 0; i < mozliwe_sciezki.size(); i += 1) {
                if(mozliwe_sciezki.get(i).czas < mozliwe_sciezki.get(0).czas) {
                    Path chwilowa = mozliwe_sciezki.get(0);
                    mozliwe_sciezki.set(0, mozliwe_sciezki.get(i));
                    mozliwe_sciezki.set(i, chwilowa);
                }
            }
            Path mozliwa_sciezka = mozliwe_sciezki.remove(0);
            for(Coordinates step : mozliwa_sciezka.last_step.SasiedniePola2(mozliwa_sciezka)) {
                Path nowa_sciezka = new Path(mozliwa_sciezka, step);

                if(step.x == koniec.x && step.y == koniec.y) {
                    return nowa_sciezka;
                }

                mozliwe_sciezki.add(nowa_sciezka);
                Collections.sort(mozliwe_sciezki);
                Grid.grid_pom[nowa_sciezka.last_step.x][nowa_sciezka.last_step.y] = true;
            }
        }
        return null;
    }
}