import java.io.*;
//import java.util.Collections;

public class Test {
    public static void main(String args[]) {
        File grid_file = new File(args[0]);
        File job_file = new File(args[1]);

        Grid grid = new Grid(grid_file);
        Robot robot = new Robot(job_file, grid);
        
        //System.out.println(robot.czas_min);
        //System.out.println(robot.paczka);
        System.out.println(robot.trasa_1.steps.size() + robot.trasa_2.steps.size() - 2);
        System.out.println(robot.czas_min/2.0f);

        for(int i = 0; i < robot.trasa_1.steps.size(); i += 1) {
            System.out.println("" + robot.trasa_1.steps.get(i).x + " " + robot.trasa_1.steps.get(i).y);
        }
        for(int i = 1; i < robot.trasa_2.steps.size(); i += 1) {
            System.out.println("" + robot.trasa_2.steps.get(i).x + " " + robot.trasa_2.steps.get(i).y);
        }
        /*int licznik = 0;
        for(Coordinates kordy : robot.trasa_2.steps) {
            if(licznik != 0) {
                System.out.println("" + kordy.x + " " + kordy.y);
            }
            licznik +=1;
        }*/
    }
}