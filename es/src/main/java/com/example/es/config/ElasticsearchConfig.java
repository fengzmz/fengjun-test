package com.example.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className ElasticsearchConfig
 * @description
 * @author fengjun
 * @date 2022年01月07日 下午11:57
 */

@Configuration
public class ElasticsearchConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        // 创建es客户端
        RestHighLevelClient client = new RestHighLevelClient(
                // 设置后缀域名(我的es服务器做了nginx转发，正常状态不需要这个后缀),  http://fjpc.tpddns.cn:10000/es/
                // RestClient.builder(new HttpHost("fjpc.tpddns.cn",10000,"http"))
                RestClient.builder(new HttpHost("fjpc.tpddns.cn",10000,"http"))
                        .setPathPrefix("/es")
        );

        return client;
    }
}
