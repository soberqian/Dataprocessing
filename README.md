# Dataprocessing
中文分词 去标点符号 去停用词操作

This program is used for text data processing.
First, we remove meaningless words, such as special characters (e.g., #, % and &), URLs. Second, we use an efficient open-sourcing software Jieba tool for the segmentation of the text. Finally, we remove the stop words that we defined.

# how to run
Running the DataProcess.java, you can obtain the result.

```java
public static void main(String[] args) throws IOException {
		String[] sentences =
				new String[] {"这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱Python和C++。", "我不喜欢日本和服。", "雷猴回归人间。",
						"工信处女干事每月经过下属科室都要亲口交代24口交换机等技术性器件的安装工作", "结果婚的和尚未结过婚的"};
		for (String sentence : sentences) {
			System.out.println(getContentProcess(sentence)); 
		}
	}
```
The output in the console are shown as follows:
```java
这是 伸手不见五指 黑夜 孙悟空 爱 北京 爱 python c
喜欢 日本 和服
雷猴 回归 人间
工信处 女干事 每月 下属 科室 交代 24 口 交换机 技术性 器件 安装 工作
婚 尚未 结过婚
```
