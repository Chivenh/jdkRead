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
	public void defaultAppenderTest(){

		/* 使用默认appender*/

		Formatter formatter = new Formatter();

		formatter.format("F:> %s ","This is a simple `String` object!");

		outr(formatter.out());
		//=> F:> This is a simple `String` object!

		formatter.format("F:> %d ",200);

		outr(formatter.out());
		//=> F:> This is a simple `String` object! F:> 200

		formatter.format("F:> %e",35000000000d);

		outr(formatter.out());
		//=> F:> This is a simple `String` object! F:> 200 F:> 3.500000e+10

		formatter.format("F:> %.2e",2345666666.123d);

		outr(formatter.out());
		//=> F:> This is a simple `String` object! F:> 200 F:> 3.500000e+10F:> 2.35e+09

	}

	@Test
	public void defineAppenderTest(){

		/*初始化时使用自己的appender*/

		StringBuilder outStr = new StringBuilder();

		Formatter formatter = new Formatter(outStr);

		formatter.format("F:> %5s","ABCDE-1212");

		outr(outStr);

		outr(String.format("%s",230));
	}

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
