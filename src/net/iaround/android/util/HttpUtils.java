package net.iaround.android.util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class HttpUtils {

	public HttpUtils() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**ʹ��Apache�ṩ�Ľӿ�д��post�ύ����
	 * @param path
	 * @param map
	 * @param encode
	 * @return
	 */
	public static String sendHttpClientPost(String path,Map<String, String> map,String encode)
	{
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		if(map !=null && !map.isEmpty())
		{
			for(Map.Entry<String, String> entry:map.entrySet())
			{
				//����������
				list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		try {
			//ʵ�ֽ�����Ĳ�����װ�����У���������
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,encode);
			//ʹ��post��ʽ�ύ����
			HttpPost httpPost = new HttpPost(path);
			httpPost.setEntity(entity);
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if(httpResponse.getStatusLine().getStatusCode() == 200)
			{
				return changeInputStream2String(httpResponse.getEntity().getContent(),encode);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	private static String changeInputStream2String(InputStream inputStream,String encode) {
		// TODO Auto-generated method stub
		//һ������ڴ�����д���ڴ���
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = 0;
		String resultString = "";
		try {
			if(inputStream != null)
			{
				//���������ж�ȡ����
				while((len=inputStream.read(data)) != -1)
				{
					byteArrayOutputStream.write(data, 0, len);

				}
				resultString = new String(byteArrayOutputStream.toByteArray(),encode);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultString;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String,String>();
		String path = "http://192.168.1.227:8080/myHttp/servlet/LoginAction";
		map.put("username", "admin");
		map.put("password", "123");
		String result = sendHttpClientPost(path,map,"utf-8");
		System.out.println(result);
	}

}
