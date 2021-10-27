package com.minjiang.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

/**
 * @auther guannw
 * @create 2021/8/25 15:35
 */
public class ESTest_Doc_Query {
    public static void main(String[] args) throws IOException {

        //创建es的客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );

        //查询索引中全部的数据
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());//查询条件查询所有
//        request.source(query);
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//        SearchHits hits = response.getHits();
//        System.out.println(hits.getTotalHits());//打印获取的查询条数
//        for (SearchHit hit : hits){
//            System.out.println(hit.getSourceAsString());
//        }


        //2.条件查询
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//        request.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("age",30)));
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//        SearchHits hits = response.getHits();
//        for (SearchHit hit : hits){
//            System.out.println(hit.getSourceAsString());
//        }

        //3.分页查询
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        builder.from(0);
//        builder.size(2);
//        request.source(builder);
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//        SearchHits hits = response.getHits();
//        for (SearchHit hit : hits){
//            System.out.println(hit.getSourceAsString());
//        }

//        //4.分页查询加排序
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        builder.sort("age", SortOrder.ASC);
//        request.source(builder);
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//        SearchHits hits = response.getHits();
//        for (SearchHit hit : hits){
//            System.out.println(hit.getSourceAsString());
//        }

        //5.排序加过滤字段
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        builder.sort("age", SortOrder.ASC);
//        String[] excludes = {};
//        String[] includes = {"name"};
//        builder.fetchSource(includes,excludes);
//        request.source(builder);
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//        SearchHits hits = response.getHits();
//        for (SearchHit hit : hits){
//            System.out.println(hit.getSourceAsString());
//        }

//        //6.组合查询
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        //must交集必须，should并集或者
//        boolQueryBuilder.must(QueryBuilders.matchQuery("age",30));
//        boolQueryBuilder.must(QueryBuilders.matchQuery("sex","男"));
//        builder.query(boolQueryBuilder);
//        request.source(builder);
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//        SearchHits hits = response.getHits();
//        for (SearchHit hit : hits){
//            System.out.println(hit.getSourceAsString());
//        }

        //7.范围查询
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age");
//        rangeQueryBuilder.gte(20);//大于等于20
//        rangeQueryBuilder.lte(30);//小于等于30
//        builder.query(rangeQueryBuilder);
//        request.source(builder);
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//        SearchHits hits = response.getHits();
//        for (SearchHit hit : hits){
//            System.out.println(hit.getSourceAsString());
//        }

//        //8.模糊查询
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name","zhangsan").fuzziness(Fuzziness.ONE);//模糊查询指定字段  fuzziness相差几个字符
//
//        builder.query(fuzzyQueryBuilder);
//        request.source(builder);
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//        SearchHits hits = response.getHits();
//        for (SearchHit hit : hits){
//            System.out.println(hit.getSourceAsString());
//        }


        //10.聚合查询
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        AggregationBuilder aggregationBuilder = AggregationBuilders.max("maxAge").field("age");//指定最大值字段为age名为maxAge，
//
//        builder.aggregation(aggregationBuilder);
//
//        request.source(builder);
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//        SearchHits hits = response.getHits();
//        for (SearchHit hit : hits){
//            System.out.println(hit.getSourceAsString());
//        }

        //10.分组查询
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();

        //根据年龄进行分组，名为age_group
        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("age_group").field("age");

        builder.aggregation(aggregationBuilder);

        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits){
            System.out.println(hit.getSourceAsString());
        }
        //关闭es客户端es
        esClient.close();
    }
}
