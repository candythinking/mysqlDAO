package com.farm.main;

import com.farm.dao.FarmDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main
{

	public static void main(String[] args)
	{
		FarmDao farmDao = new FarmDao();
		Map<String,String> map = new HashMap<String,String>();
//		map.put("province","北京");
//		map.put("name","大豆");
		map.put("market","油");
		map.put("time","2014-12");
		List list1 = farmDao.queryColumn(map,null,null);
		for(Object ob : list1){
			System.out.println(ob);
		}
	}

}
