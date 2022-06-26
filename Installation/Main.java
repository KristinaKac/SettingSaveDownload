import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        StringBuilder inf = new StringBuilder();
        String filePath = "C:" + File.separator + "Users" + File.separator + "User" + File.separator + "Games";

        createDir(inf, (new File(filePath, "src")));
        createDir(inf, new File(filePath, "res"));
        createDir(inf, new File(filePath, "savegames"));
        createDir(inf, (new File(filePath, "temp")));
        createDir(inf, (new File(filePath + File.separator + "src", "main")));
        createDir(inf, (new File(filePath + File.separator + "src", "test")));
        createFile(inf, new File(filePath + File.separator + "src" + File.separator + "main", "Main.java"));
        createFile(inf, new File(filePath + File.separator + "src" + File.separator + "main", "Utils.java"));
        createDir(inf, (new File(filePath+ File.separator + "res", "drawables")));
        createDir(inf, (new File(filePath+ File.separator + "res", "vectors")));
        createDir(inf, (new File(filePath+ File.separator + "res", "icons")));
        createFile(inf, new File(filePath + File.separator + "temp", "temp.txt"));
        writeFile(new File(filePath + File.separator + "temp", "temp.txt"), inf);
    }
    private static File createDir(StringBuilder inf, File file){
        if (file.mkdir()){
            inf.append("Каталог " + file.getName() + " был создан \n");
            System.out.println("Каталог " + file.getName() + " был создан");
        }
        return file;
    }
    private static File createFile(StringBuilder inf, File file){
        try {
            if (file.createNewFile()){
                inf.append("Файл " + file.getName() + " был создан \n");
                System.out.println("Файл " + file.getName() + " был создан");}
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return file;
    }
    private static void writeFile(File file, StringBuilder inf){
        try (FileWriter writer = new FileWriter(file, false)){
            writer.write(String.valueOf(inf));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}