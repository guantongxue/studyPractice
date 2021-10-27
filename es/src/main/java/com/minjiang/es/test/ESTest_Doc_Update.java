package com.minjiang.es.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minjiang.es.entity.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @auther guannw
 * @create 2021/8/25 15:35
 */
public class ESTest_Doc_Update {
    public static void main(String[] args) throws IOException {

        //创建es的客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );

        //更新修改数据 局部修改
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("user").id("1001");
        updateRequest.doc(XContentType.JSON,"sex","女");
        UpdateResponse reponse = esClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(reponse.getResult());
        //关闭es客户端es
        esClient.close();
    }
}
