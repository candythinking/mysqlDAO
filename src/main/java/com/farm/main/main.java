package com.farm.main;

import com.farm.dao.FarmDao;

import java.util.List;

public class main
{

	public static void main(String[] args)
	{
		FarmDao farmDao = new FarmDao();
		String queryString = "2015，斗南花卉鲜花批发交易市场，北京，云南，玫瑰花";
		List list = farmDao.query(queryString,null,null,null);
		for(Object ob : list){
			System.out.println(ob);
		}

//		Set set = farmDao.queryField(FarmDao.province);



/*		String str = "云南斗南花卉市场卡罗拉2016-10" ;
		System.out.println(BaseAnalysis.parse(str));
		System.out.println(ToAnalysis.parse(str));
		System.out.println(DicAnalysis.parse(str));
		System.out.println(IndexAnalysis.parse(str));
		System.out.println(NlpAnalysis.parse(str));*/

// 增加新词,中间按照'\t'隔开
/*		UserDefineLibrary.insertWord("ansj中文分词", "userDefine", 1000);
		List<Term> terms = ToAnalysis.parse("我觉得Ansj中文分词是一个不错的系统!我是王婆!").getTerms();
		System.out.println("增加新词例子:" + terms);*/
		String str = "云南斗南花卉市场12.";



	}
}
