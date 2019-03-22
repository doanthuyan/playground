package io.education.bookExchange.util;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
public class DataUtil {
	private static DataUtil util;
	private DataUtil() {};
	
	public static DataUtil getInstance() {
		if (util == null) util = new DataUtil();
		return util;
	}
	public Map<String,String> standardizeMapForJackson(Map<String,List<String>> inputMap){
		Map<String,String> res = new HashMap<String, String>();
		inputMap.forEach((k,v)->{
			if(v.isEmpty()) {
				res.put(k, "");
			}else {
				res.put(k,v.get(0));
			}
		});
		return res;
	}
}
