package com.test.easy_poi.util;

import cn.afterturn.easypoi.word.WordExportUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

/**
 * @author chenbin
 * @date 2021/10/11
 */
public class ExportUtil {
    public static void export(Map<String, Object> map, String url, File tempFile) {

        try {
            XWPFDocument doc = WordExportUtil.exportWord07(url, map);
            FileOutputStream fos = new FileOutputStream(tempFile);
            doc.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

