package com.minjiang.es.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minjiang.es.entity.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @auther guannw
 * @create 2021/8/25 15:35
 */
public class ESTest_Doc_Insert_Batch {
    public static void main(String[] args) throws IOException {

        //创建es的客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );

        //批量插入数据
        BulkRequest request = new BulkRequest();
        //单条数据
        request.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON, "name", "zhangsan","age",30,"sex","男"));
        request.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON, "name", "lisi","age",25,"sex","女"));
        request.add(new IndexRequest().index("user").id("1004").source(XContentType.JSON, "name", "wangwu","age",26,"sex","男"));
        BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());//打印花费了多长时间
        //关闭es客户端es
        esClient.close();
    }
}
