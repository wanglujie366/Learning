package com.beijing.IsUniversity.javaassist;
/**
 * 线程上下文类加载器
 * @author jack
 *
 */
public class Demo05 {
	public static void main(String[] args) throws Exception {
		ClassLoader loader = Demo05.class.getClassLoader();
		System.out.println(loader);
		
		ClassLoader loader2 = Thread.currentThread().getContextClassLoader();
		System.out.println(loader2);
		
		Thread.currentThread().setContextClassLoader(new FileSystemClassLoader("d:/myjava"));
		System.out.println(Thread.currentThread().getContextClassLoader());
		
		Class<Demo01> c = (Class<Demo01>) Thread.currentThread().getContextClassLoader().loadClass("com.beijing.IsUniversity.javaassist.Demo01");
		System.out.println(c);
		System.out.println(c.getClassLoader());
	}

}
