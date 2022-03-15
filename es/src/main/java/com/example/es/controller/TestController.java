package com.example.es.controller;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @className TestController
 * @description
 * @author fengjun
 * @date 2022年01月08日 上午12:39
 */
@RestController
@RequestMapping("controller")
public class TestController {

    @Resource
    private RestHighLevelClient restHighLevelClient;


    @RequestMapping("test1")
    public String test() throws IOException {

        System.out.println("进入test方法");

        // 创建索引
        CreateIndexRequest request = new CreateIndexRequest("user123");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);

        // 响应状态
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println("索引操作，响应状态：" + acknowledged);

        // 关闭ES
        restHighLevelClient.close();

        return "success";
    }
}
