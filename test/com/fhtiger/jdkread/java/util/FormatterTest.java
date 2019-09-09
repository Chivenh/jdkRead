package com.fhtiger.jdkread.java.util;

import com.fhtiger.jdkread.TestStarter;
import org.junit.Test;

import java.util.Formatter;
import java.util.Locale;

/**
 * FormatterTest
 * {@link java.util.Formatter}
 * @author LFH
 * @since 2019年09月06日 11:04
 */
public class FormatterTest extends TestStarter {

	@Test
	public void sampleTest(){

		StringBuilder stringBuilder = new StringBuilder();
		/*创建formatter对象,第一个参数是Appendable的实例对象,第二个参数是方言. */
		Formatter formatter = new Formatter(stringBuilder, Locale.CHINA);

		//每次执行format方法的结果将追加到上面构造器的第一个参数中.
		 formatter.format("%4$2s %3$2s %2$2s %1$2s", "a", "b", "c", "d");
		 formatter.format("%3s %2S %2C %2s", "a", "b", 'c', "d");

		 formatter.format(Locale.CHINA,"\ne = %010.4f",Math.E);

		outr(stringBuilder);

	}
}
