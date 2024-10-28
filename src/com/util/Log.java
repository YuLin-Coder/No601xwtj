package com.util;

/**
 * 日志
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Log {

	public void addLog(String str){
		try {
			File file=new File("c://log.txt");
			FileWriter fw=new FileWriter(file,true);
			BufferedWriter bw=new   BufferedWriter(fw);
			PrintWriter   pw   =   new   PrintWriter(bw);   
			pw.write(str);
			bw.newLine();//断行
			bw.flush();//将数据更新至文件
			pw.close();
			fw.close();//关闭文件流
		} catch (FileNotFoundException e) {
			System.out.println("警告：日志文件没找到！！！！");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("警告：日志文件IO错误！！！！");
			e.printStackTrace();
		}
	}
}

