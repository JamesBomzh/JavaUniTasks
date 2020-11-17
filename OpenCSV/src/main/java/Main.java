import java.util.*;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {
    public static  void main(String[] args) throws Exception {
        FileReader fileReader = new FileReader("C:\\Users\\JamesBomzh\\Desktop\\foreign_names.csv");
        CSVReader csvreader = new CSVReader(fileReader, ';');
        List<Human> list = new ArrayList<>();
        String[] strings;
        try {
            while ((strings = csvreader.readNext()) != null) {
                list.add(new Human(strings[0], strings[1], strings[2], strings[5], strings[3], strings[4]));

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
                if (csvreader != null) {
                    try {
                        csvreader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }

        for (Human human : list) {
            System.out.println(human.getId() + "; " + human.getName() + "; " + human.getGender() + "; " +
                    human.getDivisionName() + "; " + human.getDivisionId() + "; " + human.getSalary() +
                    "; " + human.getBirtDate());
        }
    }
}
