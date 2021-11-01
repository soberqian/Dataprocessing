package com.qian.summary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
public class BasiseCount_Chejiahao {

	public static void main(String[] args) throws IOException {
		wordcount("data_text/text_doctor_process_use.txt","\t","\\s+",2,"utf-8");
//		wordcount("audio_text/audio_preprocessing/audio_process_usetext.txt","\t","\\s+",0,"utf-8");
//		wordcountC("data/13_wan_data/rawdata_process13_chejiaohao.txt","====","\\s+",1, "gbk");
	}
	//基本统计统一使用文件
	public static void wordcount(String file,String splitS,String splitG, int c,String code) throws IOException {
		//数据读入
		BufferedReader reader = new BufferedReader( new InputStreamReader( new FileInputStream( new File(file)),code));
		List<String> list = new ArrayList<>();
		String s = null;
		while ((s = reader.readLine())!=null) {
			list.add(s.split(splitS)[c]);
		}
		reader.close();
		double sumcount = 0;
		int count = 0;
		List<Integer> listMax = new ArrayList<>();
		List<Integer> sizeIntegers = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			sizeIntegers.add(list.get(i).split(splitG).length);
			sumcount += list.get(i).split(splitG).length;
			count++;
			listMax.add(list.get(i).split(splitG).length);
		}
		double mean = sumcount/count;  //均�??
		System.out.println("aver:" + mean);
		//计算方差
		double sumst = 0.0;
		for (int i = 0; i < list.size(); i++) {
			sumst += (list.get(i).split(splitG).length-mean)*(list.get(i).split(splitG).length-mean);
		}
		double std = Math.sqrt(sumst/count);
		System.out.println("std:" + std);
		double max = (double) Collections.max(listMax);
		System.out.println("max:" + max);
		double min = (double) Collections.min(listMax);
		System.out.println("min:" + min);
		//输出频率
		Map<Integer, Integer> countMap = new Hashtable<Integer, Integer>();
		for (int i = 0; i < sizeIntegers.size(); i++) {
			if (!countMap.containsKey(sizeIntegers.get(i))) {
				countMap.put(sizeIntegers.get(i), 1);
			}else {
				countMap.put(sizeIntegers.get(i), countMap.get(sizeIntegers.get(i))+1);
			}
		}
		List<Map.Entry<Integer, Integer>> listm = new ArrayList<Map.Entry<Integer, Integer>>(countMap.entrySet());  
		Collections.sort(listm, new Comparator<Map.Entry<Integer, Integer>>() {  
			//降序排序  
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {  
				//return o1.getValue().compareTo(o2.getValue());  
				return o2.getValue().compareTo(o1.getValue());  
			}  
		}); 
		System.out.println("Percentile 25:" + getPercentile_nit(listMax,0.25));
		System.out.println("Percentile 75:" + getPercentile_nit(listMax,0.75));
		System.out.println("Median:" + getMedian(listMax));
	}
	
	@SuppressWarnings("unused")
	private static double getPercentile(List<Double> dataList, double p) {
        int n = dataList.size();
        dataList.sort(new Comparator<Double>() {
            //从小到大排序
            @Override
            public int compare(Double o1, Double o2) {
                if(o1 == null || o2== null){
                return 0;
                }
                return o1.compareTo(o2);
            }
        });
        double px =  p*(n-1);
        int i = (int)java.lang.Math.floor(px);
        double g = px - i;
        if(g==0){
            return dataList.get(i);
        }else{
            return (1-g)*dataList.get(i)+g*dataList.get(i+1);
        }
    }
	private static double getPercentile_nit(List<Integer> dataList, double p) {
        int n = dataList.size();
        dataList.sort(new Comparator<Integer>() {
            //从小到大排序
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 == null || o2== null){
                return 0;
                }
                return o1.compareTo(o2);
            }
        });
        double px =  p*(n-1);
        int i = (int)java.lang.Math.floor(px);
        double g = px - i;
        if(g==0){
            return dataList.get(i);
        }else{
            return (1-g)*dataList.get(i)+g*dataList.get(i+1);
        }
    }
	private static double getMedian(List<Integer> total) { //求中位数
		double j = 0;
		//集合排序
	    Collections.sort(total);
	    int size = total.size();
	    if(size % 2 == 1){
	    	j = total.get((size-1)/2);
	    }else {
	    	//加0.0是为了把int转成double类型，否则除以2会算错
	    	j = (total.get(size/2-1) + total.get(size/2) + 0.0)/2;
	    }
		return j;
    }
}
