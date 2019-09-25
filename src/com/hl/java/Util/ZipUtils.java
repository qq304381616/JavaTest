package com.hl.java.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    /**
     * 压缩文件
     *
     * @param files   key原文件路径， value压缩包内部路径
     * @param zipPath 压缩包完整路径
     */
    public static void zipFiles(Map<String, String> files, String zipPath) {
        File zipFile = new File(zipPath);
        if (zipFile.exists()) zipFile.delete();
        byte[] buf = new byte[1024];
        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
            for (Map.Entry<String, String> entry : files.entrySet()) {
                FileInputStream in = new FileInputStream(entry.getKey());
                out.putNextEntry(new ZipEntry(entry.getValue()));
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
