package com.minjiang.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

/**
 * @auther guannw
 * @create 2021/8/25 23:20
 */
public class ESTest_Index_Search {

    public static void main(String[] args) throws IOException {
        //创建es的客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );

        //创建索引
        GetIndexRequest request = new GetIndexRequest("user");
        GetIndexResponse getIndexResponse = esClient.indices().get(request, RequestOptions.DEFAULT);
        System.out.println("索引操作："+getIndexResponse.getAliases());//打印别名
        System.out.println("索引操作："+getIndexResponse.getMappings());//打印映射
        System.out.println("索引操作："+getIndexResponse.getSettings());//打印配置


        //关闭es客户端es
        esClient.close();
    }
}
