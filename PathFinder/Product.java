public class Product {
    String name;
    int n;
    Coordinates place;

    public Product(String line) {
        int i = 0;
        name = "";
        while(line.charAt(i) != ' ') {
            name += Character.toString(line.charAt(i));
            i += 1;
        }
        //System.out.println(name);
        i += 1;

        String pos_x_str = "";
        while(line.charAt(i) != ' ') {
            pos_x_str += line.charAt(i);
            i += 1;
        }
        i += 1;
        String pos_y_str = "";
        while(line.charAt(i) != ' ') {
            pos_y_str += line.charAt(i);
            i += 1;
        }

        try {
            int x = Integer.parseInt(pos_x_str);
            int y = Integer.parseInt(pos_y_str);
            place = new Coordinates(x, y);
        }
        catch (NumberFormatException nfe) {
            System.out.println("Problem NFE");
            System.exit(0);
        }

        i += 1;

        String n_str = "";
        while(i < line.length() && line.charAt(i) != ' ') {
            n_str += line.charAt(i);
            i += 1;
        }
        try {
            n = Integer.parseInt(n_str);
        }
        catch (NumberFormatException nfe) {
            System.out.println("Problem NFE");
            System.exit(0);
        }
    }
}
