package com.sh.znks.common.base.http;

import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wuminggu on 2018/6/15.
 */
public class HttpRequestUtils {
    private static Log logger = LogFactory.getLog(HttpRequestUtils.class);    //日志记录

    /**
     * httpPost
     * @param url  路径
     * @param jsonParam 参数
     * @return
     */
    public static JSONObject httpPost(String url, JSONObject jsonParam){
        return httpPost(url, jsonParam, false);
    }

    /**
     * post请求
     * @param url         url地址
     * @param jsonParam     参数
     * @param noNeedResponse    不需要返回结果
     * @return
     */
    public static JSONObject httpPost(String url, JSONObject jsonParam, boolean noNeedResponse){
        //post请求返回结果
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        // method.addHeader("Content-type","application/json; charset=utf-8");
        //method.setHeader("Accept", "application/json");
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                //entity.setContentEncoding("utf-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            Integer resultCode=result.getStatusLine().getStatusCode();
            if (resultCode == 200||resultCode == 201) {
                String str = "";
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    /**把json字符串转换成json对象**/
                    jsonResult = JSONObject.fromObject(str);
                } catch (Exception e) {
                    logger.error("post请求提交失败:" + url, e);
                }
            }else{
                logger.error("post请求提交失败:"+url+" data:"+jsonParam.toString()+"statusCode:"+result.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            logger.error("post请求提交失败:" + url, e);
        }finally {
            method.releaseConnection();
        }
        return jsonResult;
    }

    /**
     * 发送put请求
     * @param url    路径
     * @param  jsonParam 请求参数,json格式
     * @return
     */
    public static JSONObject httpPut(String url, JSONObject jsonParam){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead
        JSONObject jsonResult = null;
        HttpPut putMethod = new HttpPut(url);
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                //entity.setContentEncoding("utf-8");
                entity.setContentType("application/json");
                putMethod.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(putMethod);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            Integer resultCode=result.getStatusLine().getStatusCode();
            if (resultCode == 200||resultCode == 201) {
                String str = "";
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity());
                    /**把json字符串转换成json对象**/
                    jsonResult = JSONObject.fromObject(str);
                } catch (Exception e) {
                    //System.out.println(e);
                    logger.error("put请求提交失败:" + url, e);
                }
            }
        } catch (IOException e) {
            //System.out.println(e);
            logger.error("put请求提交失败:" + url, e);
        }finally {
            putMethod.releaseConnection();
        }
        return jsonResult;
    }

    public static JSONObject httpDelete(String url){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead
        JSONObject jsonResult = null;
        HttpDelete deleteMethod = new HttpDelete(url);
        try {
            HttpResponse result = httpClient.execute(deleteMethod);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(result.getEntity());
                /**把json字符串转换成json对象**/
                jsonResult = JSONObject.fromObject(strResult);
            } else {
                logger.error("delete请求提交失败:" + url);
            }
        } catch (IOException e) {
            logger.error("delete请求提交失败:" + url, e);
        }finally {
            deleteMethod.releaseConnection();
        }
        return jsonResult;
    }

    /**
     * 发送get请求
     * @param url    路径
     * @return
     */
    public static JSONObject httpGet(String url){
        //get请求返回结果
        JSONObject jsonResult = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());
                /**把json字符串转换成json对象**/
                jsonResult = JSONObject.fromObject(strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {
                logger.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            logger.error("get请求提交失败:" + url, e);
        }
        return jsonResult;
    }

    /**
     * 发送get请求
     * @param url    路径
     * @return
     */
    public static JSONObject httpGetJSONP(String url){
        //get请求返回结果
        JSONObject jsonResult = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = JSONP2JSON(EntityUtils.toString(response.getEntity()));
                /**把json字符串转换成json对象**/
                jsonResult = JSONObject.fromObject(strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {
                logger.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            logger.error("get请求提交失败:" + url, e);
        }
        return jsonResult;
    }

    private static String JSONP2JSON(String jsonp) {
        int startIndex = jsonp.indexOf("(");
        int endIndex = jsonp.lastIndexOf(")");
        return jsonp.substring(startIndex+1, endIndex);
    }

    public static JSONObject httpPost2(String url, JSONObject jsonParam,
                                       boolean noNeedResponse) {
        // post请求返回结果
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonResult = null;
        HttpPost httppost = new HttpPost(url);

        try {
            if (null != jsonParam) {

                List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();

                Iterator it = jsonParam.keys();
                while (it.hasNext()) {
                    String key = (String) it.next();
                    nvps.add(new BasicNameValuePair(key, jsonParam
                            .getString(key)));
                }

                httppost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            }
            HttpResponse result = httpClient.execute(httppost);
            url = URLDecoder.decode(url, "UTF-8");
            /** 请求发送成功，并得到响应 **/
            Integer resultCode = result.getStatusLine().getStatusCode();
            if (resultCode == 200 || resultCode == 201) {
                String str = "";
                try {
                    /** 读取服务器返回过来的json字符串数据 **/
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    /** 把json字符串转换成json对象 **/
                    jsonResult = JSONObject.fromObject(str);
                } catch (Exception e) {
                    // System.out.println(e);
                    logger.error("post请求提交失败:" + url, e);
                }
            } else {
                logger.error("post请求提交失败:" + url + " data:"
                        + jsonParam.toString() + "statusCode:"
                        + result.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            // System.out.println(e);
            logger.error("post请求提交失败:" + url, e);
        } finally {
            httppost.releaseConnection();
        }
        return jsonResult;
    }


}
