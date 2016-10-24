package com.farm.search;

import com.farm.model.Farm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySqlDemo
{

	public static void main(String[] args)
	{
		ImplementSqlInterface implementSqlInterface = new ImplementSqlInterface();
		// Students students = new Students();
		// students.setId(141040110);
		// students.setAge(23);
		// students.setName("陈小秋");
		// students.setSex("女");
		// implementSqlInterface.insertData(students);
		// implementSqlInterface.deleteDataById(141040110);
		// implementSqlInterface.updateData(students);
		// Students students =
		// implementSqlInterface.querySingalDataById(141040110);
		// System.out.println(students.getId() + "," + students.getAge() + "," +
		// students.getName() + "," + students.getSex());
//		List<Students> list = implementSqlInterface.queryDate();
		List list = implementSqlInterface.queryAll(Farm.class);
		Map<String,String> map = new HashMap<String,String>();
		map.put("province","北京");
		List list1 = implementSqlInterface.queryColumn(map,null,null);
		for(Object ob : list1){
			System.out.println(ob);
		}
	}

}
