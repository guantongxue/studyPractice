package com.minjiang.es.test;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @auther guannw
 * @create 2021/8/25 15:24
 */
public class ESTest_Client {
    public static void main(String[] args) throws IOException {

        //创建es的客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );

        //关闭es客户端es
        esClient.close();
    }
}
