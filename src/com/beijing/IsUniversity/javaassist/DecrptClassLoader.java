package com.beijing.IsUniversity.javaassist;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * �Զ����ļ�ϵͳ�������
 * @author jack
 *
 */
public class DecrptClassLoader extends ClassLoader {
	//com.beijing.IsUniversity.User -->d:/mycode/  com/beijing/IsUniversity/User.class
	private String rootDir;
	
	public DecrptClassLoader(String rootDir) {
		this.rootDir = rootDir;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> c = findLoadedClass(name);
		//Ӧ��Ҫ�Ȳ������û�м��ع�����࣬����Ѿ����أ���ֱ�ӷ��ؼ��غõ��࣬���û�У�������µ��ࡣ
		if(c!=null){
			return c;
		}else{
			ClassLoader parent = this.getParent();
			try{
				c = parent.loadClass(name);//ί�ɸ��������
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
		String path = rootDir+"/"+Classname.replace('.', '/')+".class";
		//IOUtils,����ʹ�����ǽ����е�����ת�����顣
		InputStream is=null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			is = new FileInputStream(path);
			
		
			int temp = 0;
			while((temp=is.read())!=-1){
				baos.write(temp^0xff);//ȡ���������൱�ڽ���
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























