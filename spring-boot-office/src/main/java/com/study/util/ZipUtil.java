package com.study.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    public static void main(String[] args) {
        String zipFileName = "C:\\Users\\YangYiBao\\AppData\\Local\\Temp\\zipTemp\\jcps-231031105012079437.zip";
        String folderToCompress = "C:\\Users\\YangYiBao\\AppData\\Local\\Temp\\zipTemp\\jcps-231031105012079437";
        String folderName = "jcps-231031105012079437";

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFileName))) {
            // 压缩文件夹
            compressFolder(folderToCompress, folderName, zipOutputStream);

            System.out.println("Folder compressed successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void compressFolder(String sourceFolder, String folderName, ZipOutputStream zipOutputStream) throws IOException {
        File folder = new File(sourceFolder);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // 压缩子文件夹
                    compressFolder(file.getAbsolutePath(), folderName + File.separator + file.getName(), zipOutputStream);
                } else {
                    // 压缩文件
                    addToZipFile(folderName + File.separator + file.getName(), file.getAbsolutePath(), zipOutputStream);
                    //addToZipFile(file.getName(), file.getAbsolutePath(), zipOutputStream);
                }
            }
        }
    }

    private static void addToZipFile(String fileName, String fileAbsolutePath, ZipOutputStream zipOutputStream) throws IOException {
        // 创建ZipEntry对象并设置文件名
        ZipEntry entry = new ZipEntry(fileName);
        zipOutputStream.putNextEntry(entry);

        // 读取文件内容并写入Zip文件
        try (FileInputStream fileInputStream = new FileInputStream(fileAbsolutePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                zipOutputStream.write(buffer, 0, bytesRead);
            }
        }

        // 完成当前文件的压缩
        zipOutputStream.closeEntry();
    }
}
