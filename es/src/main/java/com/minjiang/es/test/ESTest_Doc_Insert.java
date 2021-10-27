package com.minjiang.es.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minjiang.es.entity.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @auther guannw
 * @create 2021/8/25 15:35
 */
public class ESTest_Doc_Insert {
    public static void main(String[] args) throws IOException {

        //创建es的客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );

        //插入数据
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index("user").id("1001");
        User user = new User();
        user.setName("zhangsan");
        user.setAge(30);
        user.setSex("男");
        //向e插入数据必须将数据转换为jso数据格式
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);
        indexRequest.source(userJson,XContentType.JSON);
        IndexResponse reponse = esClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(reponse.getResult());//打印结果
        //关闭es客户端es
        esClient.close();
    }
}
