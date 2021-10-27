package com.minjiang.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexAction;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

/**
 * @auther guannw
 * @create 2021/8/25 23:20
 */
public class ESTest_Index_Delete {

    public static void main(String[] args) throws IOException {
        //创建es的客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );

        //删除索引
        DeleteIndexRequest deleteIndexAction = new DeleteIndexRequest("user");
        AcknowledgedResponse deleteResponse = esClient.indices().delete(deleteIndexAction, RequestOptions.DEFAULT);
        //响应状态
        System.out.println("删除索引 "+ deleteResponse.isAcknowledged());
        //关闭es客户端es
        esClient.close();
    }
}
