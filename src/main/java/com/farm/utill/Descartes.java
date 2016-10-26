package com.farm.utill;

/**
 * Created by Administrator on 2016/10/25.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Descartes
{

    public static void run(List<List<String>> dimvalue, List<String> result, int layer, String curstring)
    {
        //大于一个集合时：
        if (layer < dimvalue.size() - 1)
        {
            //大于一个集合时，第一个集合为空
            if (dimvalue.get(layer).size() == 0)
                run(dimvalue, result, layer + 1, curstring);
            else
            {
                for (int i = 0; i < dimvalue.get(layer).size(); i++)
                {
                    StringBuilder s1 = new StringBuilder();
                    s1.append(curstring + "  or  ");
                    s1.append(dimvalue.get(layer).get(i));
                    run(dimvalue, result, layer + 1, s1.toString());
                }
            }
        }
        //只有一个集合时：
        else if (layer == dimvalue.size() - 1)
        {
            //只有一个集合，且集合中没有元素
            if (dimvalue.get(layer).size() == 0) {
                result.add(curstring);
//                System.out.println("if:"+curstring);
                //只有一个集合，且集合中有元素时：其笛卡尔积就是这个集合元素本身
            }else {
                for (int i = 0; i < dimvalue.get(layer).size(); i++)
                {
                    result.add(curstring + "  or  " + dimvalue.get(layer).get(i));
//                    System.out.println("else:"+curstring + " and " + dimvalue.get(layer).get(i));
                }
            }
        }
    }

    public static List time(String str){
        Pattern p = Pattern.compile("([0-9]{4})?([年|\\-|/|.])?([0-9]{1,2})?([月|\\-|/|.])?([0-9]{1,2})?");
        Matcher m = p.matcher(str);
        List<String> dates = new ArrayList<>();
        while (m.find()) {
            if (!"".equals(m.group())) {
                String date = m.group();
                date = date.replaceAll("年", "-");
                date = date.replaceAll("月", "-");
                date = date.replaceAll("/", "-");
                date = date.replaceAll("\\.", "-");
                dates.add(date);
            }
        }
        return dates;
    }


    /**
     * @param args
     */
    public static void main(String[] args)
    {
        List<List<String>> dimvalue = new ArrayList<List<String>>();
        List<String> v1 = new ArrayList<String>();
        v1.add("a");
        v1.add("b");
        v1.add("c");
        List<String> v2 = new ArrayList<String>();
        v2.add("a");
        v2.add("b");
        v2.add("c");
        List<String> v3 = new ArrayList<String>();
        v3.add("a");
        v3.add("b");
        v3.add("c");
        List<String> v4 = new ArrayList<String>();
        v4.add("a");
        v4.add("b");
        v4.add("c");

        dimvalue.add(v1);
        dimvalue.add(v2);
        dimvalue.add(v3);
        dimvalue.add(v4);

//        Set<String> result = new HashSet<>();

        List<String> result = new ArrayList<>();
        Descartes.run(dimvalue, result, 0, "");

        int i = 1;
        System.out.println(result.size());
        for (String s : result)
        {
            System.out.println(i++ + ":" +s);
        }
    }

}
