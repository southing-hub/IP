package com.wgm2020.ip;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class QIPs {
	
	public void Qips(String ip){
		String host = "https://dm-81.data.aliyun.com";
	    String path = "/rest/160601/ip/getIpInfo.json";
	    String method = "GET";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE fab01018ece1449dab440bc778772d84");
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("ip", ip);


	    try {
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	System.out.println(response.toString());
	    	//获取response的body
	    	String jsonresult = EntityUtils.toString(response.getEntity());
	    	//System.out.println(jsonresult );
	    	JSONObject obj = JSONObject.fromObject(jsonresult);
	    	JSONObject obj1 = (JSONObject) obj.get("data");
	    	String result="";
	    	result += "本机IP为："+obj1.get("ip")+"\n";
	    	result += "所在国家："+obj1.get("country")+"\n";
	    	result += "所在区域："+obj1.get("area")+"\n";
	    	result += "所在城市："+obj1.get("city")+"\n";
	    	result += "此ip的运营商是："+obj1.get("isp");
	    	System.out.println(result);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	public static void main(String[] args) {
		QIPs ip = new QIPs();
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入您要查询的IP：");
		String IP = sc.nextLine();
		ip.Qips(IP);
	}
}
