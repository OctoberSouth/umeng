package com.live.util.push;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

/**
 * 
 * 
 * @author 刘攀
 * @time 2019年4月11日上午10:08:49
 * @version 1.0
 * @describe
 */
public class PushClient {
	
	/**
	 * The user agent
	 */
	protected final String USER_AGENT = "Mozilla/5.0";

	/**
	 * This object is used for sending the post request to Umeng
	 */
	protected HttpClient client = HttpClientBuilder.create().build();
	
	/**
	 * The host
	 */
	protected static final String HOST = "http://msg.umeng.com";
	
	/**
	 * The upload path
	 */
	protected static final String UPLOAD_PATH = "/upload";
	
	/**
	 * The post path
	 */
	protected static final String POST_PATH = "/api/send";

	public boolean send(AbstractUmengNotification msg) throws Exception {
		String timestamp = Integer.toString((int)(System.currentTimeMillis() / 1000));
		msg.setPredefinedKeyValue("timestamp", timestamp);
		String url = HOST + POST_PATH;
        String postBody = msg.getPostBody();
        String sign = DigestUtils.md5Hex(("POST" + url + postBody + msg.getAppMasterSecret()).getBytes("utf8"));
        url = url + "?sign=" + sign;
        HttpPost post = new HttpPost(url);
        post.setHeader("User-Agent", USER_AGENT);
        StringEntity se = new StringEntity(postBody, "UTF-8");
        post.setEntity(se);
        // Send the post request and get the response
        HttpResponse response = client.execute(post);
        int status = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + status);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result.toString());
		Integer successCode = 200;
		if (status == successCode) {
            System.out.println("Notification sent successfully.");
        } else {
            System.out.println("Failed to send the notification!");
        }
        return true;
    }

	/**
	 * Upload file with device_tokens to Umeng
	 * 
	 * @param appkey
	 * @param appMasterSecret
	 * @param contents
	 * @return
	 * @throws Exception
	 * @author 刘攀
	 * @time 2019年4月11日上午10:33:07
	 * @version 1.0
	 * @describe
	 */
	public String uploadContents(String appkey,String appMasterSecret,String contents) throws Exception {
		// Construct the json string
		JSONObject uploadJson = new JSONObject();
		uploadJson.put("appkey", appkey);
		String timestamp = Integer.toString((int)(System.currentTimeMillis() / 1000));
		uploadJson.put("timestamp", timestamp);
		uploadJson.put("content", contents);
		// Construct the request
		String url = HOST + UPLOAD_PATH;
		String postBody = uploadJson.toString();
		String sign = DigestUtils.md5Hex(("POST" + url + postBody + appMasterSecret).getBytes("utf8"));
		url = url + "?sign=" + sign;
		HttpPost post = new HttpPost(url);
		post.setHeader("User-Agent", USER_AGENT);
		StringEntity se = new StringEntity(postBody, "UTF-8");
		post.setEntity(se);
		// Send the post request and get the response
		HttpResponse response = client.execute(post);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println(result.toString());
		// Decode response string and get file_id from it
		JSONObject respJson = new JSONObject(result.toString());
		String ret = respJson.getString("ret");
		String success = "SUCCESS";
		if (!success.equals(ret)) {
			throw new Exception("Failed to upload file");
		}
		JSONObject data = respJson.getJSONObject("data");
		String fileId = data.getString("file_id");
		// Set file_id into rootJson using setPredefinedKeyValue
		
		return fileId;
	}

}
