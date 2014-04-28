package com.oecp.myplatform;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonTest {
	public static void main(String[] args) {
       Map map = new HashMap();
       map.put("name", "changer");
       map.put("age", 200);
       map.put("date", new Date());
       JSON.toJSONString(new Date(), SerializerFeature.WriteDateUseDateFormat);
       System.out.println(JSON.toJSONStringWithDateFormat(map, "yyyy-MM-dd"));;
	}
}
