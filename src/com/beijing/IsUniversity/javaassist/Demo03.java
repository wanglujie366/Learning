package com.beijing.IsUniversity.javaassist;
/**
 * 测试定义的FileSystemClassLoader
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
		System.out.println(c3.hashCode());//同一个类被不同加载器加载，jvm认为是不同的
		System.out.println(c4.hashCode());
		System.out.println(c4.getClassLoader());//引导类加载器
		System.out.println(c3.getClassLoader());//自定义的类加载器
		System.out.println(c5.getClassLoader());//系统默认的类加载器
	}

}
