package com.hzzx.utils;

import java.io.File;

public class FileUtil {

    // 判断文件是否存在
    public static boolean fileExists(String filePath) {
        return new File(filePath).exists();
    }

    // 判断文件是否存在没有就新建
    public static void createFile(String filePath) {
        if (!FileUtil.fileExists(filePath)) {
            File file = new File(filePath);
            file.mkdir();
        }
    }
}
