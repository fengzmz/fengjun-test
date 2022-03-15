package com.example.es.test;

import com.alibaba.fastjson.JSONObject;
import com.example.es.EsApplication;
import com.example.es.entity.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.annotation.Resource;
import java.io.IOException;

/**
 * @className EsTest
 * @description
 * @author fengjun
 * @date 2022年01月08日 上午12:04
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EsApplication.class)
public class EsTest {

    @Resource
    private RestHighLevelClient client;


    /**
     * 创建索引
     * @description
     * @author fengjun
     * @date 2022-01-10
     * @return void
     */
    @Test
    public void test() throws IOException {
        // 创建索引
        CreateIndexRequest request = new CreateIndexRequest("kuangshen_index");
        // 客户端 执行请求
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);

        // 响应状态
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.err.println("索引结果：" + createIndexResponse);
        System.out.println("索引操作，响应状态：" + acknowledged);

        // 关闭ES
        client.close();
    }

    /**
     * 判断索引是否存在
     * @description
     * @author fengjun
     * @date 2022-01-10
     * @return void
     */
    @Test
    public void test2() throws IOException {
        GetIndexRequest request = new GetIndexRequest("kuangshen_index");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println("结果:" + exists);
    }

    /**
     * 删除索引
     * @description
     * @author fengjun
     * @date 2022-01-10
     * @return void
     */
    @Test
    public void test3() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("kuangshen_index");
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println("结果:" + delete.isAcknowledged());
    }


    /**
     * 创建文档
     * @description
     * @author fengjun
     * @date 2022-01-10
     * @return void
     */
    @Test
    public void test4() throws IOException {
        // 1 创建对象
        User user = new User();
        user.setId(1);
        user.setName("李四");
        user.setAge(18);
        // 2 创建请求
        IndexRequest request = new IndexRequest("kuangshen_index");

        // 3 请求规则:  put/kuangshen_index/_doc/1
        request.id("0001");
        // 请求超时设置(可以省略)
        request.timeout(TimeValue.timeValueSeconds(1));

        // 4 将请求数据以json格式, 放入请求
        request.source(JSONObject.toJSONString(user),  XContentType.JSON);

        // 5 客户端发送请求
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println(response.toString());
        System.out.println(response.status());
    }

    /**
     * 获取文档
     * @description
     * @author fengjun
     * @date 2022-01-10
     * @return void
     */
    @Test
    public void test5() throws IOException {

        GetRequest getRequest = new GetRequest("kuangshen_index", "0001");
        // 1 判断文档 是否存在
        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        if(exists){
            // 2 获取文档内容
            GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
            System.out.println(getResponse.getSourceAsString());
            System.out.println(getResponse.toString());
        }else {
            System.out.println("未查询到文档信息");
        }
    }

    /**
     * 跟新文档
     * @description
     * @author fengjun
     * @date 2022-01-10
     * @return void
     */
    @Test
    public void test6() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("kuangshen_index", "0001");

        User user = new User();
        user.setId(2);
        user.setName("王八");
        user.setAge(18);
        // 跟新对象设置为 json格式
        updateRequest.doc(JSONObject.toJSONString(user), XContentType.JSON);

        UpdateResponse result = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(result.toString());
    }

    /**
     * 删除文档
     * @description
     * @author fengjun
     * @date 2022-01-10
     * @return void
     */
    @Test
    public void test7() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("kuangshen_index","0001");

        DeleteResponse result = client.delete(deleteRequest, RequestOptions.DEFAULT);

        System.out.println(result.status());
        System.out.println(result.toString());
    }



}
