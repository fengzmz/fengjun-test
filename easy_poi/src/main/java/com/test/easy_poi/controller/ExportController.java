package com.test.easy_poi.controller;

import cn.afterturn.easypoi.entity.ImageEntity;

import com.test.easy_poi.util.ExportUtil;
import com.test.easy_poi.util.JFreeChartToFileUtil;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author chenbin
 * @date 2021/10/11
 */
@RestController
public class ExportController {

    @RequestMapping("export")
    public void export(HttpServletResponse response) throws IOException {
        response.setContentType("application/msword");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("曹永阳授权书.docx", StandardCharsets.UTF_8.name()));
        OutputStream outputStream = response.getOutputStream();


        Map<String, Object> map = new HashMap<String, Object>();

        putBaseInfo(map);
        putImg(map);
//        putList(map);

//        zhuzhuangtu(map);
        //putBar(map);


        String url =  Objects.requireNonNull(getClass().getClassLoader().getResource("static/notarization_example.docx")).getPath();

        File tempFile = File.createTempFile("tempDoc", ".docx");

        ExportUtil.export(map, url, tempFile);


        InputStream in = new FileInputStream(tempFile);

        //创建存放文件内容的数组
        byte[] buff = new byte[1024];
        //所读取的内容使用n来接收
        int n;
        //当没有读取完时,继续读取,循环
        while ((n = in.read(buff)) != -1) {
            //将字节数组的数据全部写入到输出流中
            outputStream.write(buff, 0, n);
        }
        //强制将缓存区的数据进行输出
        outputStream.flush();
        //关流
        outputStream.close();
        in.close();
        tempFile.deleteOnExit();

    }


    private void putBaseInfo(Map<String, Object> map) {
        map.put("principal_name", "曹永阳");
        map.put("principal_idcard", "500382200101010000");
        map.put("product_name", "亲属关系");
        map.put("year", "2022");
        map.put("month", "01");
        map.put("day", "25");
    }

    private void putImg(Map<String, Object> map) {
        ImageEntity image = new ImageEntity();
        image.setHeight(30);
        image.setWidth(125);
        image.setUrl("http://tutu-visa.cn-bj.ufileos.com/4b016762-d718-4c38-8c6a-94c43ebcd25f.png?UCloudPublicKey=TOKEN_c35a6475-7e4d-4b19-a5b5-b1d5b85765c7&Signature=NtLThueOKNHs6vy%2BdveveZuVOgo%3D&Expires=3445434212");
        image.setType(ImageEntity.URL);
        map.put("img", image);
    }

    private void putBar(Map<String, Object> map) throws IOException {

        File file2 = File.createTempFile("temp", "jpg");


        DefaultPieDataset pds = new DefaultPieDataset();

        pds.setValue("上市公司股票", 100);
        pds.setValue("非上市公司股权", 200);
        pds.setValue("传统不良债权", 300);
        pds.setValue("抵债资产", 400);
        pds.setValue("投资性房地产", 500);
        pds.setValue("长期股权投资", 600);

        JFreeChartToFileUtil.createPieChart(pds, file2, "账面价值比例");

        ImageEntity image = new ImageEntity();
        image.setHeight(200);
        image.setWidth(500);
        System.out.println(file2.getAbsolutePath());
        image.setUrl(file2.getAbsolutePath());
        image.setType(ImageEntity.URL);
        map.put("img", image);
    }

    public void zhuzhuangtu(Map<String, Object> dataMap) throws IOException {
        File file2 = File.createTempFile("temp", "jpg");
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();


        List<String> nameList = new ArrayList<>();
        nameList.add("广东省");
        nameList.add("河南省");
        nameList.add("内蒙古自治区");
        nameList.add("黑龙江省");
        nameList.add("新疆");
        nameList.add("湖北省");
        nameList.add("辽宁省");
        nameList.add("山东省");
        nameList.add("陕西省");
        nameList.add("上海市");
        nameList.add("贵州省");
        nameList.add("重庆市");
        nameList.add("西藏自治区");
        nameList.add("安徽省");
        nameList.add("福建省");
        nameList.add("湖南省");
        nameList.add("海南省");
        nameList.add("江苏省");
        nameList.add("广西");
        nameList.add("宁夏");
        nameList.add("青海省");
        nameList.add("江西省");
        nameList.add("浙江省");
        nameList.add("山西省");
        nameList.add("四川省");
        nameList.add("香港特别行政区");
        nameList.add("澳门特别行政区");
        nameList.add("云南省");
        nameList.add("北京市");
        nameList.add("天津市");
        nameList.add("吉林省");


        List<String> areaList = new ArrayList<>();

        nameList.forEach(i -> {
            if (i.contains("省")) {
                areaList.add(i.replace("省", ""));
            } else if (i.contains("特别行政区")) {
                areaList.add(i.replace("特别行政区", ""));
            } else if (i.contains("市")) {
                areaList.add(i.replace("市", ""));
            } else if (i.contains("自治区")) {
                areaList.add(i.replace("自治区", ""));
            } else {
                areaList.add(i);
            }
        });


        areaList.forEach(i -> {
            dataset.setValue(23434, "账面价值", i);
        });

        areaList.forEach(i -> {
            dataset.setValue(34234234, "估值结果", i);
        });

        JFreeChartToFileUtil.createBarChart(dataset, file2, "资产结果");
        ImageEntity image = new ImageEntity();
        image.setHeight(200);
        image.setWidth(500);
        System.out.println(file2.getAbsolutePath());
        image.setUrl(file2.getAbsolutePath());
        image.setType(ImageEntity.URL);
        dataMap.put("img", image);
    }

    private void putList(Map<String, Object> map) {
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, String> map1 = new HashMap<>();
            map1.put("name", "xiao");
            map1.put("age", "12");

            list.add(map1);
        }

        map.put("list", list);
    }

}


