/*
Написать функцию,
создающую резервную копию всех файлов в директории(без поддиректорий) во вновь созданную папку ./backup
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Task1 {
    public static void main(String[] args) {


        String sourceDir = "src/main/java";
        String backupDir = "backup";

        try {
            createBackup(sourceDir, backupDir);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    public static void createBackup(String sourceDir, String backupDir) throws IOException {
        File sourceDirectory = new File(sourceDir);
        File backupDirectory = new File(backupDir);
        if (sourceDirectory.exists() && sourceDirectory.isDirectory()) {
            if (!backupDirectory.exists() && !backupDirectory.mkdir()) {
                System.err.println("Не удалось создать папку для резервных копий.");
            } else {
                File[] files = sourceDirectory.listFiles();
                if (files != null) {
                    File[] buffer = files;

                    for(int i = 0; i < files.length; ++i) {
                        File file = buffer[i];
                        if (file.isFile()) {
                            File backupFile = new File(backupDirectory, file.getName());
                            Files.copy(file.toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("Создана резервная копия файла: " + backupFile.getAbsolutePath());
                        }
                    }

                    System.out.println("Резервные копии созданы успешно!");
                } else {
                    System.err.println("Ошибка при получении списка файлов из исходной директории.");
                }

            }
        } else {
            System.err.println("Исходная директория не существует или не является директорией.");
        }
    }
}