import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Robot {
    Coordinates start, zrzut;
    String paczka;
    Grid grid;
    float czas_min = Integer.MAX_VALUE;
    public Path trasa_1, trasa_2;
    private Path trasa_1_chwila, trasa_2_chwila;

    //konstruktor pobiera i przygorowuje dane do uzycia
    public Robot(File job_file, Grid grid) {
        this.grid = grid;

        try {
            BufferedReader reader_job = new BufferedReader(new FileReader(job_file));

            String line = reader_job.readLine();
            int i = 0;

            String x_str = "";
            while(line.charAt(i) != ' ') {
                x_str += line.charAt(i);
                i += 1;
            }

            i += 1;

            String y_str = "";
            while(i < line.length() && line.charAt(i) != ' ') {
                y_str += line.charAt(i);
                i += 1;
            }
            try {
                int x = Integer.parseInt(x_str);
                int y = Integer.parseInt(y_str);
                start = new Coordinates(x, y);
            }
            catch (NumberFormatException nfe) {
                System.out.println("Problem NFE");
                System.exit(0);
            }

            line = reader_job.readLine();
            i = 0;

            x_str = "";
            while(line.charAt(i) != ' ') {
                x_str += line.charAt(i);
                i += 1;
            }

            i += 1;

            y_str = "";
            while(i < line.length() && line.charAt(i) != ' ') {
                y_str += line.charAt(i);
                i += 1;
            }
            try {
                int x = Integer.parseInt(x_str);
                int y = Integer.parseInt(y_str);
                zrzut = new Coordinates(x, y);
            }
            catch (NumberFormatException nfe) {
                System.out.println("Problem NFE");
                System.exit(0);
            }
            paczka = reader_job.readLine();
            
            reader_job.close();
        }
        catch(IOException ioe) {
            System.out.println("Problem IOE");
            System.exit(0);
        }

        Solve();
    }

    void Solve() {
        List<Product> paczki = new ArrayList<>(grid.products);

        for(Product paczka_lista : paczki) {
            if(paczka.equals(paczka_lista.name)) {
                //sprawdza podwojnie trase ze startu do  ze wzgledu na problem z preferowaniem poziomych tras
                Grid.ClearGridPom();
                Path trasa_1_chwila1 = new PathFinder(start, paczka_lista.place, grid).FindPath();
                Grid.ClearGridPom();
                Path trasa_1_chwila2 = new PathFinder(start, paczka_lista.place, grid).FindPath2();
                if(trasa_1_chwila1.czas > trasa_1_chwila2.czas) {
                    trasa_1_chwila = trasa_1_chwila2;
                }
                else {
                    trasa_1_chwila = trasa_1_chwila1;
                }
                
                
                Grid.ClearGridPom();
                Path trasa_2_chwila1 = new PathFinder(paczka_lista.place, zrzut, grid).FindPath();
                Grid.ClearGridPom();
                Path trasa_2_chwila2 = new PathFinder(paczka_lista.place, zrzut, grid).FindPath2();
                if(trasa_2_chwila1.czas > trasa_2_chwila2.czas) {
                    trasa_2_chwila = trasa_2_chwila2;
                }
                else {
                    trasa_2_chwila = trasa_2_chwila1;
                }

                int czas_wyciagania = Wyciaganie(paczka_lista.place.x, paczka_lista.place.y, paczka_lista.n);

                //przepisywanie ofcjalnie najlepszej trasy
                if((trasa_1_chwila.czas + trasa_2_chwila.czas + czas_wyciagania) < czas_min) {
                    trasa_1 = trasa_1_chwila;
                    trasa_2 = trasa_2_chwila;
                    czas_min = (trasa_1_chwila.czas + trasa_2_chwila.czas + czas_wyciagania);
                }
            }
        }
    }

    int Wyciaganie(int x, int y, int n) {
        if(Grid.Pole(x, y) == Grid.POLA.H) {
            return (3 * n + 4) * 2;
        }
        else if(Grid.Pole(x, y) == Grid.POLA.B) {
            return (2 * n + 2) * 2;
        }
        else if(Grid.Pole(x, y) == Grid.POLA.S) {
            return (1 * n + 1) * 2;
        }
        else {
            System.exit(0);
        }
        return 0;
    }
}