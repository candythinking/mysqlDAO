package com.farm.search;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Every 数据库的方法接口
 */
public interface SqlInterface
{
	public void insertData(Students students); // 插入数据

	public void deleteDataById(long id); // 删除数据

	public void updateData(Students students); // 更新数据

	public Students querySingalDataById(long id);// 查询单个数据

	public List<Students> queryDate(); // 查询所有数据

	public List queryAll(Class c1); // 查询所有数据

    public List queryColumn(Map<String,String> map, String orderByField,String limit); // 根据条件查询
}
