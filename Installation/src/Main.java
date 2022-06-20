import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        StringBuilder inf = new StringBuilder();
        //1
        File src = new File("C:\\Users\\User\\Games", "//src");
        File res = new File("C:\\Users\\User\\Games", "//res");
        File savegames = new File("C:\\Users\\User\\Games", "//sagegames");
        File temp = new File("C:\\Users\\User\\Games", "//temp");
        if (src.mkdir() && res.mkdir() && savegames.mkdir() && temp.mkdir()){
            inf.append("Каталоги src, res, savegames и temp были созданы \n");
            System.out.println("Каталоги src, res, savegames и temp были созданы");
        }
        //2
        File main = new File("C:\\Users\\User\\Games\\src", "//main");
        File test = new File("C:\\Users\\User\\Games\\src", "//test");
        if (main.mkdir() && test.mkdir()){
            inf.append("Каталоги main и test были созданы \n");
            System.out.println("Каталоги main и test были созданы");
        }
        //3
        File mainJava = new File("C:\\Users\\User\\Games\\src\\main", "Main.java");
        File utilsJava = new File("C:\\Users\\User\\Games\\src\\main", "Utils.java");
        try {
            if (mainJava.createNewFile() && utilsJava.createNewFile()){
                inf.append("Файлы Main.java и Utils.java были созданы \n");
                System.out.println("Файлы Main.java и Utils.java были созданы");}
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //4
        File drawables = new File("C:\\Users\\User\\Games\\res", "//drawables");
        File vectors = new File("C:\\Users\\User\\Games\\res", "vectors");
        File icons = new File("C:\\Users\\User\\Games\\res", "icons");
        if (drawables.mkdir() && vectors.mkdir() && icons.mkdir()){
            inf.append("Каталоги drawables, vectors и icons созданы \n");
            System.out.println("Каталоги drawables, vectors и icons созданы");
        }
        //5
        File tempTxt = new File("C:\\Users\\User\\Games\\temp", "temp.txt");
        try {
            if (tempTxt.createNewFile()) {
                inf.append("Файл temp.txt был создан \n");
                System.out.println("Файл temp.txt был создан");
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        try (FileWriter writer = new FileWriter(tempTxt, false)){
            writer.write(String.valueOf(inf));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}