package com.beijing.IsUniversity.javaassist;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 加密工具类
 * @author jack
 *
 */
public class EncrptUtil {
	public static void main(String[] args) {
		encrpt("d:/myjava/HelloWorld.class","d:/myjava/temp/HelloWorld.class" );
	}

	public static void encrpt(String src,String dest){
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream(src);
			fos = new FileOutputStream(dest);
			
			int temp = -1;
			while((temp=fis.read())!=-1){
				fos.write(temp^0xff);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}















