package com.beijing.IsUniversity.javaassist;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

/**
 * 自定义文件系统类加载器
 * @author jack
 *
 */
public class NetClassLoader extends ClassLoader {
	//com.beijing.IsUniversity.User -->www.sxt.cn/myjava/  com/beijing/IsUniversity/User.class
	private String rootUrl;
	
	public NetClassLoader(String rootUrl) {
		this.rootUrl = rootUrl;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> c = findLoadedClass(name);
		//应该要先查清楚有没有加载过这个类，如果已经加载，则直接返回加载好的类，如果没有，则加载新的类。
		if(c!=null){
			return c;
		}else{
			ClassLoader parent = this.getParent();
			try{
				c = parent.loadClass(name);//委派给父类加载
			}catch (Exception e){
				//e.printStackTrace();
			}
			
			if(c!=null){
				return c;
			}else{
				byte[] classData = getClassData(name);
				if(classData==null){
					throw new ClassNotFoundException(); 
				}else{
					c = defineClass(name,classData,0,classData.length);
				}
			}
			
		}
		return c;
	}

	private byte[] getClassData(String Classname){
		String path = rootUrl+"/"+Classname.replace('.', '/')+".class";
		//IOUtils,可以使用他们将流中的数据转成数组。
		InputStream is=null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			URL url = new URL(path);
			is = url.openStream();
			
			byte[] buffer = new byte[1024];
			int temp = 0;
			while((temp=is.read(buffer))!=-1){
				baos.write(buffer,0,temp);
			}
			
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if (baos != null) {
					baos.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}























