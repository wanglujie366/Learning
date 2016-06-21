package com.beijing.IsUniversity.javaassist;
/**
 * ���Զ����FileSystemClassLoader
 * @author jack
 *
 */
public class Demo03 {
	public static void main(String[] args) throws Exception {
		FileSystemClassLoader loader1 = new FileSystemClassLoader("d:/myjava");
		FileSystemClassLoader loader2 = new FileSystemClassLoader("d:/myjava");
		
		Class<?> c1 = loader1.loadClass("HelloWorld");
		Class<?> c2 = loader1.loadClass("HelloWorld");
		Class<?> c3 = loader2.loadClass("HelloWorld");
		
		Class<?> c4 = loader2.loadClass("java.lang.String");
		Class<?> c5 = loader2.loadClass("com.beijing.IsUniversity.javaassist.Demo01");
		
		System.out.println(c1.hashCode());
		System.out.println(c2.hashCode());
		System.out.println(c3.hashCode());//ͬһ���౻��ͬ���������أ�jvm��Ϊ�ǲ�ͬ��
		System.out.println(c4.hashCode());
		System.out.println(c4.getClassLoader());//�����������
		System.out.println(c3.getClassLoader());//�Զ�����������
		System.out.println(c5.getClassLoader());//ϵͳĬ�ϵ��������
	}

}
