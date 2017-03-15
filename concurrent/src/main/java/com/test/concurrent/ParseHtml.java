package com.test.concurrent;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class ParseHtml {

	private static CloseableHttpClient client;
	private static HttpPost httpPost;
	private static List<NameValuePair> param;
	
	static {
		client = HttpClientBuilder.create().build();
		// Post提交地址
		httpPost = new HttpPost("http://service.bj.10086.cn/phone/num/phoneNumSearch/showNumList.action");
		//http://www.hnxdf.com/vote/index.asp
		// 设置header
		httpPost.setHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.472.63 Safari/534.3");
		// 提交参数
		param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("pnsVO.searchType", "0"));
		param.add(new BasicNameValuePair("pnsVO.lastNumber", "88"));
//		<input type="hidden" name="pnsVO.searchType" value="0"/>
//		<input type="hidden" name="pnsVO.brand" value=""/>
//		<input type="hidden" name="pnsVO.reserveFee" value=""/>
//		<input type="hidden" name="pnsVO.segmentTag" value=""/>
//		<input type="hidden" name="pnsVO.beautifulTag" value=""/>
//		<input type="hidden" name="pnsVO.lastNumber" value=""/>
	}
	
	public static void main(String[] args) {
		RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(5000).setSocketTimeout(5000).build();
		httpPost.setConfig(config);
		try {
			
			httpPost.setEntity(new UrlEncodedFormEntity(param));
			HttpResponse response = client.execute(httpPost);

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String result = EntityUtils.toString(response.getEntity(), "GBK");
				System.out.println(result);
			} else {
				String result = EntityUtils.toString(response.getEntity(), "GBK");
				System.out.println(result);
			}
			
			// 关闭连接非常重要，否则连接池溢出
			EntityUtils.consume(response.getEntity());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
