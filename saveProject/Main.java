import java.io.*;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        String pathFile = "C:" + File.separator + "Users" + File.separator + "User" +
                File.separator + "Games" + File.separator + "savegames" + File.separator;
        GameProgress progress1 = new GameProgress(95, 10, 2, 3.5,
                new File(pathFile, "save1.dat"));
        GameProgress progress2 = new GameProgress(81, 7, 4, 6.5,
                new File(pathFile, "save2.dat"));
        GameProgress progress3 = new GameProgress(50, 15, 9, 12.7,
                new File(pathFile, "save3.dat"));
        String zipPath = "C:" + File.separator + "Users" + File.separator + "User" +
                File.separator + "Games" + File.separator + "savegames" + File.separator + "zip.zip";
        GameProgress[] arr = {progress1, progress2, progress3};
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            saveGame(arr[i]);
            zipFiles(arr[i], zipPath, arr);
            deliteFile(arr[i]);
            openZip(zipPath, pathFile);
            openProgress(pathFile);
        }

    }

    public static void saveGame(GameProgress progress) {
        try (FileOutputStream fos = new FileOutputStream(progress.file.getPath());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(progress);
            System.out.println("Прогресс " + progress + " сохранен");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void zipFiles(GameProgress progress, String zipPath, GameProgress[] arr) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipPath));
             FileInputStream fis = new FileInputStream(progress.file.getPath())) {
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
        File file = new File(String.valueOf(progress.file));
        if (file.delete()) {
            System.out.println("Файл " + file.getPath() + " удален");
        }
    }

    public static void openZip(String zipPath, String pathFile) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            String name;
            while ((entry = zis.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fos = new FileOutputStream(pathFile + "new" +name);
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
    public static void openProgress(String pathFile){
        GameProgress gameProgress = null;
        try(FileInputStream fis = new FileInputStream(pathFile + "newsave0.dat");
            ObjectInputStream ois = new ObjectInputStream(fis)){
            gameProgress = (GameProgress)ois.readObject();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(gameProgress);
    }
}