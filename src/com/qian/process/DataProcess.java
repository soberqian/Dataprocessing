package com.qian.process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.hfutec.preprocess.wordseg.Jieba;
import com.qian.util.StopwordsChinese;
/**
 * 中文分词，去噪音字符，去停用词操作
 * 
 * Gibbs sampling for BTM
 * @author: Yang Qian (HFUT)
 */
public class DataProcess {
	static Jieba jieba = new Jieba();
	public static void main(String[] args) throws IOException {
		String[] sentences =
				new String[] {"这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱Python和C++。", "我不喜欢日本和服。", "雷猴回归人间。",
						"工信处女干事每月经过下属科室都要亲口交代24口交换机等技术性器件的安装工作", "结果婚的和尚未结过婚的"};
		for (String sentence : sentences) {
			System.out.println(getContentProcess(sentence)); 
		}
	}
	static String getContentProcess(String content) {
		String ctest = content.replaceAll("\\#(.*?)\\#", "").replaceAll("\\@(.*?)(\\s|\\#|\\p{Punct}|\\uff09|\\u2019|\\uff0c|\\u3009|\\u300b|\\u3001|\\u3011)", "").replaceAll(
				"(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)", "").replaceAll("\\p{Punct}", "").replaceAll("[\\pP+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]", "");
		List<String> input_text_list = new ArrayList<>();
		input_text_list.add(ctest);
		List<String> outList = jieba.seg(input_text_list);
		String seg = outList.get(0);
		ArrayList<String> words = new ArrayList<String>();
		String cutWordResult[] =seg.replaceAll("\\pP|\\pS", "").split(" ");
		for (int i = 0; i < cutWordResult.length; i++) {
			words.add(cutWordResult[i]);
		}
		for(int i = 0; i < words.size(); i++){
			if(StopwordsChinese.isStopword(words.get(i))){
				words.remove(i);
				i--;
			}
		}
		String lastContent = "";
		for (int i = 0; i < words.size(); i++) {
			lastContent += words.get(i).replaceAll("[^A-Za-z0-9 \\u4e00-\\u9fa5]", "") + " ";
		}
		return lastContent.trim();
		
	}

}
