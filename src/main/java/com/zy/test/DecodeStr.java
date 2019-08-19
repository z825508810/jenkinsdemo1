package com.zy.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class DecodeStr {
@SuppressWarnings("resource")
public static void main(String[] args) throws IOException {
	try {
		InputStreamReader r = new InputStreamReader(new FileInputStream("F:\\1.txt"),"GBK");
		BufferedReader bf = new BufferedReader(r);
		String tmp=bf.readLine();
		while(tmp != null){
		System.out.println(new String(tmp.getBytes("gbk"),"utf-8"));
		tmp=bf.readLine();
		}
	} catch (UnsupportedEncodingException | FileNotFoundException e) {
		e.printStackTrace();
	}
}

}
