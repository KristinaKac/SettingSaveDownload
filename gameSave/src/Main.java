import java.io.*;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress progress1 = new GameProgress(95, 10, 2, 3.5, "C:\\Users\\User\\Games\\sagegames\\save1.dat");
        GameProgress progress2 = new GameProgress(81, 7, 4, 6.5, "C:\\Users\\User\\Games\\sagegames\\save2.dat");
        GameProgress progress3 = new GameProgress(50, 15, 9, 12.7, "C:\\Users\\User\\Games\\sagegames\\save3.dat");
        String zipPath = "C:\\Users\\User\\Games\\sagegames\\zip.zip";
        String pathFile = "C:\\Users\\User\\Games\\sagegames";
        GameProgress[] arr = {progress1, progress2, progress3};
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            saveGame(arr[i]);
            zipFiles(arr[i], zipPath, arr);
            deliteFile(arr[i]);
            saveGame(arr[i]);
            openZip(zipPath);
            openProgress(arr[i]);
        }

    }

    public static void saveGame(GameProgress progress) {
        try (FileOutputStream fos = new FileOutputStream(progress.path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(progress);
            System.out.println("Прогресс " + progress + " сохранен");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void zipFiles(GameProgress progress, String zipPath, GameProgress[] arr) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipPath));
             FileInputStream fis = new FileInputStream(progress.path)) {
            for (int i = 0; i < arr.length; i++) {
                ZipEntry entry = new ZipEntry("save" + i + ".dat");
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            }
            System.out.println("Прогресс был добавлен в архив " + zipPath);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void deliteFile(GameProgress progress) {
        File file = new File(progress.path);
        if (file.delete()) {
            System.out.println("Файл " + file.getName() + " удален");
        }
    }

    public static void openZip(String zipPath) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            String name;
            while ((entry = zis.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fos = new FileOutputStream(name);
                for (int c = zis.read(); c != -1; c = zis.read()) {
                    fos.write(c);
                }
                fos.flush();
                zis.closeEntry();
                fos.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void openProgress(GameProgress progress){
        GameProgress gameProgress = null;
        try(FileInputStream fis = new FileInputStream(progress.path);
            ObjectInputStream ois = new ObjectInputStream(fis)){
            gameProgress = (GameProgress)ois.readObject();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(gameProgress);
    }
}