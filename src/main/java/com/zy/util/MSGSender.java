package com.zy.util;


import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;



public class MSGSender
{
  private static PoolingHttpClientConnectionManager cm = null;
  static  {
    init();
  }

  
  public static String sendMsg(String mobile, String code) {
    String sid = "7b825869c1483588fcc7e5ae890d4150";
    String token = "70b5fc9d699efebb9aa30810ac6b4ded";
    String appid = "28fe09ac101246d3ba1dce09b0ede7e0";
    String templateid = "391377";
    
    Map<String, Object> jsonObject = new HashMap<String, Object>();
    ObjectMapper objectMapper = new ObjectMapper();
    String body = null;
    try {
      jsonObject.put("sid", sid);
      jsonObject.put("token", token);
      jsonObject.put("appid", appid);
      jsonObject.put("templateid", templateid);
      jsonObject.put("param", code);
      jsonObject.put("mobile", mobile);
      jsonObject.put("uid", "");
      body = objectMapper.writeValueAsString(jsonObject);
    
    }
    catch (Exception e) {
      e.printStackTrace();
      return null;
    } 

    
    String url = "https://open.ucpaas.com/ol/sms/sendsms";
    
    String result = null;
    
    CloseableHttpClient httpClient = null;
    HttpPost httpPost = null;
    try {
      httpClient = HttpClients.custom().setConnectionManager(cm).build();
      httpPost = new HttpPost(url);


      
      RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(10000).build();
      httpPost.setConfig(requestConfig);
      
      httpPost.setHeader("Accept", "application/json");
      httpPost.setHeader("Content-Type", "application/json;charset=utf-8");

      
      StringEntity se = new StringEntity(body, "UTF-8");
      httpPost.setEntity(se);
      CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
      if (closeableHttpResponse != null) {
        HttpEntity resEntity = closeableHttpResponse.getEntity();
        if (resEntity != null) {
          result = EntityUtils.toString(resEntity, "UTF-8");
        }
      } 
    } catch (Exception ex) {
      ex.printStackTrace();
    } 
    
    return result;
  }

  private static void init() {
    SSLConnectionSocketFactory sSLConnectionSocketFactory = null;
    try {
      sSLConnectionSocketFactory = new SSLConnectionSocketFactory(SSLContext.getDefault());
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } 

    Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
            .register("https", sSLConnectionSocketFactory).register("http", new PlainConnectionSocketFactory()).build();
    cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
    cm.setMaxTotal(200);
    cm.setDefaultMaxPerRoute(20);
  }
  
  public static void main(String[] args) {
    String result = sendMsg("13033615730", "测试最长可以发送几个数字");
    System.err.println(result);
  }
}
