package com.jet.fighter.file;

import com.jet.fighter.string.StringUtils;
import com.jet.fighter.zip.FileToZipUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 没事多吃华莱士,喷射战士
 * <p>
 * 文件操作工具
 * <p>
 * Description: {@link FileToZipUtils }
 *
 * @Author: di.zhang
 * @Date: 2024/5/14 14:05
 * @Version: v1.0
 */

public class FileUtils {

    private FileUtils() {
    }

    /**
     * Description: 创建 file文件对象
     * dir 文件夹
     * fileName 文件名称
     *
     * @Author: di.zhang
     * @Date: 2024/5/14 14:06
     * @Param [java.lang.String]
     * @Return [java.io.File]
     */
    public static File createFile(String dir, String fileName) {

        if (StringUtils.isEmpty(dir)) {
            throw new NullPointerException("文件地夹地址为空");
        }

        if (StringUtils.isEmpty(fileName)) {
            throw new NullPointerException("文件名称为空");
        }

        File tempDir = new File(dir);

        //确保临时目录存在
        if (!tempDir.exists() && !tempDir.mkdirs()) {
            throw new RuntimeException("无法创建临时目录: " + tempDir.getAbsolutePath());
        }

        return new File(tempDir, fileName);
    }


    /**
     * Description: 创建临时文件夹
     *
     * @Author: di.zhang
     * @Date: 2024/5/14 14:06
     * @Return [java.io.File]
     */
    public static File createTempDir() {

        //获取系统临时目录路径
        String tempDirPath = System.getProperty("java.io.tmpdir");
        File tempDir = new File(tempDirPath);

        //确保临时目录存在
        if (!tempDir.exists() && !tempDir.mkdirs()) {
            throw new RuntimeException("无法创建临时目录: " + tempDir.getAbsolutePath());
        }

        return tempDir;

    }


    /**
     * Description: 创建 file 文件对象
     * path 文件地址 完整路径
     * inputStream 文件流
     *
     * @Author: di.zhang
     * @Date: 2024/5/14 14:06
     * @Param [java.lang.String]
     * @Param [java.io.InputStream]
     * @Return [java.io.File]
     */
    public static File createFileToTemp(InputStream inputStream, String fileName) {

        if (StringUtils.isEmpty(fileName)) {
            throw new NullPointerException("文件名称为空");
        }

        if (null == inputStream) {
            throw new NullPointerException("文件流为空");
        }

        String tempDir = System.getProperty("java.io.tmpdir");
        File tmpDirectory = new File(tempDir);
        if (!tmpDirectory.exists()) {
            tmpDirectory.mkdirs();
        }

        File targetFile = new File(tmpDirectory, fileName);

        try (FileOutputStream outputStream = new FileOutputStream(targetFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            //从InputStream 读取数据并写入到目标文件
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

        } catch (IOException e) {
            throw new RuntimeException("文件写入失败", e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("关闭输入流失败", e);
            }
        }

        return targetFile;
    }


    /**
     * Description: 完整删除文件夹
     * path 文件地址 绝对路径
     * 注意：方法可以递归遍历文件树，对每个文件和目录执行删除操作。
     *
     * @Author: di.zhang
     * @Date: 2024/5/14 14:06
     * @Param [java.lang.String]
     * @Return void
     */
    public static void deleteInFull(String absolutePath) throws IOException {

        Path var1 = new File(absolutePath).toPath();

        if (Files.notExists(var1)) {
            throw new IOException("文件或目录不存在: " + var1.toAbsolutePath());
        }

        Files.walkFileTree(var1, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                if (exc != null) {
                    throw exc;
                }
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });

    }

    /**
     * Description: 删除文件
     * dirPath 文件夹地址
     * fileName 文件
     *
     * @Author: di.zhang
     * @Date: 2024/5/14 14:06
     * @Param [java.lang.String]
     * @Return void
     */
    public static void deleteFileInDir(String dirPath, String fileName) throws IOException {

        if (StringUtils.isEmpty(dirPath)) {
            throw new NullPointerException("文件夹路径为空");
        }

        if (StringUtils.isEmpty(fileName)) {
            throw new NullPointerException("文件为空");
        }

        Path var1 = new File(dirPath).toPath();

        if (Files.notExists(var1) || !Files.isDirectory(var1)) {
            throw new IOException("目录不存在: " + var1.toAbsolutePath());
        }

        Path filePath = var1.resolve(fileName);

        if (Files.notExists(filePath) || !Files.isRegularFile(filePath)) {
            throw new IOException("文件不存在: " + filePath.toAbsolutePath());
        }

        Files.delete(filePath);

    }


}
