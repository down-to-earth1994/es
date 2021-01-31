package com.example.demo;


import com.example.config.ElasticSearchConfig;
import com.example.config.ElasticsearchUtil;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 *	springboot 默认支持两种技术来和es 交互
 *	1、Jest(默认不生效)
 *  需要导入jest 的工具包
 *  2、springboot elasticSearch
 *   	1) Client 节点信息 clusterNodes、clusterName
 *      2)	ElasticsearchTemplate 操作es
 *      3)  编写一个ElasticsearchRepository 操作es
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests2 {
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplicationTests2.class);
	public final static String INDEX_NAME = "ceshi";


	@Autowired
	private TransportClient client;



	@Test
	public void  createIndex(){

	}

	@Test
	public void  queryEs(){
		boolean indexExist = ElasticsearchUtil.isIndexExist(INDEX_NAME);
		LOGGER.info("es:{}",indexExist);

	}
}
