package com.jet.fighter.file;


import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 没事多吃华莱士,喷射战士
 * <p>
 * 根据输入的文件生成压缩包
 * <p>
 * Description: {@link FileToZipUtils }
 *
 * @Author: di.zhang
 * @Date: 2024/5/14 14:05
 * @Version: v1.0
 */
public class FileToZipUtils {

    private FileToZipUtils() {
    }

    /**
     * Description: 输入源文件到压缩包
     * sourceFile 源文件地址
     * destinationZipFile 压缩包的目的地址
     *
     * @Author: di.zhang
     * @Date: 2024/5/14 14:06
     * @Param [java.io.File]
     * @Return void
     */
    public static void fileToZip(File sourceFile, File destinationZipFile) throws IOException {

        ZipFile zipFile = null;
        try {
            // 创建ZipFile对象
            zipFile = new ZipFile(destinationZipFile);

            // 创建ZipParameters对象，设置加密参数
            ZipParameters parameters = new ZipParameters();
            //默认的压缩级别，平衡了压缩速度和压缩比。
            parameters.setCompressionLevel(CompressionLevel.NORMAL);
            // 将源文件添加到压缩文件中
            zipFile.addFile(sourceFile, parameters);

        } finally {
            assert zipFile != null;
            zipFile.close();
        }

    }

    /**
     * Description: 输入源文件流 到压缩包
     * sourceStream 源文件流
     * sourceFileName 源文件名字+格式 例如：demo.png
     * destinationZipFile 压缩包的目的地址
     *
     * @Author: di.zhang
     * @Date: 2024/5/14 14:06
     * @Param [java.io.InputStream]
     * @Param [java.lang.String]
     * @Return void
     */
    public static void fileToZip(InputStream sourceStream, String sourceFileName, File destinationZipFile) throws IOException {

        ZipFile zipFile = null;
        File tmpDirectory = null;
        File targetFile = null;
        FileOutputStream outputStream = null;
        try {

            String tempDir = System.getProperty("java.io.tmpdir");
            tmpDirectory = new File(tempDir);
            if (!tmpDirectory.exists()) {
                tmpDirectory.mkdirs();
            }

            targetFile = new File(tmpDirectory, sourceFileName);

            outputStream = new FileOutputStream(targetFile);

            byte[] buffer = new byte[1024];
            int bytesRead;

            //从InputStream 读取数据并写入到目标文件
            while ((bytesRead = sourceStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            //创建ZipFile对象
            zipFile = new ZipFile(destinationZipFile);

            //创建ZipParameters对象，设置加密参数
            ZipParameters parameters = new ZipParameters();
            //默认的压缩级别，平衡了压缩速度和压缩比。
            parameters.setCompressionLevel(CompressionLevel.NORMAL);
            //将源文件添加到压缩文件中
            zipFile.addFile(targetFile, parameters);

        } finally {
            assert zipFile != null;
            zipFile.close();
            if (tmpDirectory.exists()) {
                tmpDirectory.delete();
            }
            if (targetFile.exists()) {
                targetFile.delete();
            }
            outputStream.close();
            sourceStream.close();
        }

    }


    /**
     * Description: 输入源文件到压缩包 并且加密
     * sourceFile 源文件地址
     * destinationZipFile 压缩包的目的地址
     * passWord 加密密码
     *
     * @Author: di.zhang
     * @Date: 2024/5/14 14:06
     * @Param [java.io.File]
     * @Param [java.lang.String]
     * @Return void
     */
    public static void fileToZipAndEncrypt(File sourceFile, File destinationZipFile, String passWord) throws IOException {

        ZipFile zipFile = null;
        try {
            // 创建ZipFile对象
            zipFile = new ZipFile(destinationZipFile);

            //创建ZipParameters对象，设置加密参数
            ZipParameters parameters = new ZipParameters();
            //设置密码
            zipFile.setPassword(passWord.toCharArray());
            parameters.setEncryptFiles(true);
            //默认的压缩级别，平衡了压缩速度和压缩比。
            parameters.setCompressionLevel(CompressionLevel.NORMAL);
            //默认的ZIP正常加密较弱
            parameters.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD);
            //将源文件添加到压缩文件中
            zipFile.addFile(sourceFile, parameters);

        } finally {
            assert zipFile != null;
            zipFile.close();
        }

    }


    /**
     * Description: 输入源文件流 到压缩包 并且加密
     * sourceStream 源文件流
     * sourceFileName 源文件名字+格式 例如：demo.png
     * destinationZipFile 压缩包的目的地址
     * passWord 加密密码
     *
     * @Author: di.zhang
     * @Date: 2024/5/14 14:06
     * @Param [java.io.InputStream]
     * @Param [java.lang.String]
     * @Return void
     */
    public static void fileToZipAndEncrypt(InputStream sourceStream, String sourceFileName,
                                           File destinationZipFile, String passWord) throws IOException {

        ZipFile zipFile = null;
        File tmpDirectory = null;
        File targetFile = null;
        FileOutputStream outputStream = null;
        try {

            String tempDir = System.getProperty("java.io.tmpdir");
            tmpDirectory = new File(tempDir + "/tmp");
            if (!tmpDirectory.exists()) {
                tmpDirectory.mkdirs();
            }

            targetFile = new File(tmpDirectory, sourceFileName);

            outputStream = new FileOutputStream(targetFile);

            byte[] buffer = new byte[1024];
            int bytesRead;

            //从InputStream 读取数据并写入到目标文件
            while ((bytesRead = sourceStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            //创建ZipFile对象
            zipFile = new ZipFile(destinationZipFile);

            //创建ZipParameters对象，设置加密参数
            ZipParameters parameters = new ZipParameters();
            //设置密码
            zipFile.setPassword(passWord.toCharArray());
            parameters.setEncryptFiles(true);
            //默认的压缩级别，平衡了压缩速度和压缩比。
            parameters.setCompressionLevel(CompressionLevel.NORMAL);
            //默认的ZIP正常加密较弱
            parameters.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD);
            //将源文件添加到压缩文件中
            zipFile.addFile(targetFile, parameters);

        } finally {
            assert zipFile != null;
            zipFile.close();
            if (tmpDirectory.exists()) {
                tmpDirectory.delete();
            }
            if (targetFile.exists()) {
                targetFile.delete();
            }
            outputStream.close();
            sourceStream.close();
        }

    }

}